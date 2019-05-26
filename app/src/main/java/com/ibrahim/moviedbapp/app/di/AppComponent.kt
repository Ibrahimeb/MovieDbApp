package com.ibrahim.moviedbapp.app.di

import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.app.network.ApiModule
import com.ibrahim.moviedbapp.details.di.DetailsComponent
import com.ibrahim.moviedbapp.details.di.DetailsModule
import com.ibrahim.moviedbapp.home.movie.di.HomeComponent
import com.ibrahim.moviedbapp.home.movie.di.HomeModule
import com.ibrahim.moviedbapp.home.tvShow.di.TvShowComponent
import com.ibrahim.moviedbapp.home.tvShow.di.TvshowModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(app: App)

    fun plus(homeModule: HomeModule): HomeComponent

    fun plus(tvShowmodule: TvshowModule): TvShowComponent

    fun plus(detailsModule: DetailsModule):DetailsComponent

}