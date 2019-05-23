package chuck.com.challenge.di.fragments

import chuck.com.challenge.ui.nameReplace.ReplaceNameFragment
import chuck.com.challenge.ui.nameReplace.ReplaceNameFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReplaceNameActivityFragmentBindingModule {

    @ContributesAndroidInjector(modules = [ReplaceNameFragmentModule::class])
    abstract fun replaceNameFragmentInjector(): ReplaceNameFragment
}