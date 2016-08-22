package com.socket9.pointube.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.socket9.pointube.R
import com.socket9.pointube.extensions.replaceFragment
import com.socket9.pointube.extensions.setLocale
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.screens.about.AboutFragment
import com.socket9.pointube.screens.brand.SelectBrandActivity
import com.socket9.pointube.screens.home.HomeFragment
import com.socket9.pointube.screens.login.LoginActivity
import com.socket9.pointube.screens.point.PointFragment
import com.socket9.pointube.screens.promotion.list.any.ProgramListActivity
import com.socket9.pointube.screens.promotion.main.ExpandableListAdapter
import com.socket9.pointube.screens.promotion.main.PromotionFragment
import com.socket9.pointube.screens.register.RegisterActivity
import com.socket9.pointube.screens.setting.SettingFragment
import com.socket9.pointube.utils.DialogUtil
import com.socket9.pointube.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_nav_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import rx_activity_result.RxActivityResult
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity(), AnkoLogger, HomeFragment.OnLoginListener, SettingFragment.SettingListener, PromotionFragment.PromotionListener, FilteredBrandContract.View {

    companion object {
        val FRAGMENT_HOME = 0
        val FRAGMENT_POINT = 1
        val FRAGMENT_PROMOTION = 2
        val FRAGMENT_SETTING = 3
        val FRAGMENT_ABOUT = 4
    }

    lateinit private var drawerToggle: ActionBarDrawerToggle
    private var mExpandListAdapter: ExpandableListAdapter? = null
    private var mFilteredBrandPresenter: FilteredBrandContract.Presenter? = null
    private var mCurrentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerToggle = setupDrawerToggle()
        drawerLayout.addDrawerListener(drawerToggle)

        setupToolbar(showHamburger = true, isShowIcon = true)
        drawerToggle?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        setupDrawerContent()
        mFilteredBrandPresenter = FilteredBrandPresenter(this)
        mFilteredBrandPresenter?.onCreate()
        initInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle.onOptionsItemSelected(item!!)) {
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

    override fun onDestroy() {
        super.onDestroy()
        mFilteredBrandPresenter?.onDestroy()
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    private fun setupDrawerContent() {
        nvView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {

        var fragment: Fragment? = null

        when (menuItem.itemId) {
            R.id.nav_home_fragment -> {
                fragment = HomeFragment.newInstance("Home")
                setupToolbar(showHamburger = true, isShowIcon = true)
                mCurrentPosition = FRAGMENT_HOME
            }
            R.id.nav_point_fragment -> {
                fragment = PointFragment.newInstance("Point")
                setupToolbar(title = getString(R.string.point_text_title), showHamburger = true, isShowIcon = false)
                mCurrentPosition = FRAGMENT_POINT
            }
            R.id.nav_promotion_fragment -> {
                fragment = PromotionFragment.newInstance("Promotion")
                setupToolbar(title = getString(R.string.promotion_list_text_title), showHamburger = true, isShowIcon = false)
                mCurrentPosition = FRAGMENT_PROMOTION
            }
            R.id.nav_setting_fragment -> {
                fragment = SettingFragment.newInstance("Setting")
                setupToolbar(title = getString(R.string.setting_text_title), showHamburger = true, isShowIcon = false)
                mCurrentPosition = FRAGMENT_SETTING
            }
            R.id.nav_about_fragment -> {
                fragment = AboutFragment.newInstance("Home")
                setupToolbar(title = getString(R.string.about_text_title), showHamburger = true, isShowIcon = false)
                mCurrentPosition = FRAGMENT_ABOUT
            }
        }

        replaceFragment(fragment = fragment!!)
//        replaceFragmentWithAnimation(fragment = fragment!!)

        menuItem.isCheckable = true
        title = menuItem.title
        drawerLayout.closeDrawers()
    }

    private fun initInstance() {
        selectMenu(intent.getIntExtra("currentFragment", FRAGMENT_HOME))

        /* Initial left drawer header and set enable/disable item menu based on auth permission */
        setupNavHeader()
        setupNavMenu()

        /* Initial right drawer */
//        mExpandListAdapter = ExpandableListAdapter(this, mutableListOf())
        mFilteredBrandPresenter?.loadAllBrands()
//        brandListView.adapter = mExpandListAdapter
//        brandListView.layoutManager = LinearLayoutManager(this)

        btnChangeLanguage.text = if (SharedPrefUtil.isEnglish()) getString(R.string.dialog_language_list_english) else getString(R.string.dialog_language_list_thai)

        btnChangeLanguage.setOnClickListener {
            val builder = DialogUtil.getChangeLanguageDialog(this, getString(R.string.dialog_language_title)) {
                if (it == 0) {
                    setLocale("en")
                } else {
                    setLocale("th")
                }
                startActivity<MainActivity>("currentFragment" to mCurrentPosition)
            }

            builder!!.show()
        }
    }

    private fun setupNavMenu() {
        val pointMenu = nvView.menu.findItem(R.id.nav_point_fragment)
        val settingMenu = nvView.menu.findItem(R.id.nav_setting_fragment)

        val isLogin = SharedPrefUtil.loadLoginResult() != null
        pointMenu.isEnabled = isLogin
        settingMenu.isEnabled = isLogin
    }

    fun setupNavHeader() {
        val headerLayout = nvView.getHeaderView(0)
        val loginResult = SharedPrefUtil.loadLoginResult()
        if (loginResult != null)
            headerLayout.tvAccountUsername.text = "${loginResult.firstNameEN} ${loginResult.lastNameEN}"
        else
            headerLayout.tvAccountUsername.text = "Pointube"
    }

    private fun selectMenu(page: Int) {
        val fragment = intent.getIntExtra("fragment", page)
        val item = nvView.menu.getItem(fragment)
        item.isChecked = true
        selectDrawerItem(item)
    }

    override fun onLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        RxActivityResult.on(this).startIntent(intent).subscribe({ result ->
            val data = result.data()
            val resultCode = result.resultCode()
            if (resultCode == Activity.RESULT_OK) {
                result.targetUI().initInstance()
                result.targetUI().selectMenu(data.getIntExtra("fragment", FRAGMENT_POINT))
                result.targetUI().setupNavMenu()
            }
        })
    }

    override fun onLogout() {
        selectMenu(FRAGMENT_HOME)
        setupNavMenu()
        setupNavHeader()
    }

    /* For filtered brand in promotion menu */
    override fun onLoadAllBrands(allBrands: MutableList<BrandRepo>) {
        info { allBrands }
        mExpandListAdapter = ExpandableListAdapter(this, allBrands) { brandId, brandTitle, unitId ->
            startActivity<ProgramListActivity>("brandId" to brandId, "brandTitle" to brandTitle, "unitId" to unitId)
        }
        brandListView.adapter = mExpandListAdapter
        brandListView.layoutManager = LinearLayoutManager(this)
    }

    override fun onShowFilteredBrand() {
        with(drawerLayout) {
            if (isDrawerOpen(layoutFilteredBrand)) closeDrawer(layoutFilteredBrand) else openDrawer(layoutFilteredBrand)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onSignUp() {
        val intent = Intent(this, RegisterActivity::class.java)
        RxActivityResult.on(this).startIntent(intent).subscribe { result ->
            val data = result.data()
            val resultCode = result.resultCode()
            if (resultCode == Activity.RESULT_OK) {
                info("Register complete")
                result.targetUI().initInstance()

                /* Go to select brand activity after registration complete  */
                startSelectBrandActivity()
            } else {
                info("Cancel Register")
                setupNavMenu()
                setupNavHeader()
            }
        }
    }

    private fun startSelectBrandActivity() {
        val selectBrandIntent = Intent(this, SelectBrandActivity::class.java)
        RxActivityResult.on(this).startIntent(selectBrandIntent)
                .subscribe { brandActivityResult ->
                    if (brandActivityResult.resultCode() == Activity.RESULT_OK) {
                        /* TODO: Do something */
                    } else {
                        info("Cancel select brand")
                    }
                    brandActivityResult.targetUI().initInstance()
                    brandActivityResult.targetUI().selectMenu(FRAGMENT_POINT)
                }
    }
}
