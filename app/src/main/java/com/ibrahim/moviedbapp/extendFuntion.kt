package com.ibrahim.moviedbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflaterView(@LayoutRes layout:Int, attachToRoot: Boolean = false): View = LayoutInflater.from(this.context).inflate(layout , this, attachToRoot)