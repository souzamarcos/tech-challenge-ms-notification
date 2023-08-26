dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.openapi)
}

