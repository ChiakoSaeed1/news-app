package com.data

import androidx.lifecycle.asLiveData
import com.cache.NewsDao
import com.cache.NewsEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepository @Inject constructor(private val apiServices: ApiServices,private val dao:NewsDao){
    //Api
    suspend fun getNews(queries:Map<String,String>)=apiServices.getNews(queries)
    //Local
    suspend fun saveNews(entity: NewsEntity)=dao.saveNews(entity)
    val getAllNews=dao.getAllNews().asLiveData()

}