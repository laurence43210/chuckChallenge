package chuck.com.challenge.ui.base

import javax.inject.Inject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

import chuck.com.challenge.R
import chuck.com.challenge.helpers.SharedPreferencesHelper
import chuck.com.challenge.ui.infiniteList.InfiniteListFragment
import chuck.com.challenge.ui.nameReplace.ReplaceNameFragment
import chuck.com.challenge.ui.singleJoke.SingleJokeFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.bottomNavigationView
import kotlinx.android.synthetic.main.activity_base.toolbar

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        setUpNavigationView()
        val fragment = SingleJokeFragment.newInstance()
        openFragment(fragment)
    }

    private fun setUpNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.randomJoke -> {
                    supportActionBar?.show()
                    val fragment = SingleJokeFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.nameReplace -> {
                    supportActionBar?.show()
                    val fragment = ReplaceNameFragment()
                    openFragment(fragment)

                    true
                }

                R.id.infiniteList -> {
                    supportActionBar?.hide()
                    val fragment = InfiniteListFragment()
                    openFragment(fragment)
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
}
