package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.os.Bundle
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.presentation.search.di.SearchModel

class SearchPresenterTest {

    val testPhrasesStream = PublishSubject.create<String>()
    val mockedSearchView = mock<SearchView>().apply {
        whenever(queryTextChanges).thenReturn(testPhrasesStream)
    }
    val mockedSearchModel = mock<SearchModel>()
    val presenter = SearchPresenter(mockedSearchView, mockedSearchModel)

    @Test
    fun shouldDisplayResultsFromModelWhenSearchPhraseInViewChangesAfterPresenterBound() {
        // given
        val testPhrase = "testPhrase"
        val phraseQueryResults = listOf("testResults")
        whenever(mockedSearchModel.queryForPhrase(testPhrase)).thenReturn(Single.just(phraseQueryResults))
        presenter.bind(Bundle(), Bundle(), null)

        // when
        testPhrasesStream.onNext(testPhrase)

        // then
        verify(mockedSearchView).displayResultsForPhrase(phraseQueryResults)
    }

    @Test
    fun shouldDisplayErrorWhenModelReturnsErrorForSearchPhrase() {
        // given
        val testPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedSearchModel.queryForPhrase(testPhrase)).thenReturn(Single.error(error))
        presenter.bind(Bundle(), Bundle(), null)

        // when
        testPhrasesStream.onNext(testPhrase)

        // then
        verify(mockedSearchView).displayError(error)
    }
}