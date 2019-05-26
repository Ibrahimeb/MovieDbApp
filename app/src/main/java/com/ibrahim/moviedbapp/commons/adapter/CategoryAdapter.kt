package com.ibrahim.moviedbapp.commons.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.commons.models.GenresItem

class CategoryAdapter(val itemList:List<GenresItem>, val listener:Listener ) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder = CategoryViewHolder.newInstance(parent)

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindViewHolder(itemList[holder.adapterPosition],listener)
    }

    interface Listener{
        fun filterBy(id: Int, isCheked: Boolean)
    }


}