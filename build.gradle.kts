plugins {
    kotlin("jvm") version "1.9.21"
    id("io.gitlab.arturbosch.detekt") version ("1.23.6")
}

group = "org.kiyotakeshi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

    // https://kotlinlang.org/docs/jvm-test-using-junit.html#run-a-test
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    // testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.26.3")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
}

tasks.test {
    useJUnitPlatform()
}

// https://detekt.dev/docs/gettingstarted/gradle#kotlin-dsl-3
detekt {
    toolVersion = "1.23.6"

    // https://detekt.dev/docs/gettingstarted/cli#use-the-cli
    // --auto-correct, The additional 'formatting' rule set, added with '--plugins', does support it and needs this flag.
    autoCorrect = true

    // detekt config に対して上書きしたいものだけを config/detekt/detekt.yml に記載する
    buildUponDefaultConfig = true
    config.setFrom("config/detekt/detekt.yml", "config/detekt/detekt-formatting.yml")

    // https://detekt.dev/docs/gettingstarted/gradle/#dependencies
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion(io.gitlab.arturbosch.detekt.getSupportedKotlinVersion())
            }
        }
    }
}