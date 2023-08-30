package com.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.model.ResponseNews
import com.utils.ENTITY_TABLE

@Entity(tableName = ENTITY_TABLE)
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    var response:ResponseNews
)
