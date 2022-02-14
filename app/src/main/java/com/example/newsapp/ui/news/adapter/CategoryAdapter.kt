package com.example.newsapp.ui.news.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CategoryItemLayoutBinding
import com.example.newsapp.ui.news.adapter.model.Category
import com.example.newsapp.util.ItemsDiffUtil

class CategoryAdapter(private val onCategoryItemListener: OnCategoryItemListener) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(ItemsDiffUtil<Category>()) {

    private var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
        with(holder.binding) {
            categoryTextView.setOnClickListener {
                selectedPosition = holder.adapterPosition
                onCategoryItemListener.onCategoryItemClick(getItem(position).category)
                notifyDataSetChanged()
            }
            if (holder.adapterPosition == selectedPosition) {
                categoryTextView.setTextColor(Color.parseColor("#FF4B5C"))
                categoryTextView.paint.isUnderlineText = true
            } else {
                categoryTextView.setTextColor(Color.parseColor("#5E5C5B"))
                categoryTextView.paint.isUnderlineText = false
            }
        }
    }

    class ViewHolder(val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(category: Category) {
            with(binding) {
                categoryTextView.text = category.category
            }
        }
    }
}