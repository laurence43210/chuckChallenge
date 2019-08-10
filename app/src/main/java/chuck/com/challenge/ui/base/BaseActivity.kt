package chuck.com.challenge.ui.base

import javax.inject.Inject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import chuck.com.challenge.R
import chuck.com.challenge.helpers.SharedPreferencesHelper
import chuck.com.challenge.ui.infiniteList.InfiniteListFragment
import chuck.com.challenge.ui.nameReplace.ReplaceNameFragment
import chuck.com.challenge.ui.singleJoke.SingleJokeFragment
import chuck.com.challenge.viewmodels.BaseActivityViewModule
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.bottomNavigationView
import kotlinx.android.synthetic.main.activity_base.toolbar

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var viewModel: BaseActivityViewModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this) [BaseActivityViewModule::class.java]
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        setUpNavigationView()
        viewModel.getSelectedTabId().observe(this, Observer(::onTabSelected))
    }

    private fun setUpNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.randomJoke -> {
                    viewModel.setToRandomJoke()
                    true
                }
                R.id.nameReplace -> {
                    viewModel.setToNameReplace()
                    true
                }

                R.id.infiniteList -> {
                    viewModel.setToInfiniteList()
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_base, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val checkable = menu.findItem(R.id.noExplicits)
        checkable.isChecked = sharedPreferencesHelper.explicitState
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.noExplicits -> {
            val isChecked = !item.isChecked
            item.isChecked = isChecked
            sharedPreferencesHelper.setExplicits(isChecked)
            true
        }
        else -> false
    }

    private fun onTabSelected(selectedTabId: Int) {
        when (selectedTabId) {
            R.id.randomJoke -> {
                supportActionBar?.show()
                val fragment = SingleJokeFragment.newInstance()
                openFragment(fragment)
            }
            R.id.nameReplace -> {
                supportActionBar?.show()
                val fragment = ReplaceNameFragment()
                openFragment(fragment)
            }

            R.id.infiniteList -> {
                supportActionBar?.hide()
                val fragment = InfiniteListFragment()
                openFragment(fragment)
            }
        }
    }
}
