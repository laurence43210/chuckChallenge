package chuck.com.challenge.di.fragments

import chuck.com.challenge.ui.singleJoke.SingleJokeFragment
import chuck.com.challenge.ui.singleJoke.SingleJokeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SingleJokeActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [SingleJokeFragmentModule::class])
    abstract fun singleJokeFragmentInjector(): SingleJokeFragment
}