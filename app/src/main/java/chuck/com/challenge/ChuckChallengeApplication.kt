package chuck.com.challenge

import chuck.com.challenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ChuckChallengeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
            DaggerAppComponent.factory().create(this)
}
