package com.enescanpolat.sozluk.data.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.enescanpolat.sozluk.data.local.Converters
import com.enescanpolat.sozluk.data.local.WordInfoDao
import com.enescanpolat.sozluk.data.local.WordInfoDatabase
import com.enescanpolat.sozluk.data.remote.SozlukApi
import com.enescanpolat.sozluk.data.repository.WordInfoRepositoryImpl
import com.enescanpolat.sozluk.domain.repository.WordInfoRepository
import com.enescanpolat.sozluk.domain.use_case.GetWordInfoUseCase
import com.enescanpolat.sozluk.util.Constants
import com.enescanpolat.sozluk.util.Constants.BASE_URL
import com.enescanpolat.sozluk.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectGetWordInfoUseCase(repository: WordInfoRepository):GetWordInfoUseCase{
        return GetWordInfoUseCase(repository)
    }


    @Singleton
    @Provides
    fun injectWordInfoRepository(db:WordInfoDatabase,api:SozlukApi):WordInfoRepository{
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Singleton
    @Provides
    fun injectWordInfoDatabase(app:Application):WordInfoDatabase{
        return Room.databaseBuilder(
            app,WordInfoDatabase::class.java,"word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    @Singleton
    @Provides
    fun injectSozlukApi():SozlukApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SozlukApi::class.java)
    }

}