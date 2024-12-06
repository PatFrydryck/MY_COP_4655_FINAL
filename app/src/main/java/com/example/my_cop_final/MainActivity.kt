package com.example.my_cop_final

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val nutritionRequestCode = 1 // Request code for launching the input activity
    private lateinit var user: User // Declare the User object at the class level
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NutritionItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the user object once here
        user = User(name = "Patrick Frydryck")

        val dateTextView: TextView = findViewById(R.id.dateTextView)

        // Format the current date
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        // Set the formatted date to the TextView
        dateTextView.text = formattedDate

        // Find TextViews for the user name and stats
        val caloriesText: TextView = findViewById(R.id.caloriesText)
        val proteinsAmount: TextView = findViewById(R.id.proteinsAmount)
        val carbsAmount: TextView = findViewById(R.id.carbsAmount)
        val fatsAmount: TextView = findViewById(R.id.fatsAmount)

        // Update the UI with the current user values
        caloriesText.text = getString(R.string.calories, user.calories)
        proteinsAmount.text = user.proteins.toString()
        carbsAmount.text = user.carbs.toString()
        fatsAmount.text = user.fats.toString()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.nutritionListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NutritionItemAdapter(user.items)  // Initialize the adapter with user's items
        recyclerView.adapter = adapter

        // Find the button to navigate to the input screen
        val addAppleButton: Button = findViewById(R.id.addAppleButton)
        addAppleButton.setOnClickListener {
            val intent = Intent(this, NutritionInputActivity::class.java)
            startActivityForResult(intent, nutritionRequestCode)
        }
    }

    // Handle the result returned from NutritionInputActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == nutritionRequestCode && resultCode == RESULT_OK) {
            val nutritionItem = data?.getSerializableExtra("nutritionItem") as? NutritionItem

            nutritionItem?.let {
                // Add the new item to the existing user's list
                user.addItem(it)

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged()

                // Update the UI with the updated user values
                val caloriesText: TextView = findViewById(R.id.caloriesText)
                val proteinsAmount: TextView = findViewById(R.id.proteinsAmount)
                val carbsAmount: TextView = findViewById(R.id.carbsAmount)
                val fatsAmount: TextView = findViewById(R.id.fatsAmount)

                caloriesText.text = getString(R.string.calories, user.calories)
                proteinsAmount.text = user.proteins.toString()
                carbsAmount.text = user.carbs.toString()
                fatsAmount.text = user.fats.toString()
            }
        }
    }
}