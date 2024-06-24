plugins {
    kotlin("jvm") version "1.9.22"
    id("org.gradle.idea") version "2021.3.3"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://files.minecraftforge.net/maven")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    minecraft("net.minecraftforge:forge:1.21-51.0.17")
}