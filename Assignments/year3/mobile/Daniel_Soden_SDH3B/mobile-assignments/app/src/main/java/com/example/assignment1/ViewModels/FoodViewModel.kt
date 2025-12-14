package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assignment1.Data.Repository.FoodRepository
import com.example.assignment1.Data.Repository.UserRepository
import com.example.assignment1.Models.Food
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class FoodViewModel(
    private val foodRepo: FoodRepository,
    private val userRepo: UserRepository,
    private val userViewModel: UserViewModel
) : ViewModel() {

    private val _calorieFilter = MutableStateFlow<Float?>(null)
    private val _searchQuery = MutableStateFlow<String?>("")
    private val _customFoodFilter = MutableStateFlow<Boolean?>(null)


    val itemsFlow: Flow<PagingData<Food>> = combine(
        _calorieFilter,
        _searchQuery,
        _customFoodFilter
    ) { calorieFilter, searchQuery, customFoodFilter ->
        Triple(calorieFilter, searchQuery, customFoodFilter)
    }.flatMapLatest { (calorieFilter, searchQuery, customFoodFilter) ->
        if (!searchQuery.isNullOrBlank()) {
            foodRepo.searchFoodByName(searchQuery)
        } else if (calorieFilter != null) {
            foodRepo.getFoodByCalories(calorieFilter)
        } else if (customFoodFilter != null) {
            foodRepo.getFoodByCustom(customFoodFilter)
        }
        else {
            foodRepo.getAllFood()
        }
    }.cachedIn(viewModelScope)

    fun setCalorieFilter(calorieFilter: Float?) {
        _calorieFilter.value = calorieFilter
    }

    fun setSearchQuery(query: String?) {
        _searchQuery.value = query
    }

    fun setCustomFoodFilter(isCustom: Boolean?) {
        _customFoodFilter.value = isCustom
    }

    fun addFoodToUser(food: Food) {
        viewModelScope.launch {
            userViewModel.currentUser.value?.let { currentUser ->
                val today = LocalDate.now()
                val lastFoodDate = currentUser.lastFoodEntryDate?.let { LocalDate.parse(it) }

                var newStreak = currentUser.calorieStreak ?: 0

                if (lastFoodDate == null || lastFoodDate.isBefore(today.minusDays(1))) {
                    newStreak = 1
                } else if (lastFoodDate.isEqual(today.minusDays(1))) {
                    newStreak++
                }

                val updatedUser = currentUser.copy(
                    caloriesConsumedToday = currentUser.caloriesConsumedToday + food.energy100G,
                    proteinConsumedToday = currentUser.proteinConsumedToday + food.proteinOneHundredG,
                    lastFoodEntryDate = today.toString(),
                    calorieStreak = newStreak
                )
                userViewModel.updateUser(updatedUser)
            }
        }
    }

    fun addNewFood(food: Food) {
        viewModelScope.launch {
            foodRepo.insertFood(food)
        }
    }
}