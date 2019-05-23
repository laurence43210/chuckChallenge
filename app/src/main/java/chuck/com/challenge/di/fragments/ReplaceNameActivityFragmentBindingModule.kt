package chuck.com.challenge.di.fragments

import chuck.com.challenge.activities.nameReplaceActivity.ReplaceNameFragment
import chuck.com.challenge.activities.nameReplaceActivity.ReplaceNameFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReplaceNameActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [ReplaceNameFragmentModule::class])
    abstract fun replaceNameFragmentInjector(): ReplaceNameFragment
}