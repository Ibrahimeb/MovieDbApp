package com.ibrahim.moviedbapp.home.movie.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem

class MovieAdapter(val list: List<ResultsItem>,val mListener : Listener) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder.newInstance(parent)


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindViewHolder(list[holder.adapterPosition],mListener)
    }

    interface Listener{
        fun gotoDetails()
    }

}