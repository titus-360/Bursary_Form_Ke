package com.kerugoya_bursary.form.services.serviceImp

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
): SponsorshipDetailsService {
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

    override fun updateSponsorshipDetails(sponsorshipDetails: SponsorshipDetails): SponsorshipDetails {
        return sponsorShipDetailsRepository.save(sponsorshipDetails)
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