package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.models.SponsorshipDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SponsorshipDetailsService {
    fun getAllSponsorshipDetails(pageable: Pageable): Page<SponsorshipDetails>

    fun createSponsorshipDetails(sponsorshipDetailsDto: SponsorshipDetailsDto): SponsorshipDetailsDto

    fun getSponsorshipDetailsById(id: Long): SponsorshipDetails

    fun updateSponsorshipDetails(id: Long, sponsorshipDetailsDto: SponsorshipDetailsDto): SponsorshipDetailsDto

    fun deleteSponsorshipDetails(id: Long)
}
