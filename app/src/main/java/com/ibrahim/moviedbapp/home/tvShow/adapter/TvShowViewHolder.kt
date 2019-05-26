package com.ibrahim.moviedbapp.home.tvShow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItemTv
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movie.view.*

class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    companion object{
        fun newInstance(parent: ViewGroup) = TvShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))
    }

    fun bindViewHolder(itemTv:ResultsItemTv, listener: TvShowAdapter.Listener){

        itemView.tvTitle.text = itemTv.originalName
        itemView.tvRate.text = itemTv.voteAverage.toString()
        itemView.tvYear.text = Utils.getYear(Utils.parseStringDate(itemTv.firstAirDate!!)).toString()

        Picasso.get().load(Utils.getImageUrlLarge(itemTv.posterPath!!)).placeholder(R.drawable.placeholder_movie).fit().into(itemView.ivItemMovie)

        itemView.setOnClickListener { listener.goToDetails(itemTv) }

    }
}