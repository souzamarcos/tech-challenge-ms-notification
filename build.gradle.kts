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
		implementation(rootProject.libs.spring.boot.starter.web)

		//Test
		testImplementation(rootProject.libs.spring.boot.starter.test)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}