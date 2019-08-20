package chuck.com.challenge.ui.singleJoke

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import chuck.com.challenge.viewmodels.SingleJokeFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
object SingleJokeFragmentModule {

    @JvmStatic
    @Provides
    fun provideSingleJokeFragmentViewModel(target: SingleJokeFragment, provider: ViewModelProvider.Factory) =
            ViewModelProviders.of(target, provider).get(SingleJokeFragmentViewModel::class.java)
}