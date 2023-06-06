dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))
    implementation(project(":persistence"))

    implementation(rootProject.libs.spring.boot.starter.web)
}

