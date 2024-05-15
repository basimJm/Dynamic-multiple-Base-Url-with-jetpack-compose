package com.blackhand.dynamic_urls_compose.data.di

import com.blackhand.dynamic_urls_compose.core.util.url.ConversationsUrl
import com.blackhand.dynamic_urls_compose.core.util.url.MovieUrl
import com.blackhand.dynamic_urls_compose.core.util.url.UsersUrl
import com.blackhand.dynamic_urls_compose.data.repo.ConversationsRepositoryImpl
import com.blackhand.dynamic_urls_compose.data.repo.MovieRepositoryImpl
import com.blackhand.dynamic_urls_compose.data.repo.UsersRepositoryImpl
import com.blackhand.dynamic_urls_compose.data.service.remote.ConversationsService
import com.blackhand.dynamic_urls_compose.data.service.remote.MovieService
import com.blackhand.dynamic_urls_compose.data.service.remote.UsersService
import com.blackhand.dynamic_urls_compose.domain.repo.ConversationsRepository
import com.blackhand.dynamic_urls_compose.domain.repo.MovieRepository
import com.blackhand.dynamic_urls_compose.domain.repo.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    // Conversations
    @Provides
    @ConversationsUrl
    fun provideConversationService(@ConversationsUrl retrofit: Retrofit): ConversationsService {
        return retrofit.create(ConversationsService::class.java)
    }

    @Provides
    @Singleton
    fun provideConversationsRepository(
        @ConversationsUrl conversationsService: ConversationsService
    ): ConversationsRepository {
        return ConversationsRepositoryImpl(conversationsService)
    }

    //Users
    @Provides
    @UsersUrl
    fun provideUsersService(@UsersUrl retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(@UsersUrl usersService: UsersService): UsersRepository {
        return UsersRepositoryImpl(usersService)
    }

    //Movie
    @Provides
    @Singleton
    fun provideMovieRepository( @MovieUrl movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Provides
    @MovieUrl
    fun provideMovieService(@MovieUrl retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

}