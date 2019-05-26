package com.ibrahim.moviedbapp.details.di

import com.ibrahim.moviedbapp.details.api.DetailsApi
import com.ibrahim.moviedbapp.details.mvp.DetailsContract
import com.ibrahim.moviedbapp.details.mvp.DetailsModel
import com.ibrahim.moviedbapp.details.mvp.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailsModule(val view: DetailsContract.View) {

    @Provides
    @DetailScope
    fun providerView() = view

    @Provides
    @DetailScope
    fun providesModel(api:DetailsApi) = DetailsModel(api)

    @Provides
    @DetailScope
    fun providesPresenter(view: DetailsContract.View,model: DetailsModel) = DetailsPresenter(view,model)

}