package com.utils.di

import android.content.Context
import androidx.room.Room
import com.cache.NewsDataBase
import com.utils.DATABASE_TABLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDB {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context)=Room.databaseBuilder(
        context,NewsDataBase::class.java, DATABASE_TABLE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:NewsDataBase)=db.dao()


}