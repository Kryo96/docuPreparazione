import mx.com.inftel.wildfly.gradle_plugin.WildFlyDeployTask
import mx.com.inftel.wildfly.gradle_plugin.WildFlyUndeployTask

plugins {
    id("mx.com.inftel.wildfly") version "1.0.2"
    id("war")

    application
    java

}

group = "org.example"
version= "1.0-SNAPSHOT"

dependencies {
    testImplementation(libs.junit)
    implementation(libs.guava)
    implementation("com.ibm.db2:jcc:11.5.0.0")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
}

tasks.named<War>("war") {
    archiveBaseName.set("war_db2")
    archiveAppendix.set("wildfly")
    archiveClassifier.set("release")
    manifest {
        attributes["Implementation-Title"] = "My App"
        attributes["Implementation-Version"] = "1.0"
    }

}

tasks.named<WildFlyDeployTask>("wildflyDeploy"){
    dependsOn(tasks.named("war"))

    controller.set("127.0.0.1:9990")
    username.set("user")
    password.set("user")

    // war path
    deploymentPath.set(tasks.named<War>("war").get().archiveFile.get().asFile.absolutePath)
    deploymentName.set(tasks.named<War>(name="war").get().archiveFile.get().asFile.name)

    // is war or exploded path
    deploymentArchive.set(true)
    deploymentPersistent.set(true)
}


tasks.named<WildFlyUndeployTask>("wildflyUndeploy") {
    controller.set("127.0.0.1:9990")
    username.set("user")
    password.set("user")

    // exact name of deployed war
    deploymentName.set(tasks.named<War>("war").get().archiveFile.get().asFile.name)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("org.example.App")
}
