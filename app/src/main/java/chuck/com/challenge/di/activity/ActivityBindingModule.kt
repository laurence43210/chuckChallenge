package chuck.com.challenge.di.activity

import chuck.com.challenge.di.fragments.InfiniteListActivityFragmentBindingModule
import chuck.com.challenge.di.fragments.ReplaceNameActivityFragmentBindingModule
import chuck.com.challenge.di.fragments.SingleJokeActivityFragmentBindingModule
import chuck.com.challenge.ui.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [
        SingleJokeActivityFragmentBindingModule::class,
        InfiniteListActivityFragmentBindingModule::class,
        ReplaceNameActivityFragmentBindingModule::class])
    abstract fun baseActivityInjector(): BaseActivity
}