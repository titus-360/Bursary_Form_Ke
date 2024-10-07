package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.SponsorshipDetails
import org.springframework.data.jpa.repository.JpaRepository

interface SponsorShipDetailsRepository: JpaRepository<SponsorshipDetails, Long> {

}