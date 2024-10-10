package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.BursaryApplicationDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toEntity
import com.kerugoya_bursary.form.mappers.SponsorShipDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.SponsorShipDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.BursaryApplication

object BursaryApplicationMapper {

    fun BursaryApplicationDto.toEntity(): BursaryApplication {
        return BursaryApplication(
            id = this.id,
            personalDetails = this.personalDetails.toEntity(),
            familyDetails = this.familyDetails.toEntity(),
            sponsorshipDetails = this.sponsorshipDetails.toEntity(),
            declaration = this.declaration
        )
    }

    fun BursaryApplication.toDto(): BursaryApplicationDto {
        return BursaryApplicationDto(
            id = this.id,
            personalDetails = this.personalDetails.toDto(),
            familyDetails = this.familyDetails.toDto(),
            sponsorshipDetails = this.sponsorshipDetails.toDto(),
            declaration = this.declaration
        )
    }
}
