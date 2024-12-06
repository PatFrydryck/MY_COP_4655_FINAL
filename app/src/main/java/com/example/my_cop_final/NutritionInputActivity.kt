package com.example.my_cop_final

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NutritionInputActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_input_activity)

        val nameInput: EditText = findViewById(R.id.nameInput)
        val caloriesInput: EditText = findViewById(R.id.caloriesInput)
        val proteinsInput: EditText = findViewById(R.id.proteinsInput)
        val carbsInput: EditText = findViewById(R.id.carbsInput)
        val fatsInput: EditText = findViewById(R.id.fatsInput)
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val calories = caloriesInput.text.toString().toIntOrNull() ?: 0
            val proteins = proteinsInput.text.toString().toIntOrNull() ?: 0
            val carbs = carbsInput.text.toString().toIntOrNull() ?: 0
            val fats = fatsInput.text.toString().toIntOrNull() ?: 0

            val nutritionItem = NutritionItem(name, calories, proteins, carbs, fats)

            // Pass the data back to MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("nutritionItem", nutritionItem)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
