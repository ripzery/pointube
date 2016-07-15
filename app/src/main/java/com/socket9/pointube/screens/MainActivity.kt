package com.socket9.pointube.screens

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.about.AboutFragment
import com.socket9.pointube.screens.home.HomeFragment
import com.socket9.pointube.screens.point.PointFragment
import com.socket9.pointube.screens.promotion.main.PromotionFragment
import com.socket9.pointube.screens.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {

    lateinit private var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerToggle = setupDrawerToggle()
        drawerLayout.addDrawerListener(drawerToggle)

        setupToolbar(showHamburger = true)
        setupDrawerContent()
        initInstance()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle.onOptionsItemSelected(item!!)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupDrawerToggle() : ActionBarDrawerToggle {
        return ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    private fun setupDrawerContent(){
        nvView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem){

        var fragment: Fragment? = null

        when(menuItem.itemId){
            R.id.nav_home_fragment -> {
                fragment = HomeFragment.newInstance("Home")
            }
            R.id.nav_point_fragment -> {
                fragment = PointFragment.newInstance("Point")
            }
            R.id.nav_promotion_fragment -> {
                fragment = PromotionFragment.newInstance("Promotion")
            }
            R.id.nav_setting_fragment -> {
                fragment = SettingFragment.newInstance("Setting")

            }
            R.id.nav_about_fragment -> {
                fragment = AboutFragment.newInstance("Home")
            }
        }

        replaceFragment(fragment = fragment!!)

        menuItem.isCheckable = true
        title = menuItem.title
        drawerLayout.closeDrawers()
    }

    private fun initInstance() {
        replaceFragment(fragment = HomeFragment.newInstance("Home"))
    }
}
