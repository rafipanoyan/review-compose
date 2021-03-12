plugins {
    kotlin("jvm")
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Coroutines.version}")

    testImplementation("junit:junit:4.13.2")
}