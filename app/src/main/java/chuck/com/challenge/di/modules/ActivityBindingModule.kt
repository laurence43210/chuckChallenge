package chuck.com.challenge.di.modules

import chuck.com.challenge.ui.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [BaseActivityFragmentBindingModule::class])
    abstract fun baseActivityInjector(): BaseActivity
}