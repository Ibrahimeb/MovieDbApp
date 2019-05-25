package com.ibrahim.moviedbapp.home.di

import com.ibrahim.moviedbapp.home.ui.fragment.MovieFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent (modules = [HomeModule::class])
interface HomeComponent {
    fun inject(popularFragment: MovieFragment)
}