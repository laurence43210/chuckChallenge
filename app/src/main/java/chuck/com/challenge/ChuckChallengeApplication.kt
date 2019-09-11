package chuck.com.challenge

import chuck.com.challenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class ChuckChallengeApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out ChuckChallengeApplication>? =
            DaggerAppComponent.factory().create(this)
}
