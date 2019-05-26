package com.ibrahim.moviedbapp.details.di

import com.ibrahim.moviedbapp.details.ui.DetailsMovieFragment
import dagger.Component
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    fun inject(detailsMovieFragment: DetailsMovieFragment)


}