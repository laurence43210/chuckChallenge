package chuck.com.challenge.di.fragments

import chuck.com.challenge.activities.infiniteListActivity.InfiniteListFragment
import chuck.com.challenge.activities.infiniteListActivity.InfiniteListFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfiniteListActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [InfiniteListFragmentModule::class])
    abstract fun infiniteListFragmentInjector(): InfiniteListFragment
}