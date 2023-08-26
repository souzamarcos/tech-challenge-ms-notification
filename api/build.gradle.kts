dependencies {
    implementation(project(":domain"))
    implementation(project(":controller"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.openapi)
}