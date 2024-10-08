package com.kerugoya_bursary.form.family.controllers

import com.kerugoya_bursary.form.controllers.FamilyDetailsController
import com.kerugoya_bursary.form.models.FamilyDetails
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.services.FamilyDetailsService
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
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

class FamilyDetailsControllerTest {


    @Mock
    private lateinit var service: FamilyDetailsService

    private lateinit var controller: FamilyDetailsController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller = FamilyDetailsController(service)
    }

    @Test
    @DisplayName("Should return all family details")
    fun getAllFamilyDetails() {
        val pageable = PageRequest.of(0, 10)
        val familyDetails = createFamilyDetails()
        val page: Page<FamilyDetails> = PageImpl(listOf(familyDetails))

        `when`(service.getAllFamilyDetails(pageable)).thenReturn(page)

        val result = controller.getAllFamilyDetails(pageable)

        assertEquals(1, result.body?.content?.size)
        assertEquals(familyDetails, result.body?.content?.get(0))
    }

    @Test
    @DisplayName("Should create a new family detail")
    fun createFamilyDetail() {
        val familyDetails = createFamilyDetails()

        `when`(service.createFamilyDetails(familyDetails)).thenReturn(familyDetails)

        val result = controller.createFamilyDetail(familyDetails)

        assertEquals(familyDetails, result.body)
    }

    @Test
    @DisplayName("Should return a family detail by id")
    fun getFamilyDetailById() {
        val familyDetails = createFamilyDetails()
        val id = 1L

        `when`(service.getFamilyDetailsById(id)).thenReturn(familyDetails)

        val result = controller.getFamilyDetailById(id)

        assertEquals(familyDetails, result.body)
    }

    @Test
    @DisplayName("Should update a family detail")
    fun updateFamilyDetail() {
        val familyDetails = createFamilyDetails()
        val id = 1L

        `when`(service.updateFamilyDetails(familyDetails)).thenReturn(familyDetails)

        val result = controller.updateFamilyDetail(id, familyDetails)

        assertEquals(familyDetails, result.body)
    }

    @Test
    @DisplayName("Should delete a family detail by id")
    fun deleteFamilyDetailById() {
        val id = 1L

        Mockito.doNothing().`when`(service).deleteFamilyDetails(id)

        assertDoesNotThrow { controller.deleteFamilyDetailById(id) }
    }
}

private fun createFamilyDetails(): FamilyDetails {
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