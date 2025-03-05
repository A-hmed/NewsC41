package com.route.newsc41.domain.di.modules

import android.content.Context
import androidx.room.Room
import com.route.newsc41.data.database.MyDatabase
import com.route.newsc41.data.database.dao.SourcesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    fun provideDao(dataBase: MyDatabase): SourcesDao {
        return dataBase.getSourcesDao()
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            MyDatabase.TABLE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}