package com.rishabh.jetpackcomposebasicsetup.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface DummyDao {
    @Upsert
    suspend fun upsertAll(beers: List<DummyEntity>)

    @Query("SELECT * FROM dummyentity")
    fun pagingSource(): Map<Int, DummyEntity>

    @Query("DELETE FROM dummyentity")
    suspend fun clearAll()
}