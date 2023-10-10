object Google {

    private object Version {
        const val HITL = "2.43.2"
        const val SERVICES_CLASSPATH = "4.3.14"
        const val MATERIAL = "1.8.0-rc01"
        const val RATE_US_CORE = "1.10.3"
        const val RATE_US_CORE_KTS = "1.8.1"
        const val BASEMENT = "18.2.0"
    }

    const val HILT_GRADLE_PLUGIN_CLASSPATH =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.HITL}"
    const val HILT_HILT = "com.google.dagger:hilt-android:${Version.HITL}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HITL}"

    const val SERVICES_CLASSPATH = "com.google.gms:google-services:${Version.SERVICES_CLASSPATH}"

    const val MATERIAL_VALUE = "material"
    const val MATERIAL_DIR = "com.google.android.$MATERIAL_VALUE"
    const val MATERIAL = "$MATERIAL_DIR:$MATERIAL_VALUE:${Version.MATERIAL}"

    const val RATE_US_CORE = "com.google.android.play:core:${Version.RATE_US_CORE}"
    const val RATE_US_CORE_KTS = "com.google.android.play:core-ktx:${Version.RATE_US_CORE_KTS}"

}