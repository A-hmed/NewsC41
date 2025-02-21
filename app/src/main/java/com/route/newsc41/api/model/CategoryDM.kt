package com.route.newsc41.api.model

import com.route.newsc41.R

data class CategoryDM(val id: String, val imageId: Int) {
    companion object {
        val categories = mutableListOf(
            CategoryDM(id = "general", imageId = R.drawable.general),
            CategoryDM(id = "business", imageId = R.drawable.business),
            CategoryDM(id = "sports", imageId = R.drawable.sport),

            CategoryDM(id = "health", imageId = R.drawable.health),
            CategoryDM(id = "entertainment", imageId = R.drawable.entertainment),

            CategoryDM(id = "technology", imageId = R.drawable.technology),
            CategoryDM(id = "science", imageId = R.drawable.science),
        )
    }
}