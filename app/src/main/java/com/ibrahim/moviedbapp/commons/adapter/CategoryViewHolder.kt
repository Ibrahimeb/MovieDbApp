package com.ibrahim.moviedbapp.commons.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.models.GenresItem
import com.ibrahim.moviedbapp.inflaterView
import kotlinx.android.synthetic.main.item_filter_categorys.view.*

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val TAG = CategoryViewHolder::class.java.simpleName

    companion object {
        fun newInstance(parent: ViewGroup) = CategoryViewHolder(parent.inflaterView(R.layout.item_filter_categorys))
    }

    fun bindViewHolder(item:GenresItem,listener:CategoryAdapter.Listener){
        itemView.chip_category.text = item.name

       itemView.chip_category.setOnCheckedChangeListener {
               _, isCheked -> listener.filterBy(item.id!!,isCheked); Log.i(TAG, "bindViewHolder : setOnFocusChangeListener id=${item.id}  status=$isCheked")

       }

    }

}



