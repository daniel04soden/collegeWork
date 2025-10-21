package com.example.fitnesslogger.Model

class Goal {
    var goalNature: String
    val startingWeight: Double
    var GoalWeight
    var maintenanceCalories: Double
    var dietCalories: Double
    var nutrition: Nutrition // Nutrion object, general storage of fat,carbs and protein

    constructor(
        goalNature: String
        startingWeight: Double
        GoalWeight
        maintenanceCalories: Double
        dietCalories: Double
        nutrition: Nutrition 
    ){
        this.goalNature = goalNature
        this.startingWeight = startingWeight 
        this.GoalWeight = GoalWeight 
        this.maintenanceCalories = maintenanceCalories 
        this.dietCalories =  dietCalories 
        this.nutrition = nutrition 
    }
}
