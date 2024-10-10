package com.kerugoya_bursary.form.bursary.services.serviceImp

import com.kerugoya_bursary.form.dtos.*
import com.kerugoya_bursary.form.mappers.BursaryApplicationMapper.toEntity
import com.kerugoya_bursary.form.models.*
import com.kerugoya_bursary.form.repositories.BursaryApplicationRepository
import com.kerugoya_bursary.form.services.serviceImp.BursaryServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.math.BigDecimal
import java.util.*

class BursaryServiceImplTest {
    @Mock
    private lateinit var repository: BursaryApplicationRepository

    private lateinit var service: BursaryServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = BursaryServiceImpl(repository)
    }

    @Test
    @DisplayName("Should return all bursary applications")
    fun getAllBursaryApplications() {
        val pageable = PageRequest.of(0, 10)
        val bursaryApplication = createTestBursaryApplication()
        val page: Page<BursaryApplication> = PageImpl(listOf(bursaryApplication))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllBursaryApplications(pageable)

        assertEquals(1, result.content.size)
        assertEquals(bursaryApplication, result.content[0])
    }

    @Test
    @DisplayName("Should create a new bursary application")
    fun createBursaryApplication() {
        val bursaryApplication = createTestBursaryApplication()

        `when`(repository.save(bursaryApplication)).thenReturn(bursaryApplication)

        val result = service.createBursaryApplication(bursaryApplication)

        assertEquals(bursaryApplication, result)
    }

    @Test
    @DisplayName("Should return a bursary application by id")
    fun getBursaryApplicationById() {
        val bursaryApplication = createTestBursaryApplication()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(bursaryApplication))

        val result = service.getBursaryApplicationById(id)

        assertEquals(bursaryApplication, result)
    }

    @Test
    @DisplayName("Should throw an exception when bursary application not found by id")
    fun getBursaryApplicationByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(IllegalArgumentException::class.java) {
            service.getBursaryApplicationById(id)
        }
    }

    @Test
    @DisplayName("Should update a bursary application")
    fun updateBursaryApplication() {
        val bursaryApplicationDto = createTestBursaryApplicationDto()
        val bursaryApplication = bursaryApplicationDto.toEntity()

        `when`(repository.save(bursaryApplication)).thenReturn(bursaryApplication)

        val result = service.updateBursaryApplication(bursaryApplicationDto)
        assertEquals(bursaryApplicationDto, result)
    }

    @Test
    @DisplayName("Should delete a bursary application by id")
    fun deleteBursaryApplication() {
        val bursaryApplication = createTestBursaryApplication()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(bursaryApplication))

        Mockito.doNothing().`when`(repository).delete(bursaryApplication)

        assertDoesNotThrow { service.deleteBursaryApplication(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing bursary application")
    fun deleteBursaryApplicationNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(IllegalArgumentException::class.java) {
            service.deleteBursaryApplication(id)
        }
    }
}

private fun createTestBursaryApplication(): BursaryApplication {
    return BursaryApplication(
        id = 1,
        personalDetails = createPersonalDetails(),
        familyDetails = createFamilyDetails(),
        sponsorshipDetails = createSponsorshipDetails(),
        declaration = "I declare that the information provided is true and accurate"
    )
}

private fun createTestBursaryApplicationDto(): BursaryApplicationDto {
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
