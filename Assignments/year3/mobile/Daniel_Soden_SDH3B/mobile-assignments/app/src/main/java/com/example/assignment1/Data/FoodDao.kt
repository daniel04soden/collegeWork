package com.example.assignment1.Data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assignment1.Models.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT COUNT(*) FROM food")
    suspend fun getFoodCount(): Int
    @Insert
    suspend fun insertFood(food: Food)
    @Insert
    suspend fun insertAll(foods: List<Food>)

    @Query("SELECT * FROM food")
    fun getAllFood(): PagingSource<Int, Food>

    @Query("SELECT * FROM food WHERE productName LIKE '%' || :query || '%'")
    fun searchFoodByName(query: String): PagingSource<Int, Food>

    @Query("SELECT * FROM food WHERE productName = :name")
    suspend fun getFoodByName(name: String): Food?

    @Query("SELECT * FROM food WHERE id = :id")
    suspend fun getFoodById(id: Int): Food

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("DELETE FROM food WHERE id = :id")
    suspend fun deleteFoodById(id :Long)

    @Query("SELECT * FROM food WHERE energy100G <= :calorieFilter")
    fun getFoodByCalories(calorieFilter: Float): PagingSource<Int, Food>

    @Query("SELECT * FROM food WHERE productName LIKE '%' || :customQuery || '%'")
    fun getFoodByCustom(customQuery: String): PagingSource<Int, Food>


}