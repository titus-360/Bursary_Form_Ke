package com.kerugoya_bursary.form.sponsorship.services.serviceImp

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.repositories.SponsorShipDetailsRepository
import com.kerugoya_bursary.form.services.serviceImp.SponsorshipDetailsServiceImp
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
import java.math.BigDecimal
import java.util.*
import kotlin.test.Test

class SponsorshipDetailsServiceImpTest{

    @Mock
    private lateinit var repository: SponsorShipDetailsRepository

    private lateinit var service: SponsorshipDetailsServiceImp

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = SponsorshipDetailsServiceImp(repository)
    }

    @Test
    @DisplayName("Should return all sponsorship details")
    fun getAllSponsorshipDetails() {
        val pageable = PageRequest.of(0, 10)
        val sponsorshipDetails =  createTestSponsorshipDetails()
        val page: Page<SponsorshipDetails> = PageImpl(listOf(sponsorshipDetails))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllSponsorshipDetails(pageable)

        assertEquals(1, result.content.size)
        assertEquals(sponsorshipDetails, result.content[0])
    }

    @Test
    @DisplayName("Should create a new sponsorship detail")
    fun createSponsorshipDetails() {
        val sponsorshipDetails =  createTestSponsorshipDetails()

        `when`(repository.save(sponsorshipDetails)).thenReturn(sponsorshipDetails)

        val result = service.createSponsorshipDetails(sponsorshipDetails)

        assertEquals(sponsorshipDetails, result)
    }

    @Test
    @DisplayName("Should return a sponsorship detail by id")
    fun getSponsorshipDetailsById() {
        val sponsorshipDetails = createTestSponsorshipDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(sponsorshipDetails))

        val result = service.getSponsorshipDetailsById(id)

        assertEquals(sponsorshipDetails, result)
    }

    @Test
    @DisplayName("Should throw an exception when sponsorship detail not found by id")
    fun getSponsorshipDetailsByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.getSponsorshipDetailsById(id)
        }
    }

    @Test
    @DisplayName("Should update a sponsorship detail")
    fun updateSponsorshipDetails() {
        val id = 1L
        val sponsorshipDetails = createTestSponsorshipDetails()
        val sponsorshipDetailsDto = createTestSponsorshipDetailsDto()

        `when`(repository.findById(id)).thenReturn(Optional.of(sponsorshipDetails))
        `when`(repository.save(sponsorshipDetails)).thenReturn(sponsorshipDetails)

        val result = service.updateSponsorshipDetails(id, sponsorshipDetailsDto)

        assertEquals(sponsorshipDetailsDto.feesRequired, result.feesRequired)
        assertEquals(sponsorshipDetailsDto.bursaryreceived, result.bursaryReceived)
        assertEquals(sponsorshipDetailsDto.feesBalance, result.feesBalance)
    }

    @Test
    @DisplayName("Should throw an exception when updating a non-existing sponsorship detail")
    fun updateSponsorshipDetailsNotFound() {
        val id = 1L
        val sponsorshipDetailsDto = createTestSponsorshipDetailsDto()

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.updateSponsorshipDetails(id, sponsorshipDetailsDto)
        }
    }

    @Test
    @DisplayName("Should delete a sponsorship detail by id")
    fun deleteSponsorshipDetails() {
        val sponsorshipDetails = createTestSponsorshipDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(sponsorshipDetails))

        Mockito.doNothing().`when`(repository).delete(sponsorshipDetails)

        assertDoesNotThrow { service.deleteSponsorshipDetails(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing sponsorship detail")
    fun deleteSponsorshipDetailsNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.deleteSponsorshipDetails(id)
        }
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