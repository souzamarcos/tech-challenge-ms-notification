dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))
    implementation(project(":gateway"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.openapi)
}

