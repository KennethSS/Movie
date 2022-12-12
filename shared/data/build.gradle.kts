plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":shared:domain"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax)
}
