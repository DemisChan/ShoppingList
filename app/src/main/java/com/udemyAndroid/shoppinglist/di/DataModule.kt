package com.udemyAndroid.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.udemyAndroid.shoppinglist.data.AppDatabase
import com.udemyAndroid.shoppinglist.data.ShopListDao
import com.udemyAndroid.shoppinglist.data.ShopListRepositoryImpl
import com.udemyAndroid.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "shop_item.db"
            ).build()

        }

        @Provides
        fun provideShopListDao(appDatabase: AppDatabase): ShopListDao {
            return appDatabase.shopListDao()
        }
    }
}
    