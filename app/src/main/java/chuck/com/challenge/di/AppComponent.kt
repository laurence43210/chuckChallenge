package chuck.com.challenge.di

import javax.inject.Singleton

import chuck.com.challenge.di.activity.ActivityBindingModule
import chuck.com.challenge.di.modules.ApiModule
import chuck.com.challenge.di.modules.AppModule
import chuck.com.challenge.di.modules.HelperModule
import chuck.com.challenge.di.modules.NetModule
import chuck.com.challenge.di.modules.RepositoryModule
import chuck.com.challenge.di.modules.SchedulersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, NetModule::class, HelperModule::class, ApiModule::class, RepositoryModule::class, ActivityBindingModule::class, SchedulersModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<DaggerApplication>
}
