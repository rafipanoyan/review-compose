plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    testImplementation("junit:junit:4.13")
}