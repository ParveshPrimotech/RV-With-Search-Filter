package com.example.myapp.application

import android.content.Context
import com.example.myapp.ui.main.home.CryptoCurrencyRepoImpl
import com.example.myapp.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideCryptoCurrencyRepository(): CryptoRepository = CryptoCurrencyRepoImpl()


}