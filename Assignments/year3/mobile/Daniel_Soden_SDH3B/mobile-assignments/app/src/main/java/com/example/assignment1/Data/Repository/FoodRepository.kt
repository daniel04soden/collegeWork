package com.example.assignment1.Data.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.assignment1.Data.FoodDao
import com.example.assignment1.Models.Food
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val foodDao: FoodDao) {

    fun getAllFood(): Flow<PagingData<Food>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { foodDao.getAllFood() }
        ).flow // More complex but needed for loading large amount of data
    }

    fun getFoodByCalories(calorieFilter: Float): Flow<PagingData<Food>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { foodDao.getFoodByCalories(calorieFilter) }
        ).flow
    }

    fun searchFoodByName(query: String): Flow<PagingData<Food>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { foodDao.searchFoodByName(query) }
        ).flow
    }

    fun getFoodByCustom(isCustom: Boolean): Flow<PagingData<Food>> {
        val customQuery = if (isCustom) "Custom" else ""
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { foodDao.getFoodByCustom(customQuery) }
        ).flow
    }

    suspend fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(food)
    }

    suspend fun deleteFoodById(id:Long){
        foodDao.deleteFoodById(id)
    }

}