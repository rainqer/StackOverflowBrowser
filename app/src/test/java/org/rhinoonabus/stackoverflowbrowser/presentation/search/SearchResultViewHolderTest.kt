package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.view.LayoutInflater
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SearchResultViewHolderTest {

    @Test
    fun shouldBindCodeRepositoryDataToFields() {
        // given
        val codeRepositoryToBind = CodeRepositoryFactory.REPOSITORY_A
        val viewHolder = buildViewHolder()

        // when
        viewHolder.bind(codeRepositoryToBind)

        // then
        assertThat(viewHolder.repositoryName.text).isEqualTo(codeRepositoryToBind.name)
        assertThat(viewHolder.repositoryDescription.text).isEqualTo(codeRepositoryToBind.description)
        assertThat(viewHolder.repositoryUrl.text).isEqualTo(codeRepositoryToBind.url)
    }

    private fun buildViewHolder() = SearchResultViewHolder(inflateViewForViewHolder())

    private fun inflateViewForViewHolder() =
            LayoutInflater.from(RuntimeEnvironment.application).inflate(R.layout.search_result_view_holder, null, false)
}