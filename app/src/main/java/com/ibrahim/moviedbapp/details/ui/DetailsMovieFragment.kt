package com.ibrahim.moviedbapp.details.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.enums.BundlesKey
import com.ibrahim.moviedbapp.commons.enums.TypeScreen
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.details.di.DetailsModule
import com.ibrahim.moviedbapp.details.mvp.DetailsContract
import com.ibrahim.moviedbapp.details.mvp.DetailsPresenter
import com.ibrahim.moviedbapp.home.movie.adapter.MovieAdapter
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.tvShow.adapter.TvShowAdapter
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItemTv
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details_movie.*
import javax.inject.Inject


class DetailsMovieFragment : Fragment(),DetailsContract.View,MovieAdapter.Listener,TvShowAdapter.Listener {
    private var listener: OnFragmentInteractionListener? = null
    lateinit var typeScreen:String
    val adapterMovie by lazy { MovieAdapter(this)}
    val adapterTv by  lazy { TvShowAdapter(this) }
    lateinit var responseMovie: ResultsItem
    lateinit var responseTvShow: ResultsItemTv
    var responseCategory: ResponseCategory?=null
    val component by lazy { App.get().component.plus(DetailsModule(this)) }
    @Inject
    lateinit var presenter: DetailsPresenter



    override fun gotoDetails(item: ResultsItem) {
        val bundle = Bundle()
        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,item)
        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,responseCategory)
        bundle.putString(BundlesKey.ARG_TYPE_SCREEN.name,TypeScreen.MOVIE.name)
        findNavController().navigate(R.id.action_detailsMovieFragment_self,bundle)
    }

    override fun goToDetails(item: ResultsItemTv) {
        val bundle = Bundle()
        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,item)
        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,responseCategory)
        bundle.putString(BundlesKey.ARG_TYPE_SCREEN.name,TypeScreen.TV_SHOW.name)

        findNavController().navigate(R.id.action_detailsMovieFragment_self,bundle)
    }

    override fun succesfullSimilarMovie(responseMovie: ResponseMovie) {
       adapterMovie.setListItem(responseMovie.results!!)
        setupRv()
    }

    override fun succesfullSimilarTv(responseTvShow: ResponseTvShow) {
        adapterTv.setListItem(responseTvShow.results!!)
        setupRv()
    }

    override fun setGenrsList(string: String) {
        tvGeners.text = string
    }

    override fun showProgress(isShow: Boolean) {
        progressBarDetails.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Utils.makeToast(getString(msg),requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        typeScreen = arguments?.getString(BundlesKey.ARG_TYPE_SCREEN.name) ?: "null"
        responseCategory = arguments?.getParcelable(BundlesKey.ARG_LIST_CATEGORY.name)


        if (typeScreen == TypeScreen.MOVIE.name){
            responseMovie = arguments?.getParcelable(BundlesKey.ARG_ITEM_MOVIE.name)!!
        }else
            responseTvShow = arguments?.getParcelable(BundlesKey.ARG_ITEM_MOVIE.name)!!
        setupView()


    }

    fun setupView(){
        if (typeScreen == TypeScreen.MOVIE.name){
            presenter.getSimilarMovie(responseMovie.id.toString())
            Picasso.get().load(Utils.getImageUrlLarge(responseMovie.posterPath!!)).placeholder(R.drawable.placeholder_movie).fit().into(ivPoster)
            tvTitleDetails.text = responseMovie.title
            presenter.getGeners(responseMovie.genreIds!!,responseCategory!!)
            tvRate.text = responseMovie.voteAverage.toString()
            tvOverride.text = responseMovie.overview
            tvDate.text = responseMovie.releaseDate
        }else{
            presenter.getSimilarTv(responseTvShow.id.toString())
            Picasso.get().load(Utils.getImageUrlLarge(responseTvShow.posterPath!!)).placeholder(R.drawable.placeholder_movie).fit().into(ivPoster)
            tvTitleDetails.text = responseTvShow.originalName
            presenter.getGeners(responseTvShow.genreIds!!,responseCategory!!)
            tvRate.text = responseTvShow.voteAverage.toString()
            tvOverride.text = responseTvShow.overview
            tvDate.text = responseTvShow.firstAirDate
        }


    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException("$context must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null

    }


    fun setupRv() {
        rv_similar?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_similar?.isNestedScrollingEnabled = false
        rv_similar?.setHasFixedSize(false)
        rv_similar?.adapter  = if (typeScreen == TypeScreen.MOVIE.name) adapterMovie else adapterTv
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
