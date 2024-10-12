package com.kerugoya_bursary.form.bursary.controllers

import com.kerugoya_bursary.form.controllers.BursaryController
import com.kerugoya_bursary.form.dtos.*
import com.kerugoya_bursary.form.models.*
import com.kerugoya_bursary.form.services.BursaryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import java.math.BigDecimal

class BursaryControllerTest {

    @InjectMocks
    lateinit var bursaryController: BursaryController

    @Mock
    lateinit var bursaryService: BursaryService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get all bursary applications - success`() {
        val pageable: Pageable = PageRequest.of(0, 50)
        val bursaryPage: Page<BursaryApplication> = PageImpl(listOf(createBursaryApplication()))

        `when`(bursaryService.getAllBursaryApplications(pageable)).thenReturn(bursaryPage)

        val response: ResponseEntity<Page<BursaryApplication>> = bursaryController.getBursary(pageable)

        assertEquals(bursaryPage, response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `get bursary by id - success`() {
        val id = 1L
        `when`(bursaryService.getBursaryApplicationById(id)).thenReturn(createBursaryApplication())

        val response: ResponseEntity<BursaryApplication> = bursaryController.getBursaryById(id)

        assertEquals(createBursaryApplication().id, response.body?.id)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `update bursary application - success`() {
        val bursaryApplicationDto = createBursaryApplicationDto()
        val id = 1L

        `when`(bursaryService.updateBursaryApplication(bursaryApplicationDto)).thenReturn(bursaryApplicationDto)

        val response: ResponseEntity<BursaryApplicationDto> = bursaryController.updateBursary(id, bursaryApplicationDto)

        assertEquals(bursaryApplicationDto, response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `create bursary application - success`() {
        `when`(bursaryService.createBursaryApplication(anyOrNull()))
            .thenReturn(createBursaryApplicationDto())

        val response: ResponseEntity<BursaryApplicationDto> =
            bursaryController.createBursary(createBursaryApplicationDto())

        assertEquals(createBursaryApplicationDto(), response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `delete bursary by id - success`() {
        val id = 1L
        `when`(bursaryService.deleteBursaryApplication(id)).then { }

        val response: ResponseEntity<Void> = bursaryController.deleteBursaryById(id)

        assertEquals(204, response.statusCode.value())
    }

    private fun createBursaryApplication(): BursaryApplication {
        return BursaryApplication(
            id = 1,
            personalDetails = createPersonalDetails(),
            familyDetails = createFamilyDetails(),
            sponsorshipDetails = createSponsorshipDetails(),
            declaration = "I declare that the information provided is true and accurate"
        )
    }

    private fun createBursaryApplicationDto(): BursaryApplicationDto {
        return BursaryApplicationDto(
            id = 1,
            personalDetails = createPersonalDetailsDto(),
            familyDetails = createFamilyDetailsDto(),
            sponsorshipDetails = createSponsorshipDetailsDto(),
            declaration = "I declare that the information provided is true and accurate"
        )
    }

    private fun createSponsorshipDetails(): SponsorshipDetails {
        return SponsorshipDetails(
            id = 1,
            bursaryReceived = BigDecimal.valueOf(10000),
            feesRequired = BigDecimal.valueOf(20000),
            feesBalance = BigDecimal.valueOf(3000),
        )
    }

    private fun createSponsorshipDetailsDto(): SponsorshipDetailsDto {
        return SponsorshipDetailsDto(
            bursaryReceived = BigDecimal.valueOf(10000),
            feesRequired = BigDecimal.valueOf(20000),
            feesBalance = BigDecimal.valueOf(3000),
        )
    }

    private fun createPersonalDetails(): PersonalDetails {
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

    private fun createPersonalDetailsDto(): PersonalDetailsDto {
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
            otherNames = "Doe",
        )
    }

    private fun createFamilyDetails(): FamilyDetails {
        return FamilyDetails(
            id = 1,
            siblings = createSibling(),
            parents = listOf(createParentDetails())
        )
    }

    private fun createFamilyDetailsDto(): FamilyDetailsDto {
        return FamilyDetailsDto(
            siblings = createSiblingDto(),
            parents = listOf(createParentDetailsDto())
        )
    }

    private fun createSibling(): Siblings {
        return Siblings(
            id = 1,
            primarySchool = 1,
            secondarySchool = 1,
            university = 1,
            tertiaryCollege = 1,
        )
    }

    private fun createSiblingDto(): SiblingsDto {
        return SiblingsDto(
            primarySchool = 1,
            secondarySchool = 1,
            university = 1,
            tertiaryCollege = 1,
        )
    }

    private fun createParentDetails(): ParentDetails {
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

    private fun createParentDetailsDto(): ParentDetailsDto {
        return ParentDetailsDto(
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
}
