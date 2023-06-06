plugins {
    application
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

application {
    mainClass.set("com.fiap.burger.application.boot.BurgerApplication")
}

dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))
    implementation(project(":web"))
    implementation(project(":persistence"))
}