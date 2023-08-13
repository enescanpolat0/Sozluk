package com.enescanpolat.sozluk.data.repository

import com.enescanpolat.sozluk.data.local.WordInfoDao
import com.enescanpolat.sozluk.data.remote.SozlukApi
import com.enescanpolat.sozluk.domain.model.WordInfo
import com.enescanpolat.sozluk.domain.repository.WordInfoRepository
import com.enescanpolat.sozluk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(private val api:SozlukApi,private val dao: WordInfoDao):WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{

        emit(Resource.Loading())

        val wordInfos = dao.getWordInfo(word).map {
            it.toWordInfo()
        }

        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos=api.getWordInfo(word)
            dao.deleteinfos(remoteWordInfos.map { it.word })
            dao.insertAllinfos(remoteWordInfos.map { it.toWordInfoEntity() })


        }catch (e: HttpException){
            emit(Resource.Error(message = "Birseyler Yanlis Gitti", data = wordInfos))
        }catch (e: IOException){
            emit(Resource.Error(message = "Sunucuya Bağlanılamadı Lutfen Internet Baglantinizi Kontrol Ediniz", data = wordInfos))
        }

        val newWordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}