package com.ibrahim.moviedbapp.home.tvShow.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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
import com.ibrahim.moviedbapp.home.tvShow.adapter.TvShowAdapter
import com.ibrahim.moviedbapp.home.tvShow.di.TvshowModule
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItem
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowContract
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowPresenter
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_tv.*
import kotlinx.android.synthetic.main.fragment_tv.mainProgress
import javax.inject.Inject


class TvFragment : Fragment(), TvShowContract.View,TvShowAdapter.Listener,CategoryAdapter.Listener {


    @Inject
    lateinit var presenter:TvShowPresenter
    private val component by lazy { App.get().component.plus(TvshowModule(this)) }
    val TAG = TvFragment::class.java.simpleName
    private var zipAux:ResponseTvShow?=null
    private var categoryVisible = false


    val adapter by lazy { TvShowAdapter(this) }


    override fun goToDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var listener: OnFragmentInteractionListener? = null


    private lateinit var typeScreen:String

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

    private fun showFilterCategory(visible: Int) {
        Log.i(TAG, "showFilterCategory: categoryVisible --> $categoryVisible ");
        rv_filter_tv.visibility = visible
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<ResponseTvShow>(BundlesKey.ARG_ITEM_MOVIE.name)
        val category = arguments?.getParcelable<ResponseCategory>(BundlesKey.ARG_LIST_CATEGORY.name)
        typeScreen = arguments?.getString(BundlesKey.ARG_TYPE_SCREEN_MOVIE.name).toString()

        if (typeScreen == "null")
            typeScreen = TypeScreen.POPULAR.name


        if (data == null)
            presenter.getTvShow()
        else{
            zipAux = data
            setupRv(data.results!!)
            setupRvCateg(category?.genres!!)
        }
    }

    fun setupRv(list: List<ResultsItem>) {
        rv_tv_show?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_tv_show?.isNestedScrollingEnabled = false
        rv_tv_show?.setHasFixedSize(true)
        adapter.setListItem(list)
        rv_tv_show?.adapter = adapter
    }

    fun setupRvCateg(list: List<GenresItem>) {
        rv_filter_tv?.layoutManager = GridLayoutManager(requireContext(),3)
        rv_filter_tv?.isNestedScrollingEnabled = false
        rv_filter_tv?.setHasFixedSize(true)
        rv_filter_tv?.adapter = CategoryAdapter(list,this)
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


    override fun succesfullSetZipModel(zip: ZipModelTv?) {
        zipAux = zip?.popularResponse
        listener?.setZipModel(zip!!)
    }

    override fun succesfullSetupDrawerImage(zip: ZipModelTv?) {
        zip?.topRateResponse!!.results?.get(Utils.getRamdonInt(zip.topRateResponse.results!!.size).toInt())
            ?.backdropPath?.let {
            listener?.setupImageDrawer(it)
        }
    }

    override fun succesfullValidateTypeScreen(zip: ZipModelTv?) {
        when(typeScreen){
            TypeScreen.TO_RATE.name -> setupRv(zip?.topRateResponse?.results!!)
            TypeScreen.UPCOMING.name -> setupRv(zip?.lastedResponse?.results!!)
            else -> setupRv(zip?.popularResponse?.results!!)
        }

    }


    override fun succesfullSetCategory(zip: ZipModelTv?) {
        zip?.categoryTvShow?.genres?.let { setupRvCateg(it) }
    }

    override fun updateAdapter(list: List<ResultsItem>) {
        adapter.setListItem(list)
    }

    private val itemFilter = mutableListOf<Int>()
    override fun filterBy(id: Int, isCheked: Boolean) {
        if (isCheked){
            itemFilter.add(id)
        }else
            itemFilter.remove(id)
        Log.i(TAG, "filterBy: --> $typeScreen ")
        when(typeScreen){
            TypeScreen.TO_RATE.name -> presenter.filterListTvShow(itemFilter,zipAux?.results!!)
            TypeScreen.UPCOMING.name ->presenter.filterListTvShow(itemFilter,zipAux?.results!!)
            else -> presenter.filterListTvShow(itemFilter,zipAux?.results!!)
        }
    }

    override fun showProgress(isShow: Boolean) {
        mainProgress.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Utils.makeToast(getString(msg), requireContext())
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
        fun setupImageDrawer(path: String)
        fun setZipModel(zip: ZipModelTv)

    }

}
