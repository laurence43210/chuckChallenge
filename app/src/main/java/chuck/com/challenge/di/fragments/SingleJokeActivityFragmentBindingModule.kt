package chuck.com.challenge.di.fragments

import chuck.com.challenge.activities.singleJokeActivity.SingleJokeActivity
import chuck.com.challenge.activities.singleJokeActivity.SingleJokeFragment
import chuck.com.challenge.activities.singleJokeActivity.SingleJokeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SingleJokeActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [SingleJokeFragmentModule::class])
    abstract fun singleJokeFragmentInjector(): SingleJokeFragment
}