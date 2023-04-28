plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":shared:domain:movie"))
    implementation(project(":shared:domain:actor"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax)
}
