plugins {
	java
}

subprojects {

	apply {
		plugin("java")
	}

	group = "com.fiap"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_19

	repositories {
		mavenCentral()
	}

	dependencies {
		//TODO implementar classes JPA
		//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
		runtimeOnly("com.mysql:mysql-connector-j")

		//Test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
