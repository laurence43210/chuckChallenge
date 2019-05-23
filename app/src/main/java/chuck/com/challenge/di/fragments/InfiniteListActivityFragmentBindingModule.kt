package chuck.com.challenge.di.fragments

import chuck.com.challenge.ui.infiniteList.InfiniteListFragment
import chuck.com.challenge.ui.infiniteList.InfiniteListFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfiniteListActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [InfiniteListFragmentModule::class])
    abstract fun infiniteListFragmentInjector(): InfiniteListFragment
}