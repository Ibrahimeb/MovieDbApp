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
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.home.models.ZipMovie
import com.ibrahim.moviedbapp.home.ui.fragment.MovieFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MovieFragment.OnFragmentInteractionListener {



    val TAG = HomeActivity::class.java.simpleName
    var fragmentActual: Fragment? = null
    lateinit var zip: ZipMovie

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.container_home)
    }

    override fun setZipModel(zip: ZipMovie) {
        this.zip = zip
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val bundle = Bundle()
        when (item.itemId) {
            R.id.nav_popular -> {
                bundle.putParcelable(MovieFragment.ARG_ITEM_MOVIE,zip.popularList)
                navController.navigate(R.id.popularFragment,bundle)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_up_coming -> {
                bundle.putParcelable(MovieFragment.ARG_ITEM_MOVIE,zip.upComingList)
                navController.navigate(R.id.popularFragment,bundle)


                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_top_rate -> {
                bundle.putParcelable(MovieFragment.ARG_ITEM_MOVIE, zip.topRateList)
                navController.navigate(R.id.popularFragment,bundle)
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


    override fun updateFragmentActual(fragment: Fragment) {
        fragmentActual = fragment
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movie -> {
                // Handle the camera action
            }
            R.id.nav_tv -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
