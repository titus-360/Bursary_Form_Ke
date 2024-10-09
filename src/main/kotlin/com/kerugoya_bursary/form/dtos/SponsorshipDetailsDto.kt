package com.kerugoya_bursary.form.dtos

import java.math.BigDecimal

/**
 *
 * @author Titus Murithi Bundi
 */
data class SponsorshipDetailsDto(
    val feesRequired: BigDecimal = BigDecimal.ZERO,
    val bursaryreceived: BigDecimal = BigDecimal.ZERO,
    val feesBalance: BigDecimal = BigDecimal.ZERO,
)
