package chuck.com.challenge.di.fragments

import chuck.com.challenge.ui.infiniteList.InfiniteListFragment
import chuck.com.challenge.ui.infiniteList.InfiniteListFragmentModule
import chuck.com.challenge.ui.nameReplace.ReplaceNameFragment
import chuck.com.challenge.ui.singleJoke.SingleJokeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BaseActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [InfiniteListFragmentModule::class])
    abstract fun infiniteListFragmentInjector(): InfiniteListFragment

    @ContributesAndroidInjector
    abstract fun replaceNameFragmentInjector(): ReplaceNameFragment

    @ContributesAndroidInjector
    abstract fun singleJokeFragmentInjector(): SingleJokeFragment
}