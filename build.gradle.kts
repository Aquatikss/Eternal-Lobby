plugins {
    id("java")
}

group = "com.aquamobs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.minestom:minestom-snapshots:f62abc722f")
}

tasks.test {
    useJUnitPlatform()
}