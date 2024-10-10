package com.kerugoya_bursary.form.dtos

import java.math.BigDecimal

/**
 *
 * @author Titus Murithi Bundi
 */
data class SponsorshipDetailsDto(
    val id: Long = 0L,
    val feesRequired: BigDecimal = BigDecimal.ZERO,
    val bursaryReceived: BigDecimal = BigDecimal.ZERO,
    val feesBalance: BigDecimal = BigDecimal.ZERO,
)
