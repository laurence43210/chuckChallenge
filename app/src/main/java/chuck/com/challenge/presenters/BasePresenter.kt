package chuck.com.challenge.presenters

import chuck.com.challenge.contracts.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected var view: V? = null

    protected fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    open fun destroyAllDisposables() = compositeDisposable.clear()

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}