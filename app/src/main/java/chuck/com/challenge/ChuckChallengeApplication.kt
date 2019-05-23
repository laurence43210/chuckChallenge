package chuck.com.challenge

import chuck.com.challenge.di.DaggerAppComponent
import chuck.com.challenge.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ChuckChallengeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
            DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                   .build()

}
