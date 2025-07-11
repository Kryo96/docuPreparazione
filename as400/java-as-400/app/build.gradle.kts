import mx.com.inftel.wildfly.gradle_plugin.WildFlyDeployTask
import mx.com.inftel.wildfly.gradle_plugin.WildFlyUndeployTask

plugins {
    id("mx.com.inftel.wildfly") version "1.0.2"
    application
    id("war")
    java
}

group = "org.company.middleware"
version= "1.0.0"


dependencies {
    // Jakarta EE API
    providedCompile("jakarta.platform:jakarta.jakartaee-api:9.1.0")

    // Driver JDBC - provided
    providedCompile("net.sf.jt400:jt400:11.0")
    providedCompile("com.ibm.db2:jcc:11.5.0.0")

    // Salesforce SDK
    implementation("com.force.api:force-wsc:57.0.0")
    implementation("com.force.api:force-partner-api:57.0.0")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("ch.qos.logback:logback-core:1.4.8")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}
// spec https://docs.gradle.org/current/userguide/upgrading_version_8.html#test_task_default_classpath
val test by testing.suites.existing(JvmTestSuite::class)

val wildflyHost: String = (project.findProperty("wildflyHost") as? String) ?: "127.0.0.1:9990"
val wildflyUser: String = (project.findProperty("wildflyUser") as? String) ?: "user"
val wildflyPassword: String = (project.findProperty("wildflyPassword") as? String) ?: "user"

tasks.named<WildFlyDeployTask>("wildflyDeploy"){
    dependsOn(tasks.named("war"))
    description = "Deploy war wildfly"
    group = "deployment"

    controller.set(wildflyHost)
    username.set(wildflyUser)
    password.set(wildflyPassword)

    // war path
    deploymentPath.set(tasks.named<War>("war").get().archiveFile.get().asFile.absolutePath)
    deploymentName.set(tasks.named<War>("war").get().archiveFile.get().asFile.name)

    // is war or exploded path
    deploymentArchive.set(true)
    deploymentPersistent.set(false)

    doFirst { println("Deploying war: ${deploymentName.get()}") }
    doLast { println("Deploy completed") }
}

tasks.named<WildFlyUndeployTask>("wildflyUndeploy") {
    description = "Undeploy war wildfly"
    group = "deployment"

    controller.set(wildflyHost)
    username.set(wildflyUser)
    password.set(wildflyPassword)

    // exact name of deployed war
    deploymentName.set(tasks.named<War>("war").get().archiveFile.get().asFile.name)

    doFirst { println("Undeploying war: ${deploymentName.get()}") }
    doLast { println("Undeploy completed") }
}



tasks.withType<JavaCompile>().configureEach {
    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

tasks.register<Copy>("prepareDrivers") {
    from(configurations["providedCompile"])
    include("**/jt400*.jar")
    include("**/jcc*.jar")
    into("${layout.projectDirectory}/wildfly-drivers")
    println("Driver JDBC pronti in: ${layout.projectDirectory}/wildfly-drivers")
}



tasks.named<War>("war") {
    archiveBaseName.set("salesforce_as400-middleware")
    manifest {
        attributes["Implementation-Title"] = "My App"
        attributes["Implementation-Version"] = "1.0"
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}


tasks.register<Test>("testDB2") {
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })
    // Con parametri default (localhost:50000)
    // ./gradlew testDB2

    // Con parametri custom
    // ./gradlew testDB2 -Pdb2.host=192.168.1.100 -Pdb2.port=50001

    description = "Esegue test di connessione DB2"
    group = "verification"

    useJUnitPlatform {
        includeTags("integration")
    }

    systemProperty("app.env", "development")
    systemProperty("run.db2.tests", "true")
    systemProperty("db2.host", project.findProperty("db2.host") ?: "localhost")
    systemProperty("db2.port", project.findProperty("db2.port") ?: "50000")
    systemProperty("db2.database", project.findProperty("db2.database") ?: "SAMPLE")
    systemProperty("db2.user", project.findProperty("db2.user") ?: "db2inst1")
    systemProperty("db2.password", project.findProperty("db2.password") ?: "test")


    filter {
        includeTestsMatching("org.example.AppTest.testDB2Connection")
    }
}

tasks.register<Test>("testAS400") {
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })

    // Richiede sempre i parametri
    //    ./gradlew testAS400 \
    // -Pas400.host=your-as400.company.com \
    // -Pas400.user=YOURUSR \
    // -Pas400.password=YOURPASS \
    // -Pas400.libraries=YOURLIB

    description = "Esegue test di connessione AS400"
    group = "verification"

    useJUnitPlatform {
        includeTags("integration")
    }

    systemProperty("app.env", "production")
    systemProperty("run.as400.tests", "true")
    systemProperty("as400.host", project.findProperty("as400.host") as String? ?: "")
    systemProperty("as400.user", project.findProperty("as400.user") as String? ?: "")
    systemProperty("as400.password", project.findProperty("as400.password") as String? ?: "")
    systemProperty("as400.libraries", project.findProperty("as400.libraries") as String? ?: "PRODLIB")

    filter {
        includeTestsMatching("org.example.AppTest.testAS400Connection")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("org.example.App")
}
