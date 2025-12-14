package com.example.assignment1.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment1.Models.MeditationVideo
import kotlinx.coroutines.flow.Flow

@Dao
interface MeditationDao {

    @Query("SELECT * FROM meditation")
    fun getAllMeditations(): Flow<List<MeditationVideo>>

    @Query("DELETE FROM meditation")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertAll(meditations: List<MeditationVideo>)

    @Query("SELECT * FROM meditation ORDER BY RANDOM() LIMIT 1")
    fun getRandomMeditation(): Flow<MeditationVideo>

}