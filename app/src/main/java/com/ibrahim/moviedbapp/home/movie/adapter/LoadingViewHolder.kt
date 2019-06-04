package com.ibrahim.moviedbapp.home.movie.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.inflaterView

class LoadingViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
       fun newInstaces(parent:ViewGroup) = LoadingViewHolder(parent.inflaterView(R.layout.footer_loading_rv))
    }

    fun bindViewHolder(){

    }


}