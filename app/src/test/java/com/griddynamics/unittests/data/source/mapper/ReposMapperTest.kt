package com.griddynamics.unittests.data.source.mapper

import com.griddynamics.unittests.data.api.model.Owner
import com.griddynamics.unittests.data.api.model.response.RepoResponse
import com.griddynamics.unittests.data.db.entities.RepoEntity
import com.griddynamics.unittests.domain.model.Repo
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

private const val FAKE_REPO_ID = 123L
private const val FAKE_REPO_NAME = "SpaceX"
private const val FAKE_REPO_USER = "Elon Musk"
private const val FAKE_REPO_DESCRIPTION =
    "SpaceX designs, manufactures and launches advanced rockets and spacecraft."
private const val FAKE_OWNER_ID = 123L
private const val FAKE_OWNER_LOGIN = "Elon Musk"
private const val FAKE_REPO_RESPONSE_ID = 123L
private const val FAKE_REPO_RESPONSE_NAME = "SpaceX"
private const val FAKE_REPO_RESPONSE_DESCRIPTION =
    "SpaceX designs, manufactures and launches advanced rockets and spacecraft."
private const val FAKE_REPO_RESPONSE_FULL_NAME = "Elon Musk"

class ReposMapperTest {

    private lateinit var mapper: RepoMapper

    @Before
    fun setup() {
        mapper = RepoMapper()
    }

    @Test
    fun testMapDomainToStorage() {
        // given
        val repo = createFakeRepo()

        // when
        val entity: RepoEntity = mapper.mapDomainToStorage(repo)

        // then
        assertThat(entity.id).isEqualTo(repo.id)
        assertThat(entity.user).isEqualTo(repo.user)
        assertThat(entity.name).isEqualTo(repo.name)
        assertThat(entity.description).isEqualTo(repo.description)
    }

    @Test
    fun testMapStorageToDomain() {
        // given
        val entity = createFakeReposEntity()

        // when
        val repo: Repo = mapper.mapStorageToDomain(entity)

        // then
        assertThat(entity.id).isEqualTo(repo.id)
        assertThat(entity.user).isEqualTo(repo.user)
        assertThat(entity.name).isEqualTo(repo.name)
        assertThat(entity.description).isEqualTo(repo.description)
    }

    @Test
    fun testMapApiToDomain() {
        // given
        val repoResponse = createFakeRepoResponse()

        // when
        val repo: Repo = mapper.mapApiToDomain(repoResponse)

        // then
        assertThat(repoResponse.id).isEqualTo(repo.id)
        assertThat(repoResponse.owner?.login).isEqualTo(repo.user)
        assertThat(repoResponse.name).isEqualTo(repo.name)
        assertThat(repoResponse.description).isEqualTo(repo.description)
    }

    private fun createFakeRepo(): Repo {
        return Repo(
            id = FAKE_REPO_ID,
            name = FAKE_REPO_NAME,
            description = FAKE_REPO_DESCRIPTION,
            user = FAKE_REPO_USER
        )
    }

    private fun createFakeReposEntity(): RepoEntity {
        return RepoEntity(
            id = FAKE_REPO_ID,
            name = FAKE_REPO_NAME,
            description = FAKE_REPO_DESCRIPTION,
            user = FAKE_REPO_USER
        )
    }

    private fun createFakeRepoResponse(): RepoResponse {
        return RepoResponse(
            id = FAKE_REPO_RESPONSE_ID,
            name = FAKE_REPO_RESPONSE_NAME,
            description = FAKE_REPO_RESPONSE_DESCRIPTION,
            fullName = FAKE_REPO_RESPONSE_FULL_NAME,
            owner = createFakeOwner()
        )
    }

    private fun createFakeOwner(): Owner {
        return Owner(
            login = FAKE_OWNER_LOGIN,
            id = FAKE_OWNER_ID
        )
    }
}