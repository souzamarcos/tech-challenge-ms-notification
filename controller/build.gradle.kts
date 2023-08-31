dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))
    implementation(project(":gateway"))

    implementation(rootProject.libs.spring.beans)
    implementation(rootProject.libs.openapi)
}

