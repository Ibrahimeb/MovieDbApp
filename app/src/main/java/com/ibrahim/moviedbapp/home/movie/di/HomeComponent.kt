package com.ibrahim.moviedbapp.home.movie.di

import com.ibrahim.moviedbapp.home.movie.ui.MovieFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent (modules = [HomeModule::class])
interface HomeComponent {
    fun inject(movieFragment: MovieFragment)
}