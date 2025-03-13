plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.t1"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    //Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("ru.t1.grigiv.starter:t1-logger-starter:0.0.1-SNAPSHOT")

}

tasks.withType<Test> {
    useJUnitPlatform()
}