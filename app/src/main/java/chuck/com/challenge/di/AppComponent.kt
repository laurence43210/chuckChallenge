package chuck.com.challenge.di

import chuck.com.challenge.ChuckChallengeApplication
import javax.inject.Singleton

import chuck.com.challenge.di.modules.ActivityBindingModule
import chuck.com.challenge.di.modules.ApiModule
import chuck.com.challenge.di.modules.AppModule
import chuck.com.challenge.di.modules.DataSourceModule
import chuck.com.challenge.di.modules.HelperModule
import chuck.com.challenge.di.modules.NetModule
import chuck.com.challenge.di.modules.RepositoryModule
import chuck.com.challenge.di.modules.SchedulersModule
import chuck.com.challenge.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, NetModule::class, HelperModule::class, ApiModule::class, RepositoryModule::class, ActivityBindingModule::class, SchedulersModule::class, ViewModelModule::class, DataSourceModule::class])
interface AppComponent : AndroidInjector<ChuckChallengeApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<ChuckChallengeApplication>
}
