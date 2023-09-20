package com.zahran.movieapp.di.moviesmodule

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zahran.movieapp.data.source.remote.MovieApiService
import com.zahran.movieapp.data.source.remote.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
/**
 * This is a Hilt module called MoviesNetworkModule,
 * which is used for providing dependencies related to network communication with the MovieApiService.
 * The module is annotated with @Module and @InstallIn(SingletonComponent::class),
 * which means that it will be installed in the SingletonComponent and
 * has the scope of the entire application.

The module provides the following dependencies:

OkHttpClient:
 * This dependency is provided by a method called provideOkHttpClient,
 * which returns an instance of OkHttpClient
 * that is built with RequestInterceptor and HttpLoggingInterceptor.

MovieApiService:
 * This dependency is provided by a method called provideRetrofitService,
 * which takes an instance of OkHttpClient as a parameter
 * and returns an instance of MovieApiService.
 * This method builds a Retrofit instance using MoshiConverterFactory for JSON parsing
 * and the provided OkHttpClient, and creates a MovieApiService instance
 * using the Retrofit.create method.

The @Singleton annotation
 * is used on both provideOkHttpClient and provideRetrofitService methods,
 * which means that Hilt will only create one instance of each dependency
 * and provide it whenever it is needed.

By using these @Provides methods,
 * we can provide these dependencies to any component in our app
 * by simply annotating the constructor of that component with @Inject.

 */
@Module
@InstallIn(SingletonComponent::class)
class MoviesNetworkModule {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(RequestInterceptor()).addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): MovieApiService =
        Retrofit.Builder()
            .baseUrl(MovieApiService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
            .create(MovieApiService::class.java)
}