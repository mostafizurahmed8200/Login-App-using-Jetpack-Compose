package com.ahmed.login_jetpack_compose.di

import android.content.Context
import androidx.room.Room
import com.ahmed.login_jetpack_compose.data.local.AppDatabase
import com.ahmed.login_jetpack_compose.data.local.dao.LoginDao
import com.ahmed.login_jetpack_compose.data.local.dao.UserDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLoginDao(appDatabase: AppDatabase): LoginDao = appDatabase.loginDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDataDao = appDatabase.userDataDao()


}