object JetbrainsConfig {

    private object Version {
        const val KOTLIN_GRADLE_PLUGIN = "1.7.20"
        const val KOTLINX_COROUTINES_CORE = "1.6.0"
        const val KOTLINX_COROUTINES_ANDROID = "1.6.0"
    }

    const val KOTLIN_GRADLE_PLUGIN_CLASSPATH =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN_GRADLE_PLUGIN}"
    const val KOTLIN_STDLIB_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.KOTLIN_GRADLE_PLUGIN}"

    const val KOTLINX_COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.KOTLINX_COROUTINES_CORE}"
    const val KOTLINX_COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.KOTLINX_COROUTINES_ANDROID}"

}

