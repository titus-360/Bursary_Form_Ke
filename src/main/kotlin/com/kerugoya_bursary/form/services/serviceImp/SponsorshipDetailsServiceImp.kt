package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.repositories.SponsorShipDetailsRepository
import com.kerugoya_bursary.form.services.SponsorshipDetailsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class SponsorshipDetailsServiceImp(
    private val sponsorShipDetailsRepository: SponsorShipDetailsRepository
) : SponsorshipDetailsService {
    override fun getAllSponsorshipDetails(pageable: Pageable): Page<SponsorshipDetails> {
        return sponsorShipDetailsRepository.findAll(pageable)
    }

    override fun createSponsorshipDetails(sponsorshipDetails: SponsorshipDetails): SponsorshipDetails {
        return sponsorShipDetailsRepository.save(sponsorshipDetails)
    }

    override fun getSponsorshipDetailsById(id: Long): SponsorshipDetails {
        return sponsorShipDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("SponsorshipDetails with id $id not found")
        }
    }

    override fun updateSponsorshipDetails(
        id: Long,
        sponsorshipDetailsDto: SponsorshipDetailsDto
    ): SponsorshipDetailsDto {

        val existingSponsorshipDetails = sponsorShipDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("SponsorshipDetails with id $id not found")
        }
        existingSponsorshipDetails.apply {
            feesRequired = sponsorshipDetailsDto.feesRequired
            bursaryReceived = sponsorshipDetailsDto.bursaryReceived
            feesBalance = sponsorshipDetailsDto.feesBalance
        }
        val updatedSponsorshipDetails = sponsorShipDetailsRepository.save(existingSponsorshipDetails)
        return SponsorshipDetailsDto(
            id,
            feesRequired = updatedSponsorshipDetails.feesRequired ?: BigDecimal.ZERO,
            bursaryReceived = updatedSponsorshipDetails.bursaryReceived ?: BigDecimal.ZERO,
            feesBalance = updatedSponsorshipDetails.feesBalance ?: BigDecimal.ZERO
        )
    }

    override fun deleteSponsorshipDetails(id: Long) {
        val sponsorshipDetails = sponsorShipDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("SponsorshipDetails with id $id not found")
        }
        if (sponsorshipDetails != null) {
            sponsorShipDetailsRepository.delete(sponsorshipDetails)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SponsorshipDetailsServiceImp::class.java)
    }


}
