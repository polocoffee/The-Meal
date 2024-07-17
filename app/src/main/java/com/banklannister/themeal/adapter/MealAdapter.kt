package com.banklannister.themeal.adapter

import com.banklannister.themeal.R
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.banklannister.themeal.databinding.ItemCategoryBinding
import com.banklannister.themeal.model.Category

class MealAdapter(private val onItemClick: (Category) -> Unit) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {


    private var categories: List<Category> = emptyList()


    inner class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onItemClick: (Category) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(category: Category) {
            binding.categoryName.text = category.strCategory
            binding.categoryImage.load(category.strCategoryThumb) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }

            binding.root.setOnClickListener { onItemClick(category) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(inflater, onItemClick)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategories(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }


}