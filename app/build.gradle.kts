plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
 //   kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.compamy.onestep"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.compamy.onestep"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    val room_version="2.6.1"
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
   // kapt("androidx.room:room-compiler:$room_version")

    val hilt_version="2.50"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    //kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    val androidTestDagger="2.37"
    androidTestImplementation("com.google.dagger:hilt-android-testing:$androidTestDagger")
    //kaptAndroidTest("com.google.dagger:hilt-android-compiler:$androidTestDagger")

    val nav_version = "2.8.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    val material_verison="1.7.5"
    implementation("androidx.compose.material:material:$material_verison")




    implementation("io.coil-kt.coil3:coil-compose:3.0.2")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.2")

    val cameraxVersion = "1.3.1"
    implementation ("androidx.camera:camera-core:${cameraxVersion}")
    implementation ("androidx.camera:camera-camera2:${cameraxVersion}")
    implementation ("androidx.camera:camera-view:${cameraxVersion}")
    implementation ("androidx.camera:camera-lifecycle:$cameraxVersion")

    val material3_version ="1.1.0"
    implementation("androidx.compose.material3:material3:$material3_version")
    val constraintLayout_version ="1.0.1"
    implementation ("androidx.constraintlayout:constraintlayout-compose:$constraintLayout_version")



    val jUnitVersion ="4.13.2"
    testImplementation("junit:junit:$jUnitVersion")

    val robolectricVersion ="1.6.1"
    testImplementation("androidx.test:core:$robolectricVersion")

    val mockitoFrameworkVersion ="5.14.2"
    testImplementation("org.mockito:mockito-core:$mockitoFrameworkVersion")


    val mockitoKotlinVersion ="5.4.0"
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")

    val mockkVersion ="1.13.13"
    testImplementation("io.mockk:mockk:$mockkVersion")

    implementation("com.google.accompanist:accompanist-navigation-animation:0.36.0")





}