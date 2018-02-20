package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.view.LayoutInflater
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_A
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_B
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserFactory.USER_A
import org.rhinoonabus.stackoverflowbrowser.domain.SearchResultItem
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SearchResultsAdapterTest {

    val searchResultsAdapter = SearchResultsAdapter()

    @Test
    fun shouldGetCountEqualToTheSetCollectionSize() {
        // given
        val testResults = listOf(REPOSITORY_A)

        // when
        searchResultsAdapter.setData(testResults)

        // then
        assertThat(searchResultsAdapter.itemCount).isEqualTo(testResults.size)
    }

    @Test
    fun shouldBindAppropriateSearchResultDataToViewHolder() {
        // given
        val testResults = listOf<SearchResultItem>(USER_A, REPOSITORY_B)
        val indexToBeBound = 1
        val viewHolder = buildViewHolder()
        searchResultsAdapter.setData(testResults)

        // when
        searchResultsAdapter.onBindViewHolder(viewHolder, indexToBeBound)

        // then
        assertThat(viewHolder.itemName.text).isEqualTo(testResults[indexToBeBound].name)
        assertThat(viewHolder.itemUrl.text).isEqualTo(testResults[indexToBeBound].url)
    }

    private fun buildViewHolder() = SearchResultViewHolder(inflateViewForViewHolder())

    private fun inflateViewForViewHolder() =
            LayoutInflater.from(RuntimeEnvironment.application).inflate(R.layout.search_result_view_holder, null, false)
}