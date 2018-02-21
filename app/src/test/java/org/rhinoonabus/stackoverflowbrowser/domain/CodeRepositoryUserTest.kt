package org.rhinoonabus.stackoverflowbrowser.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryUserEntityFactory

class CodeRepositoryUserTest {

    @Test
    fun shouldBeSuccessFullyCreatedFromRepositoryUserEntity() {
        // given
        val repositoryUserEntity = GitHubRepositoryUserEntityFactory.USER_ENTITY_A

        // when
        val createdRepositoryUser = CodeRepositoryUser(repositoryUserEntity)

        // then
        assertThat(createdRepositoryUser.id).isEqualTo(repositoryUserEntity.id)
        assertThat(createdRepositoryUser.name).isEqualTo(repositoryUserEntity.userLogin)
        assertThat(createdRepositoryUser.url).isEqualTo(repositoryUserEntity.url)
    }
}