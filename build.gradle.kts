plugins {
	java
	id("org.sonarqube") version "3.4.0.2513"
}

subprojects {

	apply {
		plugin("java")
		plugin("org.sonarqube")
	}

	group = "com.fiap"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_19

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation(rootProject.libs.spring.boot.starter.web)

		//Test
		testImplementation(rootProject.libs.spring.boot.starter.test)
	}

	sonarqube {
		properties {
			property("sonar.sources", "src")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}