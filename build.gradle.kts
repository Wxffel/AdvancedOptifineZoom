import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * BUILD CONSTANTS
 */

val minecraftVersion = "1.16.5"
val yarnMappingsVersion = "1.16.5+build.6:v2"
val fabricLoaderVersion = "0.11.3"
val fabricApiVersion = "0.32.5+1.16"
val fabricLanguageKotlinVersion = "1.5.0+kotlin.1.4.31"

val jvmVersion = JavaVersion.VERSION_1_8
val jvmVersionString = jvmVersion.versionString

/*
 * PROJECT
 */

group = "de.kpaw"
version = "1.0.0"

/*
 * PLUGINS
 */

plugins {
    kotlin("jvm") version "1.4.30"
    id("fabric-loom") version "0.6-SNAPSHOT"
}

/*
 * DEPENDENCY MANAGEMENT
 */

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

/*
 * BUILD
 */

java.targetCompatibility = jvmVersion
java.sourceCompatibility = jvmVersion

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

/*
 * EXTENSIONS
 */

val JavaVersion.versionString get() = majorVersion.let {
    val version = it.toInt()
    if (version <= 10) "1.$it" else it
}

fun TaskProvider<KotlinCompile>.configureJvmVersion() { get().kotlinOptions.jvmTarget = jvmVersionString }