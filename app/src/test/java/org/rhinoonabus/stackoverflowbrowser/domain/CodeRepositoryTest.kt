package org.rhinoonabus.stackoverflowbrowser.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryEntityFactory

class CodeRepositoryTest {

    @Test
    fun shouldBeSuccessfullyConstructedFromRepositoryEntity() {
        // given
        val repositoryEntity = GitHubRepositoryEntityFactory.REPOSITORY_ENTITY_A

        // when
        val createdRepository = CodeRepository(repositoryEntity)

        // then
        assertThat(createdRepository.id).isEqualTo(repositoryEntity.id)
        assertThat(createdRepository.name).isEqualTo(repositoryEntity.name)
        assertThat(createdRepository.description).isEqualTo(repositoryEntity.description)
        assertThat(createdRepository.url).isEqualTo(repositoryEntity.url)
    }
}