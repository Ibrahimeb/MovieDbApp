package com.ibrahim.moviedbapp.home.movie.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem

class MovieAdapter(val mListener : Listener) : RecyclerView.Adapter<MovieViewHolder>() {
    private val list = mutableListOf<ResultsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder.newInstance(parent)


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindViewHolder(list[holder.adapterPosition],mListener)
    }

    fun setListItem(list: List<ResultsItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener{
        fun gotoDetails()
    }

}