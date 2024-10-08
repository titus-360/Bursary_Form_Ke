package com.kerugoya_bursary.form.siblings.controllers

import com.kerugoya_bursary.form.controllers.SiblingsController
import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.services.SiblingsService
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
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

class SiblingsControllerTest{

    @Mock
    private lateinit var service: SiblingsService

    private lateinit var controller: SiblingsController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller = SiblingsController(service)
    }

    @Test
    @DisplayName("Should return all siblings details")
    fun getAllSiblingsDetails() {
        val pageable = PageRequest.of(0, 10)
        val siblings = createTestSibling()
        val page: Page<Siblings> = PageImpl(listOf(siblings))

        `when`(service.getAllSiblings(pageable)).thenReturn(page)

        val result = controller.getAllSiblings(pageable)

        assertEquals(1, result.body?.content?.size)
        assertEquals(siblings, result.body?.content?.get(0))
    }

    @Test
    @DisplayName("Should create a new sibling detail")
    fun createSiblingDetail() {
        val siblings = createTestSibling()

        `when`(service.createSiblings(siblings)).thenReturn(siblings)

        val result = controller.createSibling(siblings)

        assertEquals(siblings, result.body)
    }

    @Test
    @DisplayName("Should return a sibling detail by id")
    fun getSiblingDetailById() {
        val siblings = createTestSibling()
        val id = 1L

        `when`(service.getSiblingsById(id)).thenReturn(siblings)

        val result = controller.getSiblingById(id)

        assertEquals(siblings, result.body)
    }

    @Test
    @DisplayName("Should update a sibling detail and return the updated detail")
    fun updateSiblingDetail() {
        val id = 1L
        val siblings = createTestSibling()
        val siblingsDto = createTestSiblingDto()

        `when`(service.updateSiblings(id, siblingsDto)).thenReturn(siblings)

        val result: ResponseEntity<Siblings> = controller.updateSibling(id, siblingsDto)

        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(siblings, result.body)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing sibling detail")
    fun updateSiblingDetailNotFound() {
        val id = 1L
        val siblingsDto = createTestSiblingDto()

        `when`(service.updateSiblings(id, siblingsDto)).thenThrow(ResourceNotFoundException::class.java)

        assertThrows(ResourceNotFoundException::class.java) {
            controller.updateSibling(id, siblingsDto)
        }
    }

    @Test
    @DisplayName("Should delete a sibling detail by id")
    fun deleteSiblingDetailById() {
        val id = 1L

        Mockito.doNothing().`when`(service).deleteSiblings(id)

        assertDoesNotThrow { controller.deleteSiblingById(id) }
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