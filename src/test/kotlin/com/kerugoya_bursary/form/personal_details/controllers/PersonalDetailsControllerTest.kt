package com.kerugoya_bursary.form.personal_details.controllers

import com.kerugoya_bursary.form.controllers.PersonalDetailsController
import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.services.PersonalDetailsService
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

class PersonalDetailsControllerTest{


    @Mock
    private lateinit var service: PersonalDetailsService

    private lateinit var controller: PersonalDetailsController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller = PersonalDetailsController(service)
    }

    @Test
    @DisplayName("Should return all personal details")
    fun getAllPersonalDetails() {
        val pageable = PageRequest.of(0, 10)
        val personalDetails = createTestPersonalDetails()
        val page: Page<PersonalDetails> = PageImpl(listOf(personalDetails))

        `when`(service.getAllPersonalDetails(pageable)).thenReturn(page)

        val result = controller.getAllPersonalDetails(pageable)

        assertEquals(1, result.body?.content?.size)
        assertEquals(personalDetails, result.body?.content?.get(0))
    }

    @Test
    @DisplayName("Should create a new personal detail")
    fun createPersonalDetail() {
        val personalDetails = createTestPersonalDetails()

        `when`(service.savePersonalDetails(personalDetails)).thenReturn(personalDetails)

        val result = controller.createPersonalDetail(personalDetails)

        assertEquals(personalDetails, result.body)
    }

    @Test
    @DisplayName("Should return a personal detail by id")
    fun getPersonalDetailById() {
        val personalDetails = createTestPersonalDetails()
        val id = 1L

        `when`(service.getPersonalDetailsById(id)).thenReturn(personalDetails)

        val result = controller.getPersonalDetailById(id)

        assertEquals(personalDetails, result.body)
    }

    @Test
    @DisplayName("Should update a personal detail and return the updated detail")
    fun updatePersonalDetail() {
        val id = 1L
        val personalDetails = createTestPersonalDetails()
        val personalDetailsDto = createTestPersonalDetailsDto()

        `when`(service.updatePersonalDetails(id, personalDetailsDto)).thenReturn(personalDetails)

        val result: ResponseEntity<PersonalDetails> = controller.updatePersonalDetail(id, personalDetailsDto)

        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(personalDetails, result.body)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing personal detail")
    fun updatePersonalDetailNotFound() {
        val id = 1L
        val personalDetailsDto = createTestPersonalDetailsDto()

        `when`(service.updatePersonalDetails(id, personalDetailsDto)).thenThrow(ResourceNotFoundException::class.java)

        assertThrows(ResourceNotFoundException::class.java) {
            controller.updatePersonalDetail(id, personalDetailsDto)
        }
    }

    @Test
    @DisplayName("Should delete a personal detail by id")
    fun deletePersonalDetailById() {
        val id = 1L

        Mockito.doNothing().`when`(service).deletePersonalDetailsById(id)

        assertDoesNotThrow { controller.deletePersonalDetailById(id) }
    }
}




private fun createTestPersonalDetails(): PersonalDetails {
    return PersonalDetails(
        id = 1,
        firstName = "John",
        surname = "Doe",
        gender = "male",
        county = "1990-01-01",
        dob = "1990-01-01",
        phone = "0712345678",
        school = "Kerugoya High School",
        educationLevel = "secondary",
        educationFinancier = "self",
        admissionNumber = "123456",
        course = "Computer Science",
        disability = false,
        ward = "Kerugoya",
        subCounty = "Kirinyaga",
        idNumber = "12345678",
        otherNames = "Doe",
    )
}


private fun createTestPersonalDetailsDto(): PersonalDetailsDto {
    return  PersonalDetailsDto(
        firstName = "Jane",
        surname = "Doe",
        otherNames = "Doe",
        idNumber = "87654321",
        gender = "female",
        dob = "1991-01-01",
        phone = "0787654321",
        county = "Nairobi",
        subCounty = "Westlands",
        ward = "Parklands",
        school = "Nairobi High School",
        educationLevel = "university",
        admissionNumber = "654321",
        course = "Software Engineering",
        disability = false,
        educationFinancier = "parents"
    )
}