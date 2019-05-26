package com.ibrahim.moviedbapp.home.tvShow.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItemTv
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowContract
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowPresenter
import kotlinx.android.synthetic.main.fragment_tv.*
import kotlinx.android.synthetic.main.fragment_tv.mainProgress
import javax.inject.Inject
import android.view.animation.Animation
import android.view.View.MeasureSpec
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.View.MeasureSpec.makeMeasureSpec
import android.view.View.MeasureSpec.EXACTLY
import android.opengl.ETC1.getWidth
import android.app.ActionBar
import android.view.animation.Transformation
import androidx.core.content.ContextCompat
import android.widget.LinearLayout






class TvFragment : Fragment(), TvShowContract.View,TvShowAdapter.Listener,CategoryAdapter.Listener {


    private var category:ResponseCategory?=null
    @Inject
    lateinit var presenter:TvShowPresenter
    private val component by lazy { App.get().component.plus(TvshowModule(this)) }
    val TAG = TvFragment::class.java.simpleName
    private var zipAux:ResponseTvShow?=null
    private var categoryVisible = false


    val adapter by lazy { TvShowAdapter(this) }


    private fun expand(v: View) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    LinearLayout.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()

//                if (interpolatedTime == 1f) {
//                   // llContentLogin.setBackgroundColor(ContextCompat.getColor(context!!, R.color.t))
//                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = ((targetHeight / v.context.resources.displayMetrics.density).toInt()).toLong()
        v.startAnimation(a)
    }


    private fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                    rv_filter_tv.setBackgroundColor(ContextCompat.getColor(context!!, android.R.color.transparent))

                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = ((initialHeight / v.context.resources.displayMetrics.density).toInt()).toLong()
        v.startAnimation(a)
    }


    override fun goToDetails(item:ResultsItemTv) {
        val bundle = Bundle()
        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,item)
        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,category)
        bundle.putString(BundlesKey.ARG_TYPE_SCREEN.name,TypeScreen.TV_SHOW.name)

        findNavController().navigate(R.id.action_tvFragment_to_detailsMovieFragment,bundle)
    }

    private var listener: OnFragmentInteractionListener? = null


    private lateinit var typeScreen:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter -> {
                categoryVisible = if (!categoryVisible){
                    expand(rv_filter_tv)
                    true
                } else{
                  collapse(rv_filter_tv)
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
        category = arguments?.getParcelable(BundlesKey.ARG_LIST_CATEGORY.name)
        typeScreen = arguments?.getString(BundlesKey.ARG_TYPE_SCREEN.name).toString()

        if (typeScreen == "null")
            typeScreen = TypeScreen.POPULAR.name

        Log.i(TAG, "onViewCreated: ---> $data")
        if (data == null)
            presenter.getTvShow()
        else{
            zipAux = data
            setupRv(data.results!!)
            setupRvCateg(category?.genres!!)
        }
    }

    fun setupRv(list: List<ResultsItemTv>) {
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
        presenter.onDetach()
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
        Log.i(TAG, "succesfullValidateTypeScreen:  --> $typeScreen");
        when(typeScreen){
            TypeScreen.TO_RATE.name -> setupRv(zip?.topRateResponse?.results!!)
            TypeScreen.UPCOMING.name -> setupRv(zip?.lastedResponse?.results!!)
            else -> setupRv(zip?.popularResponse?.results!!)
        }

    }


    override fun succesfullSetCategory(zip: ZipModelTv?) {
        category=zip?.categoryTvShow
        zip?.categoryTvShow?.genres?.let { setupRvCateg(it) }
    }

    override fun updateAdapter(list: List<ResultsItemTv>) {
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
