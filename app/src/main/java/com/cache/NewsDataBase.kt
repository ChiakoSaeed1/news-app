package com.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NewsEntity::class], version = 9, exportSchema = false)
@TypeConverters(TypeConvertorCache::class)
abstract class NewsDataBase :RoomDatabase(){
    abstract fun dao():NewsDao
}