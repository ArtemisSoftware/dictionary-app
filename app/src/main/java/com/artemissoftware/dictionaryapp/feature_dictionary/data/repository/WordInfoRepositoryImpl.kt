package com.artemissoftware.dictionaryapp.feature_dictionary.data.repository

import com.artemissoftware.dictionaryapp.feature_dictionary.core.util.Resource
import com.artemissoftware.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.artemissoftware.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {

        emit(Resource.Loading())

        //Send the cached data to the ui while still loading

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word }) //remove existing words from cache
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() }) //add new words to cache

        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = wordInfos
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}