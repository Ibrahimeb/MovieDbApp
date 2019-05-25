package com.ibrahim.moviedbapp.home.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    companion object {
        fun newInstance(parent: ViewGroup) = MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))
    }

    fun bindViewHolder(item:ResultsItem , listener:MovieAdapter.Listener){

        itemView.tvTitle.text = item.title
        itemView.tvRate.text = item.voteAverage.toString()
        itemView.tvYear.text = Utils.getYear(Utils.parseStringDate(item.releaseDate!!)).toString()

        Picasso.get().load(Utils.getImageUrlLarge(item.posterPath!!)).placeholder(R.drawable.placeholder_movie).fit().into(itemView.ivItemMovie)



    }

}