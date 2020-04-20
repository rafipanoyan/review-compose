import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    testImplementation("junit:junit:4.13")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}