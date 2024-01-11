buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath ("com.google.gms:google-services:4.3.10")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}
allprojects{
    repositories{
        google()
        jcenter()
        maven{
            url= uri("https://jitpack.io")
        }
    }
}