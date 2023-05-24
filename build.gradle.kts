plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	application
}

group = "com.fiap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

application {
	mainClass.set("com.fiap.burger.BurgerApplication")
}

repositories {
	mavenCentral()
}

dependencies {
	//TODO implementar classes JPA
	//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
