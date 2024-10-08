package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.repositories.SponsorShipDetailsRepository
import com.kerugoya_bursary.form.services.SponsorshipDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


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

    override fun updateSponsorshipDetails(id: Long, sponsorshipDetailsDto: SponsorshipDetailsDto): SponsorshipDetails {
        val existingSponsorshipDetails = sponsorShipDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("SponsorshipDetails with id $id not found")
        }
        existingSponsorshipDetails.apply {
            feesRequired = sponsorshipDetailsDto.feesRequired
            bursaryReceived = sponsorshipDetailsDto.bursaryreceived
            feesBalance = sponsorshipDetailsDto.feesBalance
        }
        return sponsorShipDetailsRepository.save(existingSponsorshipDetails)
    }

    override fun deleteSponsorshipDetails(id: Long) {
        val sponsorshipDetails = sponsorShipDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("SponsorshipDetails with id $id not found")
        }
        if (sponsorshipDetails != null) {
            sponsorShipDetailsRepository.delete(sponsorshipDetails)
        }
    }


}
