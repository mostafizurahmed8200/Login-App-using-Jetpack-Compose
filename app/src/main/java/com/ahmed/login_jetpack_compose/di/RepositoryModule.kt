package com.ahmed.login_jetpack_compose.di

import com.ahmed.login_jetpack_compose.domain.repository.LoginRepository
import com.ahmed.login_jetpack_compose.domain.repository.LoginRepositoryImpl
import com.ahmed.login_jetpack_compose.domain.repository.UserDataRepository
import com.ahmed.login_jetpack_compose.domain.repository.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindUserDataRepository(userDataRepositoryImpl: UserDataRepositoryImpl)
    : UserDataRepository
}