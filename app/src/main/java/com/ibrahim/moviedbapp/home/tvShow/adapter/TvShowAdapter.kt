package com.ibrahim.moviedbapp.home.tvShow.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItem

class TvShowAdapter(val listItem:List<ResultsItem>,val listener:Listener) : RecyclerView.Adapter<TvShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder = TvShowViewHolder.newInstance(parent)


    override fun getItemCount(): Int =listItem.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindViewHolder(listItem[holder.adapterPosition],listener)
    }

    interface Listener{
        fun goToDetails()
    }


}