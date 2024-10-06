package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.models.SponsorshipDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SponsorshipDetailsService {
    fun getAllSponsorshipDetails(pageable: Pageable): Page<SponsorshipDetails>

    fun createSponsorshipDetails(sponsorshipDetails: SponsorshipDetails): SponsorshipDetails

    fun getSponsorshipDetailsById(id: Long): SponsorshipDetails

    fun updateSponsorshipDetails(sponsorshipDetails: SponsorshipDetails): SponsorshipDetails

    fun deleteSponsorshipDetails(id: Long)
}