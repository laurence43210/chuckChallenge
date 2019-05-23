package chuck.com.challenge.di.activity

import chuck.com.challenge.activities.infiniteListActivity.InfiniteListActivity
import chuck.com.challenge.activities.nameReplaceActivity.ReplaceNameActivity
import chuck.com.challenge.activities.singleJokeActivity.SingleJokeActivity
import chuck.com.challenge.di.fragments.InfiniteListActivityFragmentBindingModule
import chuck.com.challenge.di.fragments.ReplaceNameActivityFragmentBindingModule
import chuck.com.challenge.di.fragments.SingleJokeActivityFragmentBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [SingleJokeActivityFragmentBindingModule::class])
    abstract fun singleJokeActivityInjector(): SingleJokeActivity

    @ContributesAndroidInjector(modules = [InfiniteListActivityFragmentBindingModule::class])
    abstract fun infiniteListActivityInjector(): InfiniteListActivity

    @ContributesAndroidInjector(modules = [ReplaceNameActivityFragmentBindingModule::class])
    abstract fun replaceNameActivityInjector(): ReplaceNameActivity
}