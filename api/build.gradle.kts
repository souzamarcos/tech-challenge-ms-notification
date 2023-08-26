dependencies {
    implementation(project(":domain"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.openapi)
    implementation(project(mapOf("path" to ":web")))
}