package com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cache.NewsEntity
import com.data.NewsRepository
import com.data.model.ResponseNews
import com.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository):ViewModel(){

    fun newsApi():HashMap<String,String>{
        val queries:HashMap<String,String> = HashMap()
        queries[API_KEY]= MY_API_KEY
        queries[SORT_BY]= POPULARITY
        queries["q"]="apple"
        queries["from"]= "2023-08-26"
        queries["to"]= "2023-08-26"
        return queries
    }

    val newsData=MutableLiveData<MyResponse<ResponseNews>>()

    fun loadNews(queries:Map<String,String>)=viewModelScope.launch {
        newsData.value=MyResponse.Loading()
        val response=repository.getNews(queries)
        newsData.value=BaseResponse(response).generateApi()
        val cache=newsData.value?.data
        if (cache != null) {
            exCache(cache)
        }
    }

    //Local
    private fun saveCache(entity: NewsEntity)=viewModelScope.launch {
        repository.saveNews(entity)
    }
    private fun exCache(response:ResponseNews)=viewModelScope.launch {
        val entity=NewsEntity(response)
         saveCache(entity)
    }
    val readCache=repository.getAllNews
}