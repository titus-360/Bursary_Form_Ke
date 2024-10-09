package com.kerugoya_bursary.form.sponsorship.controllers

import com.kerugoya_bursary.form.controllers.SponsorshipController
import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.services.SponsorshipDetailsService
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
import java.math.BigDecimal
import kotlin.test.Test

class SponsorshipControllerTest{
    @Mock
    private lateinit var service: SponsorshipDetailsService

    private lateinit var controller: SponsorshipController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        controller = SponsorshipController(service)
    }

    @Test
    @DisplayName("Should return all sponsorship details")
    fun getAllSponsorshipDetails() {
        val pageable = PageRequest.of(0, 10)
        val sponsorshipDetails =  createTestSponsorshipDetails()
        val page: Page<SponsorshipDetails> = PageImpl(listOf(sponsorshipDetails))

        `when`(service.getAllSponsorshipDetails(pageable)).thenReturn(page)

        val result = controller.getAllSponsors(pageable)

        assertEquals(1, result.body?.content?.size)
        assertEquals(sponsorshipDetails, result.body?.content?.get(0))
    }

    @Test
    @DisplayName("Should create a new sponsorship detail")
    fun createSponsorshipDetail() {
        val sponsorshipDetails =  createTestSponsorshipDetails()

        `when`(service.createSponsorshipDetails(sponsorshipDetails)).thenReturn(sponsorshipDetails)

        val result = controller.createSponsor(sponsorshipDetails)

        assertEquals(sponsorshipDetails, result.body)
    }

    @Test
    @DisplayName("Should return a sponsorship detail by id")
    fun getSponsorshipDetailById() {
        val sponsorshipDetails =  createTestSponsorshipDetails()
        val id = 1L

        `when`(service.getSponsorshipDetailsById(id)).thenReturn(sponsorshipDetails)

        val result = controller.getSponsorById(id)

        assertEquals(sponsorshipDetails, result.body)
    }

    @Test
    @DisplayName("Should update a sponsorship detail and return the updated detail")
    fun updateSponsorshipDetail() {
        val id = 1L
        val sponsorshipDetails = createTestSponsorshipDetails()
        val sponsorshipDetailsDto = createTestSponsorshipDetailsDto()

        `when`(service.updateSponsorshipDetails(id, sponsorshipDetailsDto)).thenReturn(sponsorshipDetails)

        val result: ResponseEntity<SponsorshipDetails> = controller.updateSponsor(id, sponsorshipDetailsDto)

        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(sponsorshipDetails, result.body)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing sponsorship detail")
    fun updateSponsorshipDetailNotFound() {
        val id = 1L
        val sponsorshipDetailsDto = createTestSponsorshipDetailsDto()

        `when`(service.updateSponsorshipDetails(id, sponsorshipDetailsDto)).thenThrow(ResourceNotFoundException::class.java)

        assertThrows(ResourceNotFoundException::class.java) {
            controller.updateSponsor(id, sponsorshipDetailsDto)
        }
    }

    @Test
    @DisplayName("Should delete a sponsorship detail by id")
    fun deleteSponsorshipDetailById() {
        val id = 1L

        Mockito.doNothing().`when`(service).deleteSponsorshipDetails(id)

        assertDoesNotThrow { controller.deleteSponsorsById(id) }
    }
}


private fun createTestSponsorshipDetails(): SponsorshipDetails {
    return SponsorshipDetails(
        id = 1,
        bursaryReceived = BigDecimal.valueOf(10000),
        feesRequired = BigDecimal.valueOf(20000),
        feesBalance = BigDecimal.valueOf(3000),
    )
}

private fun createTestSponsorshipDetailsDto(): SponsorshipDetailsDto {
    return SponsorshipDetailsDto(
        feesRequired = BigDecimal.valueOf(20000),
        feesBalance = BigDecimal.valueOf(3000),
        bursaryreceived = BigDecimal.valueOf(10000)
    )
}