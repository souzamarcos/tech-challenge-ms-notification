dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))
    implementation(project(":controller"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.openapi)
}