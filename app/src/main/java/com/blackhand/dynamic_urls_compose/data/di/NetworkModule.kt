package com.blackhand.dynamic_urls_compose.data.di

import com.blackhand.dynamic_urls_compose.core.common.Constant.CONVERSATIONS_BASE_URL
import com.blackhand.dynamic_urls_compose.core.common.Constant.MOVIE_BASE_URL
import com.blackhand.dynamic_urls_compose.core.common.Constant.USERS_BASE_URL
import com.blackhand.dynamic_urls_compose.core.util.url.ConversationsUrl
import com.blackhand.dynamic_urls_compose.core.util.url.MovieUrl
import com.blackhand.dynamic_urls_compose.core.util.url.UsersUrl
import com.blackhand.dynamic_urls_compose.data.interceptor.ConversationsTokenInterceptor
import com.blackhand.dynamic_urls_compose.data.interceptor.MovieTokenInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    // conversations
    @Provides
    @Singleton
    fun provideTokenInterceptor(): ConversationsTokenInterceptor {
        return ConversationsTokenInterceptor()
    }

    @Provides
    @ConversationsUrl
    fun provideConversationsOkHttp(tokenInterceptor: ConversationsTokenInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(tokenInterceptor)
            .build()


    @Provides
    @ConversationsUrl
    fun provideConversationsRetrofit(
        @ConversationsUrl client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .baseUrl(CONVERSATIONS_BASE_URL)
            .build()
    }

    //users
    @Provides
    @UsersUrl
    fun provideUsersOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(30, TimeUnit.MINUTES)
            .build()

    @Provides
    @UsersUrl
    fun provideUsersRetrofit(
        @UsersUrl client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .baseUrl(USERS_BASE_URL)
            .build()
    }

    //movie
    @Provides
    @Singleton
    fun provideMovieInterceptor(): Interceptor {
        return MovieTokenInterceptor()
    }

    @Provides
    @MovieUrl
    fun provideMovieOkHttp(movieTokenInterceptor: MovieTokenInterceptor): OkHttpClient =
        OkHttpClient
            .Builder().addInterceptor(movieTokenInterceptor)
            .readTimeout(30, TimeUnit.MINUTES)
            .build()

    @Provides
    @MovieUrl
    fun provideMovieRetrofit(
        @MovieUrl client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .baseUrl(MOVIE_BASE_URL)
            .build()
    }
}