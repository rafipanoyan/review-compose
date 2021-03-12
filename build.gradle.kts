// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha09")
        classpath(kotlin("gradle-plugin", version = Kotlin.version))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // TODO remove this when https://github.com/Kotlin/kotlinx.collections.immutable/issues/96 is fixed
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}