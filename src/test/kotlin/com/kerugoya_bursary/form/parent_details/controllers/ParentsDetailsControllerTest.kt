package com.kerugoya_bursary.form.parent_details.controllers

import com.kerugoya_bursary.form.controllers.ParentsDetailsController
import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.services.ParentDetailsService
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
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

class ParentsDetailsControllerTest {
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
        val parentDetailsDto = createTestParentDetailsDto()
        val parentDetails = parentDetailsDto.toEntity()

        `when`(service.createParentDetails(parentDetailsDto)).thenReturn(parentDetailsDto)

        val result = controller.createParent(parentDetailsDto)

        assertEquals(parentDetailsDto, result.body)
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
        val parentDetailsDto = ParentDetailsDto(
            id = parentDetails.id,
            idNumber = parentDetails.idNumber,
            phone = parentDetails.phone,
            occupation = parentDetails.occupation,
            relationship = parentDetails.relationship,
            age = parentDetails.age,
            county = parentDetails.county,
            subCounty = parentDetails.subCounty,
            ward = parentDetails.ward,
            firstName = parentDetails.firstName,
            surname = parentDetails.surname,
            otherNames = parentDetails.otherNames,
            status = parentDetails.status,
            type = parentDetails.type
        )
        val id = 1L

        `when`(service.updateParentDetails(id, parentDetailsDto)).thenReturn(parentDetails)
        val result = controller.updateParents(id, parentDetailsDto)

        assertEquals(parentDetailsDto, result.body?.toDto())
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

private fun createTestParentDetailsDto(): ParentDetailsDto {
    return ParentDetailsDto(
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
