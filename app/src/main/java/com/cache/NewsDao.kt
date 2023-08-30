package com.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.data.model.ResponseNews
import com.utils.ENTITY_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveNews(entity: NewsEntity)

    @Query("SELECT * FROM $ENTITY_TABLE")
    fun getAllNews():Flow<List<NewsEntity>>
}