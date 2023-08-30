package com.cache

import androidx.room.TypeConverter
import com.data.model.ResponseNews
import com.google.gson.Gson


class TypeConvertorCache {
    private val gson = Gson()

    @TypeConverter
    fun convertString(data: ResponseNews): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun convertApi(str: String): ResponseNews {
        return gson.fromJson(str, ResponseNews::class.java)
    }
}