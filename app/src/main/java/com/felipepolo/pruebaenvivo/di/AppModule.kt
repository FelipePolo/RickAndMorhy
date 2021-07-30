package com.felipepolo.pruebaenvivo.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.felipepolo.pruebaenvivo.AppConstants.BASE_RICK_AND_MORTHY_URL
import com.felipepolo.pruebaenvivo.AppConstants.DATA_BASE_NAME
import com.felipepolo.pruebaenvivo.data.local.AppDataBase
import com.felipepolo.pruebaenvivo.data.local.models.ExampleDao
import com.felipepolo.pruebaenvivo.data.remote.ExampleWebService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }


    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(BASE_RICK_AND_MORTHY_URL)
            .okHttpClient(okhttp3.OkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideFireBaseOauth(): FirebaseAuth {
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://SomeBaseurl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideExampleWebService(retrofit: Retrofit): ExampleWebService {
        return retrofit.create(ExampleWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomInstance(context: Context): AppDataBase {
        return Room.databaseBuilder(context,AppDataBase::class.java,DATA_BASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideExampleDao(appDataBase: AppDataBase): ExampleDao {
        return appDataBase.exampleDao()
    }
}

