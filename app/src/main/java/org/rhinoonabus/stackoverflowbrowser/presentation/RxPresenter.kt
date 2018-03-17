package org.rhinoonabus.stackoverflowbrowser.presentation

import com.infullmobile.android.infullmvp.PresentedView
import com.infullmobile.android.infullmvp.Presenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<out PresentedViewType : PresentedView<*, *>>(presentedView: PresentedViewType)
    : Presenter<PresentedViewType>(presentedView) {

    private val subscriptions = CompositeDisposable()

    override fun unbind() {
        subscriptions.clear()
    }

    protected fun Disposable.register() = subscriptions.add(this)
}
