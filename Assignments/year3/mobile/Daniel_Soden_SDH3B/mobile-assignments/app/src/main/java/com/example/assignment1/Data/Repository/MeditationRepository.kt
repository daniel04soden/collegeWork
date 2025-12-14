package com.example.assignment1.Data.Repository

import com.example.assignment1.Data.MeditationDao
import com.example.assignment1.Models.MeditationVideo
import kotlinx.coroutines.flow.Flow

class MeditationRepository(private val meditationDao: MeditationDao) {

    fun getAllMeditations(): Flow<List<MeditationVideo>> = meditationDao.getAllMeditations()

    suspend fun insertAll(meditations: List<MeditationVideo>) {
        meditationDao.insertAll(meditations)
    }

    fun getRandomMeditation(): Flow<MeditationVideo> = meditationDao.getRandomMeditation()

    suspend fun deleteAll() {
        meditationDao.deleteAll()
    }
}