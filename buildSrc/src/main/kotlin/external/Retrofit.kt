package external

object Retrofit {
    private object Version {
        const val CORE = "2.9.0"
        const val CONNECTION = "4.9.3"
    }

    const val RETROFIT_RETROFIT = "com.squareup.retrofit2:retrofit:${Version.CORE}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.CORE}"

    const val URL_CONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${Version.CONNECTION}"
    const val INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.CONNECTION}"

}