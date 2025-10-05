package com.kshitiz.samachar24.di

import android.content.Context
import androidx.room.Room
import com.kshitiz.samachar24.data.local.NewsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        NewsDb::class.java,
        "news_db"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: NewsDb) = db.newsDao()
}


