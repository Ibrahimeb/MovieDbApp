package com.ibrahim.moviedbapp.home.movie.ui

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.adapter.CategoryAdapter
import com.ibrahim.moviedbapp.commons.enums.BundlesKey
import com.ibrahim.moviedbapp.commons.enums.TypeScreen
import com.ibrahim.moviedbapp.commons.models.GenresItem
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.home.movie.adapter.MovieAdapter
import com.ibrahim.moviedbapp.home.movie.di.HomeModule
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import com.ibrahim.moviedbapp.home.movie.mvp.HomeContract
import com.ibrahim.moviedbapp.home.movie.mvp.HomePresenter
import kotlinx.android.synthetic.main.fragment_popular.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject


class MovieFragment : Fragment(), HomeContract.View, MovieAdapter.Listener,CategoryAdapter.Listener {
    private var listener: OnFragmentInteractionListener? = null
    private val TAG = MovieFragment::class.java.simpleName
    private var zipAux:ResponseMovie?=null
    private var categoryVisible = false

    private val movieAdapter by lazy { MovieAdapter(this) }

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
        zipAux = zip.popularList
        listener?.setZipModel(zip)
    }

    override fun succesfullSetupDrawerImage(zip:ZipMovie?) {
        zip?.popularList!!.results?.get(Utils.getRamdonInt(zip.popularList.results!!.size).toInt())
            ?.backdropPath?.let {
            listener?.setupImageDrawer(it)
        }
    }

    private fun showFilterCategory(visibility: Int){
        Log.i(TAG, "showFilterCategory: isvisible --> $categoryVisible ")
        rv_filter_movie?.visibility = visibility
    }

    override fun succesfullValidateTypeScreen(zip:ZipMovie?) {
        when(typeScreen){
            TypeScreen.TO_RATE.name -> setupRv(zip?.topRateList?.results!!)
            TypeScreen.UPCOMING.name -> setupRv(zip?.upComingList?.results!!)
            else -> setupRv(zip?.popularList?.results!!)
        }
    }

    override fun succesfullSetCategory(zip: ZipMovie?) {
        zip?.categoryMovie?.genres?.let { setupRvCateg(it) }
    }

    private val component by lazy { App.get().component.plus(HomeModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setHasOptionsMenu(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter -> {
                categoryVisible = if (!categoryVisible){
                    showFilterCategory(View.VISIBLE)
                    true
                } else{
                    showFilterCategory(View.GONE)
                    false
                }
            }
        }
        return super.onOptionsItemSelected(item)
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
        val category = arguments?.getParcelable<ResponseCategory>(BundlesKey.ARG_LIST_CATEGORY.name)
        typeScreen = arguments?.getString(BundlesKey.ARG_TYPE_SCREEN_MOVIE.name).toString()

        if (typeScreen == "null")
            typeScreen = TypeScreen.POPULAR.name


        if (data == null)
            presenter.getMovie()
        else{
            zipAux = data
            setupRv(data.results!!)
            setupRvCateg(category?.genres!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }

    fun setupRv(list: List<ResultsItem>) {
        rv_popular_movie?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_popular_movie?.isNestedScrollingEnabled = false
        rv_popular_movie?.setHasFixedSize(true)
        movieAdapter.setListItem(list)
        rv_popular_movie?.adapter = movieAdapter
    }

    fun setupRvCateg(list: List<GenresItem>) {
        rv_filter_movie?.layoutManager = GridLayoutManager(requireContext(),3)
        rv_filter_movie?.isNestedScrollingEnabled = false
        rv_filter_movie?.setHasFixedSize(true)
        rv_filter_movie?.adapter = CategoryAdapter(list,this)
    }

    private val itemFilter = mutableListOf<Int>()
    override fun filterBy(id: Int, isCheked: Boolean) {
        if (isCheked){
            itemFilter.add(id)
        }else
            itemFilter.remove(id)
        Log.i(TAG, "filterBy: --> $typeScreen ")
        when(typeScreen){
            TypeScreen.TO_RATE.name -> presenter.filterListMovie(itemFilter,zipAux?.results!!)
            TypeScreen.UPCOMING.name ->presenter.filterListMovie(itemFilter,zipAux?.results!!)
            else -> presenter.filterListMovie(itemFilter,zipAux?.results!!)
        }

    }

    override fun updateAdapter(list: List<ResultsItem>) {
        movieAdapter.setListItem(list)
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
