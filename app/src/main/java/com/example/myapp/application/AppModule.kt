package com.example.myapp.application

import com.example.myapp.ui.main.CryptoCurrencyRepoImpl
import com.example.myapp.repository.CryptocurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideCryptoCurrencyRepository(): CryptocurrencyRepository = CryptoCurrencyRepoImpl()
}