package ru.teledoc.studio.mealdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_category.view.*
import ru.teledoc.studio.mealdb.retrofit.models.Category

class MealCategoryAdapter(private val listener: MealCategoryListener? = null) : RecyclerView.Adapter<MealCategoryAdapter.MealCategoryViewHolder>() {

    var categories: List<Category> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

    override fun getItemCount() =
        categories.count()

    override fun onBindViewHolder(holder: MealCategoryViewHolder, position: Int) =
        holder.build(categories[position])

    override fun onViewAttachedToWindow(holder: MealCategoryViewHolder) {
        super.onViewAttachedToWindow(holder)

        listener?.let { lst ->
            holder.itemView.setOnClickListener {
                lst.onClick(categories[holder.adapterPosition].title)
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: MealCategoryViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    class MealCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun build(category: Category): Unit = with(itemView) {
            title.text = category.title

            Glide
                .with(this)
                .load(category.thumb)
                .into(thumbs as AppCompatImageView)
        }
    }

    interface MealCategoryListener {
        fun onClick(id: String)
    }
}