package com.kerugoya_bursary.form.dtos

/**
 *
 * @author Titus Murithi Bundi
 */
data class BursaryApplicationDto(
    var id: Long = 0,
    var personalDetails: PersonalDetailsDto,
    var familyDetails: FamilyDetailsDto,
    var sponsorshipDetails: SponsorshipDetailsDto,
    var declaration: String
)
