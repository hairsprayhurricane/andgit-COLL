package com.example.savch_andgit.music.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM tracks ORDER BY name ASC")
    fun getAll(): Flow<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TrackEntity)

    @Delete
    suspend fun delete(entity: TrackEntity)

    @Query("SELECT * FROM tracks WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): TrackEntity?
}
