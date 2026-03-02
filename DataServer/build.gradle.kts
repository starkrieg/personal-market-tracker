plugins {
    id("java")
    id("org.springframework.boot").version("4.0.3")

    // JaCoCo for Unit Test coverage
    id("jacoco")
}

apply(plugin = "io.spring.dependency-management")

group = "personal"
version = "0.0.1-WIP"

// Gradle 8.14 support JDK up to 24
// JDK 21 is the LTS before 25, so we keeping it at 21 for now
// JDK 21 can be kept until Sept 2026 (https://www.oracle.com/java/technologies/java-se-support-roadmap.html)
java.setSourceCompatibility("21") // Set your desired Java version, e.g., Java 21

repositories {
    mavenCentral()
}

dependencies {
    // This dependency transitively includes JPA, Hibernate, and JDBC support
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // For web application
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Database at this point should be Postgres
    implementation("org.postgresql:postgresql")

    // Cache database as Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Thymeleaf for templating web pages
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Add actuator endpoints
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // For testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

jacoco {
    toolVersion = "0.8.14"
    // Define where JaCoCo reports will be stored
    // path /build/reports/jacoco
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}

tasks.test {
    useJUnitPlatform()
    // Always generate JaCoCo report after running tests
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    // Test should always be run before the JaCoCo Test Report
    dependsOn(tasks.test)
    reports {
        csv.required = false;
        xml.required = false;
        //html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}