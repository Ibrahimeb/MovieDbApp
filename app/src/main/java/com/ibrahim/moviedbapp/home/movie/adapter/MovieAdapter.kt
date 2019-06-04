package com.ibrahim.moviedbapp.home.movie.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import android.graphics.Movie
import java.nio.file.Files.size


class MovieAdapter(val mListener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isLoadingAdded: Boolean = false
    private val list = mutableListOf<ResultsItem>()

    companion object {
        const val ITEM = 1
        const val LOADING = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == ITEM) MovieViewHolder.newInstance(parent) else LoadingViewHolder.newInstaces(parent)


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM)
            (holder as MovieViewHolder).bindViewHolder(list[holder.adapterPosition], mListener)
        else
            (holder as LoadingViewHolder).bindViewHolder()
    }

    override fun getItemViewType(position: Int) = if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM

    fun setListItem(list: List<ResultsItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    fun add(mc: ResultsItem) {
        list.add(mc)
        notifyItemInserted(list.size - 1)
    }

    fun addAll(mcList: List<ResultsItem>) {
        addAll(mcList)
    }

    private fun remove(item: ResultsItem) {
        val position = list.indexOf(item)
        if (position > -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(ResultsItem())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = list.size - 1
        list.removeAt(position)
        notifyItemRemoved(position)

    }

    private fun getItem(position: Int): ResultsItem {
        return list[position]
    }


    interface Listener {
        fun gotoDetails(item: ResultsItem)
    }

}