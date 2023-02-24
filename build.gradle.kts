/**
 * Alchemist build for Web Renderer execution.
 */
plugins {
    kotlin("multiplatform") version "1.8.0"
    application
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

val alchemistVersion = "25.14.2"

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("it.unibo.alchemist:alchemist:$alchemistVersion")
                implementation("it.unibo.alchemist:alchemist-incarnation-sapere:$alchemistVersion")
            }
        }
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                allWarningsAsErrors = true
            }
        }
    }
}

application {
    mainClass.set("it.unibo.alchemist.Alchemist")
}
