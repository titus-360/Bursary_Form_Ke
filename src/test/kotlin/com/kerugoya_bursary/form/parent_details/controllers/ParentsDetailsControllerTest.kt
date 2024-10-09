package com.kerugoya_bursary.form.parent_details.controllers

import com.kerugoya_bursary.form.controllers.ParentsDetailsController
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.services.ParentDetailsService
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
import kotlin.test.Test

class ParentsDetailsControllerTest{
    @Mock
    private lateinit var service: ParentDetailsService

    private lateinit var controller: ParentsDetailsController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller = ParentsDetailsController(service)
    }

    @Test
    @DisplayName("Should return all parent details")
    fun getAllParentsDetails() {
        val pageable = PageRequest.of(0, 10)
        val parentDetails = createTestParentDetails()
        val page: Page<ParentDetails> = PageImpl(listOf(parentDetails))

        `when`(service.getAllParentDetails(pageable)).thenReturn(page)

        val result = controller.getAllParents(pageable)

        assertEquals(1, result.body?.content?.size)
        assertEquals(parentDetails, result.body?.content?.get(0))
    }

    @Test
    @DisplayName("Should create a new parent detail")
    fun createParentDetail() {
        val parentDetails = createTestParentDetails()

        `when`(service.createParentDetails(parentDetails)).thenReturn(parentDetails)

        val result = controller.createParent(parentDetails)

        assertEquals(parentDetails, result.body)
    }

    @Test
    @DisplayName("Should return a parent detail by id")
    fun getParentDetailById() {
        val parentDetails = createTestParentDetails()
        val id = 1L

        `when`(service.getParentDetailsById(id)).thenReturn(parentDetails)

        val result = controller.getParentsById(id)

        assertEquals(parentDetails, result.body)
    }

    @Test
    @DisplayName("Should update a parent detail")
    fun updateParentDetail() {
        val parentDetails = createTestParentDetails()
        val id = 1L

        `when`(service.updateParentDetails(parentDetails)).thenReturn(parentDetails)

        val result = controller.updateParents(id, parentDetails)

        assertEquals(parentDetails, result.body)
    }

    @Test
    @DisplayName("Should delete a parent detail by id")
    fun deleteParentDetailById() {
        val id = 1L

        Mockito.doNothing().`when`(service).deleteParentDetails(id)

        assertDoesNotThrow { controller.deleteParentById(id) }
    }
}

private fun createTestParentDetails(): ParentDetails {
    return ParentDetails(
        id = 1,
        idNumber = "12345678",
        phone = "0712345678",
        occupation = "farmer",
        relationship = "mother",
        age = 50,
        county = "Kirinyaga",
        subCounty = "Kirinyaga Central",
        ward = "Kerugoya",
        firstName = "Jane",
        surname = "Doe",
        otherNames = "Doe",
        status = "married",
        type = "guardian",
    )
}