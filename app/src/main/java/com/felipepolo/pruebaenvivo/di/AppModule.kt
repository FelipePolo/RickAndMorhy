package com.felipepolo.pruebaenvivo.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.felipepolo.pruebaenvivo.AppConstants.BASE_RICK_AND_MORTHY_URL
import com.felipepolo.pruebaenvivo.AppConstants.DATA_BASE_NAME
import com.felipepolo.pruebaenvivo.data.local.AppDataBase
import com.felipepolo.pruebaenvivo.data.local.CharactersDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
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
    fun provideFireBaseauth(): FirebaseAuth {
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideRoomInstance(context: Context): AppDataBase {
        return Room.databaseBuilder(context,AppDataBase::class.java,DATA_BASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDataBase: AppDataBase): CharactersDao {
        return appDataBase.charactersDao()
    }
}

