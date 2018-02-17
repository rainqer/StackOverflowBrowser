package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.annotation.LayoutRes
import com.infullmobile.android.infullmvp.PresentedActivityView
import org.rhinoonabus.stackoverflowbrowser.R

open class SearchView: PresentedActivityView<SearchPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_search

    override fun onViewsBound() {
        // NO-OP
    }
}
