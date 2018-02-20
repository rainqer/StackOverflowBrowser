package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.view.LayoutInflater
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_A
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserFactory.USER_A
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SearchResultViewHolderTest {

    @Test
    fun shouldBindCodeRepositoryDataToFields() {
        // given
        val codeRepositoryToBind = REPOSITORY_A
        val viewHolder = buildViewHolder()

        // when
        viewHolder.bind(codeRepositoryToBind)

        // then
        assertThat(viewHolder.itemType.text).isEqualTo(getString(R.string.codeRepositoryTypeName))
        assertThat(viewHolder.itemName.text).isEqualTo(codeRepositoryToBind.name)
        assertThat(viewHolder.itemUrl.text).isEqualTo(codeRepositoryToBind.url)
    }

    @Test
    fun shouldBindCodeRepositoryUserDataToFields() {
        // given
        val codeRepositoryUserToBind = USER_A
        val viewHolder = buildViewHolder()

        // when
        viewHolder.bind(codeRepositoryUserToBind)

        // then
        assertThat(viewHolder.itemType.text).isEqualTo(getString(R.string.codeRepositoryUserTypeName))
        assertThat(viewHolder.itemName.text).isEqualTo(codeRepositoryUserToBind.name)
        assertThat(viewHolder.itemUrl.text).isEqualTo(codeRepositoryUserToBind.url)
    }

    private fun buildViewHolder() = SearchResultViewHolder(inflateViewForViewHolder())

    private fun inflateViewForViewHolder() =
            LayoutInflater.from(RuntimeEnvironment.application).inflate(R.layout.search_result_view_holder, null, false)

    private fun getString(stringRes: Int) = RuntimeEnvironment.application.getString(stringRes)
}