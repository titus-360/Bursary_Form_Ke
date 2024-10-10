package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.models.SponsorshipDetails
import java.math.BigDecimal

/**
 *
 * @author Titus Murithi Bundi
 */
object SponsorShipDetailsMapper {

    fun SponsorshipDetailsDto.toEntity(): SponsorshipDetails {
        return SponsorshipDetails(
            feesRequired = this.feesRequired,
            feesBalance = this.feesBalance,
            bursaryReceived = this.bursaryReceived
        )
    }

    fun SponsorshipDetails.toDto(): SponsorshipDetailsDto {
        return SponsorshipDetailsDto(
            feesRequired = this.feesRequired ?: BigDecimal.ZERO,
            feesBalance = this.feesBalance ?: BigDecimal.ZERO,
            bursaryReceived = this.bursaryReceived ?: BigDecimal.ZERO
        )
    }
}
