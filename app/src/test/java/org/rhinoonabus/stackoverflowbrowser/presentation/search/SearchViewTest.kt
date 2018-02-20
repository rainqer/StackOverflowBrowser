package org.rhinoonabus.stackoverflowbrowser.presentation.search

import com.infullmobile.android.infullmvp.basetest.InFullMvpActivityBaseTest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesOrUsersWithPhraseUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchView.Companion.QUERY_DELAY_TIME_IN_SECONDS
import org.rhinoonabus.stackoverflowbrowser.presentation.search.di.SearchModule
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class SearchViewTest: InFullMvpActivityBaseTest<SearchActivity, SearchPresenter, SearchView>() {

    override val testActivityClass = SearchActivity::class.java
    val testScheduler = TestScheduler()
    val mockedPresenter = mock<SearchPresenter>()
    val mockedModel = mock<SearchModel>()
    val mockedAdapter = mock<SearchResultsAdapter>()

    @Test
    fun shouldProvideStreamWithChangedQueryValuesWithDelay() {
        // given
        val testText = "testText"
        val testedStream = testedView.queryTextChanges.test()

        // when
        testedView.searchView.setQuery(testText, false)

        // then
        testScheduler.advanceTimeBy(QUERY_DELAY_TIME_IN_SECONDS, TimeUnit.SECONDS)
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

    @Test
    fun shouldBeSettingNewDataOnResultsAdapter() {
        // given
        val testResults = listOf(CodeRepositoryFactory.REPOSITORY_A)

        // when
        testedView.displayResultsForPhrase(testResults)

        // then
        verify(mockedAdapter).setData(testResults)
    }

    override fun substituteModules(activity: SearchActivity) =
            activity.searchGraph.setSearchModule(TestSearchModule())

    inner class TestSearchModule: SearchModule() {

        override fun providesSearchPresenter(view: SearchView, model: SearchModel) = mockedPresenter

        override fun providesSearchModel(
                searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesOrUsersWithPhraseUseCase
        ) = mockedModel

        override fun providesSearchResultsAdapter() = mockedAdapter

        override fun providesOnQueryTextChangedScheduler() = testScheduler
    }
}