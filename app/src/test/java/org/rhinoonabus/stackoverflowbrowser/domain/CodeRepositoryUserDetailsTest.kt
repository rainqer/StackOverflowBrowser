package org.rhinoonabus.stackoverflowbrowser.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubUserDetailsResponseEntityFactory

class CodeRepositoryUserDetailsTest {

    @Test
    fun shouldCorrectlyParseFromEntity() {
        // given
        val entityToParse = GitHubUserDetailsResponseEntityFactory.A_RESPONSE

        // when
        val parsedUserDetails = CodeRepositoryUserDetails(entityToParse)

        // then
        assertThat(parsedUserDetails.id).isEqualTo(entityToParse.id)
        assertThat(parsedUserDetails.login).isEqualTo(entityToParse.login)
        assertThat(parsedUserDetails.avatarUrl).isEqualTo(entityToParse.avatarUrl)
        assertThat(parsedUserDetails.followers).isEqualTo(entityToParse.followers)
    }
}