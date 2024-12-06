package com.example.my_cop_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionItemAdapter(private val items: List<NutritionItem>) :
    RecyclerView.Adapter<NutritionItemAdapter.NutritionItemViewHolder>() {

    // ViewHolder for each item
    inner class NutritionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemCalories: TextView = itemView.findViewById(R.id.itemCalories)
        val itemProteins: TextView = itemView.findViewById(R.id.itemProteins)
        val itemCarbs: TextView = itemView.findViewById(R.id.itemCarbs)
        val itemFats: TextView = itemView.findViewById(R.id.itemFats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_item, parent, false)
        return NutritionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutritionItemViewHolder, position: Int) {
        val nutritionItem = items[position]

        // Set item name and calories
        holder.itemName.text = nutritionItem.name
        holder.itemCalories.text = " - ${nutritionItem.calories} Calories"

        // Set macros (Proteins, Carbs, Fats)
        holder.itemProteins.text = "Proteins: ${nutritionItem.proteins}g - "
        holder.itemCarbs.text = "Carbs: ${nutritionItem.carbs}g - "
        holder.itemFats.text = "Fats: ${nutritionItem.fats}g"
    }

    override fun getItemCount(): Int = items.size
}
