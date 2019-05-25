package com.ibrahim.moviedbapp.home.tvShow.di

import com.ibrahim.moviedbapp.home.tvShow.ui.TvFragment
import dagger.Module
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvshowModule::class])
interface TvShowComponent {
    fun inject(tvFragment: TvFragment)
}