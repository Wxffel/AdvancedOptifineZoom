group = "de.kpaw"
version = "1.0.0"

plugins {
    kotlin("jvm") version "1.8.10"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.3")
    mappings("net.fabricmc:yarn:1.19.3+build.5")
    modImplementation("net.fabricmc:fabric-loader:0.14.17")
    modImplementation(fabricApi.module("fabric-key-binding-api-v1", "0.75.1+1.19.3"))
    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.1+kotlin.1.8.10")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
}