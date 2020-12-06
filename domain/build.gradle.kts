plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Coroutines.version}")

    testImplementation("junit:junit:4.13.1")
}