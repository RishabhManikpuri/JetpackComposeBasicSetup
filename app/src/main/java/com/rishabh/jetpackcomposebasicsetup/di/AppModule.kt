package com.rishabh.jetpackcomposebasicsetup.di

import android.content.Context
import androidx.room.Room
import com.rishabh.jetpackcomposebasicsetup.data.local.DummyDatabase
import com.rishabh.jetpackcomposebasicsetup.data.remote.DummyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): DummyDatabase {
        return Room.databaseBuilder(
            context,
            DummyDatabase::class.java,
            "dummy.db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideBeerApi(): DummyApi {
//        return Retrofit.Builder()
//            .baseUrl(DummyApi.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create()
//    }

}