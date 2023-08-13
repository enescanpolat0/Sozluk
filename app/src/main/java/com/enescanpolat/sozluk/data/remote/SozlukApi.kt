package com.enescanpolat.sozluk.data.remote

import com.enescanpolat.sozluk.data.remote.dto.WordInfoDto
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface SozlukApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(@Path("word") word : String):List<WordInfoDto>

}