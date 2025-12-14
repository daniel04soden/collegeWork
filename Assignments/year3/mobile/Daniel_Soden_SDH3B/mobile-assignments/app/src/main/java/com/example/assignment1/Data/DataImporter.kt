package com.example.assignment1.Data

import android.content.Context
import android.util.Log
import com.example.assignment1.Models.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class DataImporter(private val context: Context, private val foodDao: FoodDao) {

    // Learned from https://medium.com/nerd-for-tech/android-export-and-import-data-9223c6bce8da
    private val jsonFile = "food.json"

    suspend fun importDataFromJson() {
        withContext(Dispatchers.IO) {
            Log.d("Import","Importing data from JSON")
            val count = foodDao.getFoodCount()
            Log.d("Import","Init food count: $count")
            if (count > 0) {
                println("Database already contains $count food items. Skipping JSON import.")
                return@withContext
            }

            try {
                Log.d("Import","Importing data from JSON")
                val inputStream = context.assets.open(jsonFile)
                val reader = InputStreamReader(inputStream)
                Log.d("Import","File opened")

                val listType = object : TypeToken<List<Food>>() {}.type
                val foodEntities: List<Food> = Gson().fromJson(reader, listType)

                reader.close()
                Log.d("Import","${foodEntities.size} items from JSON.")

                if (foodEntities.isNotEmpty()) {
                    foodDao.insertAll(foodEntities)
                    Log.d("Import","Successfully imported ${foodEntities.size} food items.")
                }

            } catch (e: Exception) {
                Log.d("Import","Import failed")
                e.printStackTrace()
                Log.d("Import","Error importing data: ${e.message}")
            }
        }
    }
}