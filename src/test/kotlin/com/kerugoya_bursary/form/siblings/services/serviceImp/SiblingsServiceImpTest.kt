package com.kerugoya_bursary.form.siblings.services.serviceImp

import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.SiblingsDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.repositories.SiblingsRepository
import com.kerugoya_bursary.form.services.serviceImp.SiblingsServiceImp
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.test.Test

class SiblingsServiceImpTest {

    @Mock
    private lateinit var repository: SiblingsRepository

    private lateinit var service: SiblingsServiceImp

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = SiblingsServiceImp(repository)
    }

    @Test
    @DisplayName("Should return all siblings details")
    fun getAllSiblingsDetails() {
        val pageable = PageRequest.of(0, 10)
        val siblings = createTestSibling()
        val page: Page<Siblings> = PageImpl(listOf(siblings))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllSiblings(pageable)

        assertEquals(1, result.content.size)
        assertEquals(siblings, result.content[0])
    }

    @Test
    @DisplayName("Should create a new sibling detail")
    fun createSiblings() {
        val siblingsDto = createTestSiblingDto()
        val siblings = siblingsDto.toEntity()

        `when`(repository.save(siblings)).thenReturn(siblings)

        val result = service.createSiblings(siblingsDto)

        assertEquals(siblingsDto, result)
    }

    @Test
    @DisplayName("Should return a sibling detail by id")
    fun getSiblingsById() {
        val siblings = createTestSibling()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(siblings))

        val result = service.getSiblingsById(id)

        assertEquals(siblings, result)
    }

    @Test
    @DisplayName("Should throw an exception when sibling detail not found by id")
    fun getSiblingsByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.getSiblingsById(id)
        }
    }


    @Test
    @DisplayName("Should update a sibling detail")
    fun updateSiblingDetail() {
        val id = 1L
        val siblings = createTestSibling()
        val siblingsDto = createTestSiblingDto()

        `when`(repository.findById(id)).thenReturn(Optional.of(siblings))
        `when`(repository.save(siblings)).thenReturn(siblings)

        val result = service.updateSiblings(id, siblingsDto)

        assertEquals(siblingsDto.primarySchool, result.primarySchool)
        assertEquals(siblingsDto.secondarySchool, result.secondarySchool)
        assertEquals(siblingsDto.university, result.university)
        assertEquals(siblingsDto.tertiaryCollege, result.tertiaryCollege)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing sibling detail")
    fun updateSiblingDetailNotFound() {
        val id = 1L
        val siblingsDto = createTestSiblingDto()

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.updateSiblings(id, siblingsDto)
        }
    }

    @Test
    @DisplayName("Should delete a sibling detail by id")
    fun deleteSiblings() {
        val siblings = createTestSibling()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(siblings))

        Mockito.doNothing().`when`(repository).delete(siblings)

        assertDoesNotThrow { service.deleteSiblings(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing sibling detail")
    fun deleteSiblingsNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.deleteSiblings(id)
        }
    }
}

private fun createTestSibling(): Siblings {
    return Siblings(
        id = 1,
        primarySchool = 1,
        secondarySchool = 1,
        university = 1,
        tertiaryCollege = 1,
    )
}

private fun createTestSiblingDto(): SiblingsDto {
    return SiblingsDto(
        primarySchool = 1,
        secondarySchool = 1,
        university = 1,
        tertiaryCollege = 1,
    )
}
