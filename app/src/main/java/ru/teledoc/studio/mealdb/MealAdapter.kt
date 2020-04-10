package ru.teledoc.studio.mealdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_meat.view.*
import ru.teledoc.studio.mealdb.retrofit.models.Meal

class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    var meals: List<Meal> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meat, parent, false)
        )

    override fun getItemCount() =
        meals.count()

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(meals[position])


    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meal: Meal): Unit = with(itemView) {
            title.text = meal.title

            Glide
                .with(this)
                .load(meal.thumbs)
                .into(thumbs as AppCompatImageView)
        }
    }
}