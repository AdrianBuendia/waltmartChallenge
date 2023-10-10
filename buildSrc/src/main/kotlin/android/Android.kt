object Android {

    private object Version {
        const val TOOLS_GRADLE = "7.3.1"
        const val TOOLS_BUNDLE = "1.8.2"
    }

    const val TOOLS_GRADLE_CLASSPATH = "com.android.tools.build:gradle:${Version.TOOLS_GRADLE}"
    const val TOOLS_BUNDLE_CLASSPATH = "com.android.tools.build:bundletool:${Version.TOOLS_BUNDLE}"
    //const val SUPPORT = "com.android.support:support-v4:21.0.3"
    const val SUPPORT = "com.android.support:design:${Release.BUILD_TOOLS_VERSION}"
}