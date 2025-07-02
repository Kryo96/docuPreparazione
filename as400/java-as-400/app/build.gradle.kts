import mx.com.inftel.wildfly.gradle_plugin.WildFlyDeployTask
import mx.com.inftel.wildfly.gradle_plugin.WildFlyPlugin

plugins {
    java
    application
    id("war")
    id("mx.com.inftel.wildfly") version "1.0.2"
}

group = "dontKnow"
version= "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//configure<JavaPlugin>{}

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
    archiveAppendix.set("wildfly")
    archiveBaseName.set("war_db2")
}

wildfly{
    controller="127.0.0.1:9990"
    username="user"
    password="user"
    deployment = tasks.named<War>("war").get().archiveFile.get().asFile.absolutePath
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("org.example.App")
}
