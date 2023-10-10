package com.goodaysolutions.waltmartchallenge.core.di.module

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.goodaysolutions.waltmartchallenge.BuildConfig
import com.goodaysolutions.waltmartchallenge.constants.AppDataBase
import com.goodaysolutions.waltmartchallenge.constants.IntervalTime
import com.goodaysolutions.waltmartchallenge.core.data.api.Api
import com.goodaysolutions.waltmartchallenge.core.data.local.AppDatabase
import com.goodaysolutions.waltmartchallenge.core.data.local.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptors(): Array<Interceptor> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        val customHeadersInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept", "*/*")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(newRequest)
        }

        val handshakeLoggerInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val handshake = response.handshake
            if (handshake != null && BuildConfig.DEBUG) {
                Log.d("SSLHandshake", "TLS:${handshake.tlsVersion}")
            }
            response
        }

        return arrayOf(loggingInterceptor, customHeadersInterceptor, handshakeLoggerInterceptor)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpInterceptors: Array<Interceptor>
    ): OkHttpClient {
        val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .build()

        val builder = OkHttpClient.Builder()
            .callTimeout(IntervalTime.TIME_130_S, TimeUnit.SECONDS)
            .connectTimeout(IntervalTime.TIME_130_S, TimeUnit.SECONDS)
            .readTimeout(IntervalTime.TIME_130_S, TimeUnit.SECONDS)
            .writeTimeout(IntervalTime.TIME_130_S, TimeUnit.SECONDS)
            .connectionSpecs(Collections.singletonList(connectionSpec))
        httpInterceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)


    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.item()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, AppDataBase.name)
        .build()

}