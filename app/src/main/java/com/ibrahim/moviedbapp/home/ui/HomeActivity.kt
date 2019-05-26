package com.ibrahim.moviedbapp.home.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.enums.BundlesKey
import com.ibrahim.moviedbapp.commons.enums.TypeScreen
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import com.ibrahim.moviedbapp.home.movie.ui.MovieFragment
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv
import com.ibrahim.moviedbapp.home.tvShow.ui.TvFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MovieFragment.OnFragmentInteractionListener,TvFragment.OnFragmentInteractionListener {



    private val TAG: String = HomeActivity::class.java.simpleName
    private var zipMovie: ZipMovie?=null
    private var zipTvShow: ZipModelTv?=null
    private var typeView = TypeScreen.MOVIE.name

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.container_home)
    }

    override fun setZipModel(zip: ZipMovie) {
        this.zipMovie = zip
    }

    override fun setZipModel(zip: ZipModelTv) {
        this.zipTvShow = zip
    }



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val bundle = Bundle()
        when (item.itemId) {
            R.id.nav_popular -> {

                bundle.putString(
                    BundlesKey.ARG_TYPE_SCREEN.name,
                    TypeScreen.POPULAR.name)





                when(typeView){
                    TypeScreen.MOVIE.name ->{
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipMovie?.popularList)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipMovie?.categoryMovie)
                        navController.navigate(R.id.popularFragment,bundle)
                    }
                    TypeScreen.TV_SHOW.name -> {
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipTvShow?.popularResponse)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipTvShow?.categoryTvShow)
                        navController.navigate(R.id.tvFragment,bundle)
                    }
                }



                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_up_coming -> {

                bundle.putString(
                    BundlesKey.ARG_TYPE_SCREEN.name,
                    TypeScreen.UPCOMING.name)

                when(typeView){
                    TypeScreen.MOVIE.name ->{
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipMovie?.upComingList)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipMovie?.categoryMovie)
                        navController.navigate(R.id.popularFragment,bundle)
                    }
                    TypeScreen.TV_SHOW.name -> {
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipTvShow?.lastedResponse)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipTvShow?.categoryTvShow)
                        navController.navigate(R.id.tvFragment,bundle)
                    }


                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_top_rate -> {

                bundle.putString(
                    BundlesKey.ARG_TYPE_SCREEN.name,
                    TypeScreen.TO_RATE.name)


                when(typeView){
                    TypeScreen.MOVIE.name ->{
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipMovie?.topRateList)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipMovie?.categoryMovie)
                        navController.navigate(R.id.popularFragment,bundle)
                    }
                    TypeScreen.TV_SHOW.name -> {
                        bundle.putParcelable(BundlesKey.ARG_ITEM_MOVIE.name,zipTvShow?.topRateResponse)
                        bundle.putParcelable(BundlesKey.ARG_LIST_CATEGORY.name,zipTvShow?.categoryTvShow)
                        navController.navigate(R.id.tvFragment,bundle)
                    }


                }

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupView()

    }


    fun setupView() {
        setupDrawer()
        setupNavigationButton()
    }
    fun setupNavigationButton(){
        bottom_navigation.selectedItemId = R.id.nav_popular
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    fun setupDrawer() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun setupImageDrawer(path: String) {
        Log.i(TAG, "setupImageDrawer: $path")
        Picasso.get().load(Utils.getImageUrlLarge(path)).placeholder(R.drawable.placeholder).fit().into(iv_header_nav)
    }




    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movie -> {
                typeView = TypeScreen.MOVIE.name
                bottom_navigation.selectedItemId = R.id.nav_popular
                navController.navigate(R.id.popularFragment)
            }
            R.id.nav_tv -> {
                typeView = TypeScreen.TV_SHOW.name
                bottom_navigation.selectedItemId = R.id.nav_popular
                navController.navigate(R.id.tvFragment)
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
