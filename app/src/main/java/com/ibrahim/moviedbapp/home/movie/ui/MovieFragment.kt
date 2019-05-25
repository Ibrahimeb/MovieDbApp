package com.ibrahim.moviedbapp.home.movie.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.enums.BundlesKey
import com.ibrahim.moviedbapp.commons.enums.TypeScreen
import com.ibrahim.moviedbapp.home.movie.adapter.MovieAdapter
import com.ibrahim.moviedbapp.home.movie.di.HomeModule
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import com.ibrahim.moviedbapp.home.movie.mvp.movie.HomeContract
import com.ibrahim.moviedbapp.home.movie.mvp.HomePresenter
import kotlinx.android.synthetic.main.fragment_popular.*
import javax.inject.Inject


class MovieFragment : Fragment(), HomeContract.View, MovieAdapter.Listener {
    private var listener: OnFragmentInteractionListener? = null
    private val TAG = MovieFragment::class.java.simpleName

    @Inject
    lateinit var presenter: HomePresenter


    private lateinit var typeScreen:String


    override fun showProgress(isShow: Boolean) {
        mainProgress?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Utils.makeToast(getString(msg), requireContext())
    }

    override fun succesfullSetZipModel(zip: ZipMovie?) {
        if (zip==null)return
        listener?.setZipModel(zip)
    }

    override fun succesfullSetupDrawerImage(zip:ZipMovie?) {
        zip?.popularList!!.results?.get(Utils.getRamdonInt(zip.popularList.results!!.size).toInt())
            ?.backdropPath?.let {
            listener?.setupImageDrawer(it)
        }
    }

    override fun succesfullValidateTypeScreen(zip:ZipMovie?) {
        when(typeScreen){
            TypeScreen.TO_RATE.name -> setupRv(zip?.topRateList?.results!!)
            TypeScreen.UPCOMING.name -> setupRv(zip?.upComingList?.results!!)
            else -> setupRv(zip?.popularList?.results!!)
        }
    }

    private val component by lazy { App.get().component.plus(HomeModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<ResponseMovie>(BundlesKey.ARG_ITEM_MOVIE.name)
        typeScreen = arguments?.getString(BundlesKey.ARG_TYPE_SCREEN_MOVIE.name).toString()

        if (typeScreen == "null")
            typeScreen = TypeScreen.POPULAR.name


        if (data == null)
            presenter.getMovie()
        else
            setupRv(data.results!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }

    fun setupRv(list: List<ResultsItem>) {
        rv_popular_movie?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_popular_movie?.isNestedScrollingEnabled = false
        rv_popular_movie?.setHasFixedSize(true)
        rv_popular_movie?.adapter = MovieAdapter(list, this)
    }

    override fun gotoDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
        fun setupImageDrawer(path: String)
        fun setZipModel(zip: ZipMovie)

    }



}
