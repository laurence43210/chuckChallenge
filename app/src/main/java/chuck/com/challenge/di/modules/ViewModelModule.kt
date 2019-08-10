package chuck.com.challenge.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chuck.com.challenge.di.ChuckViewModelFactory
import chuck.com.challenge.di.ViewModelKey
import chuck.com.challenge.viewmodels.InfiniteListFragmentViewModel
import chuck.com.challenge.viewmodels.ReplaceNameFragmentViewModel
import chuck.com.challenge.viewmodels.SingleJokeFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SingleJokeFragmentViewModel::class)
    abstract fun bindSingleJokeFragmentViewModel(viewModel: SingleJokeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReplaceNameFragmentViewModel::class)
    abstract fun bindReplaceNameFragmentViewModel(viewModel: ReplaceNameFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfiniteListFragmentViewModel::class)
    abstract fun bindInfiniteListFragmentViewModel(viewModel: InfiniteListFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ChuckViewModelFactory): ViewModelProvider.Factory
}
