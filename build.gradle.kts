plugins {
    id("java")
    application
}
application {
    mainClass = "com.mnboot.Main"
}


group = "com.mnboot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")

    testCompileOnly("org.projectlombok:lombok:1.18.48")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.48")

    implementation("org.codejargon.feather:feather:1.0")
    implementation("org.jsoup:jsoup:1.24.1")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<JavaExec>("run"){

    standardInput = System.`in`
    logging.captureStandardOutput(LogLevel.QUIET)
    logging.captureStandardError(LogLevel.QUIET)
}