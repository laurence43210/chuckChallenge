package chuck.com.challenge.di.activity

import chuck.com.challenge.di.fragments.BaseActivityFragmentBindingModule
import chuck.com.challenge.ui.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [BaseActivityFragmentBindingModule::class])
    abstract fun baseActivityInjector(): BaseActivity
}