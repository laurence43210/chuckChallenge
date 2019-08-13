package chuck.com.challenge.ui.nameReplace

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import chuck.com.challenge.viewmodels.ReplaceNameFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
object ReplaceNameFragmentModule {

    @JvmStatic
    @Provides
    fun provideReplaceNameFragmentViewModel(target: ReplaceNameFragment, provider: ViewModelProvider.Factory) =
            ViewModelProviders.of(target, provider).get(ReplaceNameFragmentViewModel::class.java)
}