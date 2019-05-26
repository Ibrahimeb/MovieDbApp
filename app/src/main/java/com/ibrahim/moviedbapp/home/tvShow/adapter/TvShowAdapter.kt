package com.ibrahim.moviedbapp.home.tvShow.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItem

class TvShowAdapter(val listener:Listener) : RecyclerView.Adapter<TvShowViewHolder>() {
    private val list = mutableListOf<ResultsItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder = TvShowViewHolder.newInstance(parent)


    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindViewHolder(list[holder.adapterPosition],listener)
    }

    fun setListItem(list: List<ResultsItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    interface Listener{
        fun goToDetails()
    }


}