plugins {
    kotlin("jvm") version "1.9.22"
    // id("net.minecraftforge.gradle") version "5.1.0"
    // id("net.minecraftforge.gradle.forge") version "2.20.1"
    id("net.minecraftforge.gradle.forge") version "2.0.2"

}



group = "com.example"
version = "1.0-SNAPSHOT"
archivesBaseName = "more-mobs-mod"

repositories {
    mavenCentral()
    maven(url = "https://maven.minecraftforge.net")
    maven(url = "https://files.minecraftforge.net/maven")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    minecraft("net.minecraftforge:forge:1.21-51.0.17")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}