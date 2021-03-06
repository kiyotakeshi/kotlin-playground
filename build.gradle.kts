plugins {
    kotlin("jvm") version "1.6.10"
}

group = "org.kiyotakeshi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

dependencies {
    implementation(kotlin("stdlib"))
}
