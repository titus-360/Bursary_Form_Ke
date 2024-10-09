package com.kerugoya_bursary.form.family.services.serviceImp

import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.FamilyDetails
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.repositories.FamilyDetailsRepository
import com.kerugoya_bursary.form.services.serviceImp.FamilyDetailsServiceImpl
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

class FamilyDetailsServiceImplTest{

    @Mock
    private lateinit var repository: FamilyDetailsRepository

    private lateinit var service: FamilyDetailsServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = FamilyDetailsServiceImpl(repository)
    }

    @Test
    @DisplayName("Should return all family details")
    fun getAllFamilyDetails() {
        val pageable = PageRequest.of(0, 10)
        val familyDetails =  createTestFamilyDetails()
        val page: Page<FamilyDetails> = PageImpl(listOf(familyDetails))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllFamilyDetails(pageable)

        assertEquals(1, result.content.size)
        assertEquals(familyDetails, result.content[0])
    }

    @Test
    @DisplayName("Should create a new family detail")
    fun createFamilyDetails() {
        val familyDetails =  createTestFamilyDetails()

        `when`(repository.save(familyDetails)).thenReturn(familyDetails)

        val result = service.createFamilyDetails(familyDetails)

        assertEquals(familyDetails, result)
    }

    @Test
    @DisplayName("Should return a family detail by id")
    fun getFamilyDetailsById() {
        val familyDetails = createTestFamilyDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(familyDetails))

        val result = service.getFamilyDetailsById(id)

        assertEquals(familyDetails, result)
    }

    @Test
    @DisplayName("Should throw an exception when family detail not found by id")
    fun getFamilyDetailsByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.getFamilyDetailsById(id)
        }
    }

    @Test
    @DisplayName("Should update a family detail")
    fun updateFamilyDetails() {
        val familyDetails =  createTestFamilyDetails()

        `when`(repository.save(familyDetails)).thenReturn(familyDetails)

        val result = service.updateFamilyDetails(familyDetails)

        assertEquals(familyDetails, result)
    }

    @Test
    @DisplayName("Should delete a family detail by id")
    fun deleteFamilyDetails() {
        val familyDetails = createTestFamilyDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(familyDetails))

        Mockito.doNothing().`when`(repository).delete(familyDetails)

        assertDoesNotThrow { service.deleteFamilyDetails(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing family detail")
    fun deleteFamilyDetailsNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.deleteFamilyDetails(id)
        }
    }
}


private fun createTestFamilyDetails(): FamilyDetails {
    return FamilyDetails(
        id = 1,
        siblings = createSibling(),
        parents = listOf(createParentDetails())
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