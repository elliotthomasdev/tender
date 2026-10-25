plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

java {
    toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.spring.boot.starter.webmvc.test)
}

tasks.test { useJUnitPlatform() }