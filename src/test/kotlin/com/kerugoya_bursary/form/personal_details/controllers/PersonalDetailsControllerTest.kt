package com.kerugoya_bursary.form.personal_details.controllers

import com.kerugoya_bursary.form.controllers.PersonalDetailsController
import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.services.PersonalDetailsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

class PersonalDetailsControllerTest {

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
        val personalDetailsDto = createTestPersonalDetailsDto()
        val personalDetails = personalDetailsDto.toEntity()

        `when`(service.savePersonalDetails(personalDetailsDto)).thenReturn(personalDetailsDto)

        val result = controller.createPersonalDetail(personalDetailsDto)

        assertEquals(personalDetailsDto, result.body)
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

        `when`(service.updatePersonalDetails(id, personalDetailsDto)).thenReturn(personalDetailsDto)

        val result: ResponseEntity<PersonalDetailsDto> = controller.updatePersonalDetail(id, personalDetailsDto)

        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(personalDetailsDto, result.body)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing personal detail")
    fun updatePersonalDetailNotFound() {
        // Test implementation
    }

    @Test
    @DisplayName("Should delete a personal detail by id")
    fun deletePersonalDetailById() {
        // Test implementation
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
            otherNames = "Doe"
        )
    }

    private fun createTestPersonalDetailsDto(): PersonalDetailsDto {
        return PersonalDetailsDto(
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
            otherNames = "Doe"
        )
    }
}
