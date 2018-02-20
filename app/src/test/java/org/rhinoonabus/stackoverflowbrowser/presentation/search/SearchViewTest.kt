package org.rhinoonabus.stackoverflowbrowser.presentation.search

import com.infullmobile.android.infullmvp.basetest.InFullMvpActivityBaseTest
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesWithPhraseUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.search.di.SearchModule
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class SearchViewTest: InFullMvpActivityBaseTest<SearchActivity, SearchPresenter, SearchView>() {

    override val testActivityClass = SearchActivity::class.java
    val mockedPresenter = mock<SearchPresenter>()
    val mockedModel = mock<SearchModel>()

    @Test
    fun shouldProvideStreamWithChangedQueryValuesWithDelay() {
        // given
        val testText = "testText"
        val testedStream = testedView.queryTextChanges.test()

        // when
        testedView.searchView.setQuery(testText, false)

        // then
        testedStream.awaitCount(1)
        testedStream.assertValue(testText)
    }

    @Test
    fun shouldDisplayErrorMessageInToast() {
        // given
        val error = IllegalStateException("test error")

        // when
        testedView.displayError(error)

        // then
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(getString(R.string.general_error))
    }

    override fun substituteModules(activity: SearchActivity) {
        activity.searchGraph.setSearchModule(TestSearchModule())
    }

    inner class TestSearchModule: SearchModule() {

        override fun providesSearchPresenter(view: SearchView, model: SearchModel) = mockedPresenter

        override fun providesSearchModel(
                searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesWithPhraseUseCase
        ) = mockedModel
    }
}