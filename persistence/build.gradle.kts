dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))

    implementation(rootProject.libs.flyway.core)
    implementation(rootProject.libs.flyway.mysql)
    implementation(rootProject.libs.spring.boot.starter.data.jpa)
//    implementation(rootProject.libs.hibernate)
    runtimeOnly(rootProject.libs.mysql.connector)
}