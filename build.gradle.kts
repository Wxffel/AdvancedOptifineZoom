val minecraftVersion = "1.18.2"
val yarnMappingsVersion = "1.18.2+build.3"
val fabricLoaderVersion = "0.13.3"
val fabricApiVersion = "0.50.0+1.18.2"
val fabricLanguageKotlinVersion = "1.7.3+kotlin.1.6.20"

group = "de.kpaw"
version = "1.0.0"

plugins {
    kotlin("jvm") version "1.6.20"
    id("fabric-loom") version "0.12-SNAPSHOT"
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappingsVersion")
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricLanguageKotlinVersion")
    modImplementation(fabricApi.module("fabric-key-binding-api-v1", fabricApiVersion))
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