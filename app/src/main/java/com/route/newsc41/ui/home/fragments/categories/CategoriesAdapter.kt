package com.route.newsc41.ui.home.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.newsc41.api.model.CategoryDM
import com.route.newsc41.databinding.ItemCategoryLeftSidedButtonBinding
import com.route.newsc41.databinding.ItemCategoryRightSidedButtonBinding

class CategoriesAdapter(
    val categories: List<CategoryDM>,
    val onCategoryClick: (CategoryDM) -> Unit
) : Adapter<ViewHolder>() {
    companion object {
        const val RIGHT_BUTTON_CATEGORY_TYPE = 1
        const val LEFT_BUTTON_CATEGORY_TYPE = 2
    }

    override fun getItemViewType(position: Int): Int =
        if (position % 2 == 0) RIGHT_BUTTON_CATEGORY_TYPE else
            LEFT_BUTTON_CATEGORY_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == RIGHT_BUTTON_CATEGORY_TYPE) {
            val binding = ItemCategoryRightSidedButtonBinding.inflate(inflater, parent, false)
            RightCategoryVH(binding)
        } else {
            val binding = ItemCategoryLeftSidedButtonBinding.inflate(inflater, parent, false)
            LeftCategoryVH(binding)
        }
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.itemView.setOnClickListener {
            onCategoryClick(category)
        }
        if (holder is RightCategoryVH) {
            holder.binding.articleImage.setImageResource(category.imageId)
        } else {
            holder as LeftCategoryVH
            holder.binding.articleImage.setImageResource(category.imageId)
        }
    }

    class RightCategoryVH(val binding: ItemCategoryRightSidedButtonBinding) :
        ViewHolder(binding.root)

    class LeftCategoryVH(val binding: ItemCategoryLeftSidedButtonBinding) :
        ViewHolder(binding.root)


}