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

val alchemistVersion = "25.14.4"

kotlin {
    jvm {
        withJava()
    }
    js(IR) {
        browser {
            binaries.executable()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("it.unibo.alchemist:alchemist-web-renderer:$alchemistVersion")
                implementation("com.soywiz.korlibs.korim:korim:3.4.0")
            }
        }
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

tasks.named("run", JavaExec::class) {
    args("-y", "./src/commonMain/resources/dodgeball.yml")
}
