package com.example.my_cop_final
import java.io.Serializable

data class NutritionItem(
    val name: String,
    val calories: Int,
    val proteins: Int,
    val carbs: Int,
    val fats: Int
) : Serializable

class User(
    val name: String,
    var calories: Int = 0,
    var proteins: Int = 0,
    var carbs: Int = 0,
    var fats: Int = 0,
    val items: MutableList<NutritionItem> = mutableListOf()
) {
    fun addItem(item: NutritionItem) {
        items.add(item)
        calories += item.calories
        proteins += item.proteins
        carbs += item.carbs
        fats += item.fats
    }

    fun removeItem(item: NutritionItem) {
        if (items.remove(item)) {
            calories -= item.calories
            proteins -= item.proteins
            carbs -= item.carbs
            fats -= item.fats
        }
    }
}
