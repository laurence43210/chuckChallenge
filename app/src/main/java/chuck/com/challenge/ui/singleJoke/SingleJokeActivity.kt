package chuck.com.challenge.ui.singleJoke

import android.os.Bundle

import chuck.com.challenge.R
import dagger.android.support.DaggerAppCompatActivity

class SingleJokeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }
}
