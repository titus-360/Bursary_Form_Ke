package com.kerugoya_bursary.form.personal_details.services.serviceImp

import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.repositories.PersonalDetailsRepository
import com.kerugoya_bursary.form.services.serviceImp.PersonalDetailsServiceImp
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

class PersonalDetailsServiceImpTest{

    @Mock
    private lateinit var repository: PersonalDetailsRepository

    private lateinit var service: PersonalDetailsServiceImp

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = PersonalDetailsServiceImp(repository)
    }

    @Test
    @DisplayName("Should return all personal details")
    fun getAllPersonalDetails() {
        val pageable = PageRequest.of(0, 10)
        val personalDetails = createTestPersonalDetails()
        val page: Page<PersonalDetails> = PageImpl(listOf(personalDetails))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllPersonalDetails(pageable)

        assertEquals(1, result.content.size)
        assertEquals(personalDetails, result.content[0])
    }

    @Test
    @DisplayName("Should create a new personal detail")
    fun createPersonalDetails() {
        val personalDetails = createTestPersonalDetails()

        `when`(repository.save(personalDetails)).thenReturn(personalDetails)

        val result = service.savePersonalDetails(personalDetails)

        assertEquals(personalDetails, result)
    }

    @Test
    @DisplayName("Should return a personal detail by id")
    fun getPersonalDetailsById() {
        val personalDetails = createTestPersonalDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(personalDetails))

        val result = service.getPersonalDetailsById(id)

        assertEquals(personalDetails, result)
    }

    @Test
    @DisplayName("Should throw an exception when personal detail not found by id")
    fun getPersonalDetailsByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.getPersonalDetailsById(id)
        }
    }

    @Test
    @DisplayName("Should update a personal detail")
    fun updatePersonalDetails() {
        val id = 1L
        val personalDetails = createTestPersonalDetails()
        val personalDetailsDto = createTestPersonalDetailsDto()

        `when`(repository.findById(id)).thenReturn(Optional.of(personalDetails))
        `when`(repository.save(personalDetails)).thenReturn(personalDetails)

        val result = service.updatePersonalDetails(id, personalDetailsDto)

        assertEquals(personalDetailsDto.firstName, result.firstName)
        assertEquals(personalDetailsDto.surname, result.surname)
        assertEquals(personalDetailsDto.otherNames, result.otherNames)
        assertEquals(personalDetailsDto.idNumber, result.idNumber)
        assertEquals(personalDetailsDto.gender, result.gender)
        assertEquals(personalDetailsDto.dob, result.dob)
        assertEquals(personalDetailsDto.phone, result.phone)
        assertEquals(personalDetailsDto.county, result.county)
        assertEquals(personalDetailsDto.subCounty, result.subCounty)
        assertEquals(personalDetailsDto.ward, result.ward)
        assertEquals(personalDetailsDto.school, result.school)
        assertEquals(personalDetailsDto.educationLevel, result.educationLevel)
        assertEquals(personalDetailsDto.admissionNumber, result.admissionNumber)
        assertEquals(personalDetailsDto.course, result.course)
        assertEquals(personalDetailsDto.disability, result.disability)
        assertEquals(personalDetailsDto.educationFinancier, result.educationFinancier)

    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing personal detail")
    fun updatePersonalDetailsNotFound() {
        val id = 1L
        val personalDetailsDto = createTestPersonalDetailsDto()

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.updatePersonalDetails(id, personalDetailsDto)
        }
    }

    @Test
    @DisplayName("Should delete a personal detail by id")
    fun deletePersonalDetails() {
        val personalDetails = createTestPersonalDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(personalDetails))

        Mockito.doNothing().`when`(repository).delete(personalDetails)

        assertDoesNotThrow { service.deletePersonalDetailsById(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing personal detail")
    fun deletePersonalDetailsNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.deletePersonalDetailsById(id)
        }
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