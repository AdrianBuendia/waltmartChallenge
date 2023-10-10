object AndroidX {

    private object Version {

        const val APP_COMPAT = "1.5.1"
        const val CORE = "1.9.0"
        const val CONSTRAINT_LAYOUT = "2.1.4"
        const val LIVE_CYCLE = "2.5.1"
        const val FRAGMENT = "1.5.5"
        const val CAMERA = "1.3.0-alpha02"
        const val NAVIGATION_GRADLE_PLUGIN = "2.5.3"
        const val NAVIGATION = "2.5.3"
        const val LIFECYCLE = "2.5.1"
        const val LIFECYCLE_EXTENSIONS = "2.2.0"

        const val LEGACY_SUPPORT = "1.0.0"
        const val BIOMETRIC = "1.1.0"
        const val ROOM = "2.5.0"
    }

    const val ANDROID_JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"

    const val CORE = "androidx.core:core-ktx:${Version.CORE}"

    const val CONSTRAINT_LAYOUT_VALUE = "constraintlayout"

    const val CONSTRAINT_LAYOUT_ANDROIDX_DIR = "androidx.$CONSTRAINT_LAYOUT_VALUE"

    const val CONSTRAINT_LAYOUT =
        "$CONSTRAINT_LAYOUT_ANDROIDX_DIR:$CONSTRAINT_LAYOUT_VALUE:${Version.CONSTRAINT_LAYOUT}"

    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIVE_CYCLE}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIVE_CYCLE}"
    const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIVE_CYCLE}"
    const val SAVEDSTATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.LIVE_CYCLE}"

    const val FRAGMENT_KTS = "androidx.fragment:fragment-ktx:${Version.FRAGMENT}"

    const val CAMERA_CORE = "androidx.camera:camera-core:${Version.CAMERA}"
    const val CAMERA_CAMERA = "androidx.camera:camera-camera2:${Version.CAMERA}"
    const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${Version.CAMERA}"
    const val CAMERA_VIEW = "androidx.camera:camera-view:${Version.CAMERA}"

    const val NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN_CLASSPATH =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION_GRADLE_PLUGIN}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"

    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${Version.LIFECYCLE}"
    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Version.LIFECYCLE}"

    const val LIFECYCLE_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${Version.LIFECYCLE_EXTENSIONS}"

    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Version.LEGACY_SUPPORT}"

    const val BIOMETRIC = "androidx.biometric:biometric:${Version.BIOMETRIC}"



    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"

    //annotationProcessor "androidx.room:room-compiler:$room_version"

    const val ROOM_COMPILER_KAPT = "androidx.room:room-compiler:${Version.ROOM}"
    // To use Kotlin annotation processing tool (kapt)
        // kapt "androidx.room:room-compiler:$room_version"
    // To use Kotlin Symbol Processing (KSP)
    //ksp "androidx.room:room-compiler:$room_version"
    // optional - Guava support for Room, including Optional and ListenableFuture
    //implementation "androidx.room:room-guava:$room_version"


}