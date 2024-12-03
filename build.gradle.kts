import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.0.1"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "life"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://raw.github.com/gephi/gephi/mvn-thirdparty-repo/")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation ("com.google.code.gson:gson:2.10.1")


}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            packageName = "Life"
            packageVersion = "1.0.0"
        }
    }
}
tasks.shadowJar {
    archiveBaseName.set("myapp")
    archiveClassifier.set("")
    archiveVersion.set("4.0")

    manifest {
        attributes["Main-Class"] = "D/Progs/Life/src/main/kotlin/MainKt"
    }
}