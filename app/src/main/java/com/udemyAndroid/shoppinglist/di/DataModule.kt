package com.udemyAndroid.shoppinglist.di

import android.app.Application
import com.udemyAndroid.shoppinglist.data.AppDatabase
import com.udemyAndroid.shoppinglist.data.ShopListDao
import com.udemyAndroid.shoppinglist.data.ShopListRepositoryImpl
import com.udemyAndroid.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @Provides
        @Singleton
        fun providesDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        @Singleton
        fun provideShopListDao(appDatabase: AppDatabase): ShopListDao {
            return appDatabase.shopListDao()
        }
    }
}
    