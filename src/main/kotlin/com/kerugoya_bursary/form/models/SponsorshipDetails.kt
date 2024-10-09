package com.kerugoya_bursary.form.models

import jakarta.persistence.*
import java.math.BigDecimal

/**
 *
 * @author Titus Murithi Bundi
 */
@Entity
@Table(name = "sponsorship_details")
data class SponsorshipDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsorship_details_id_seq")
    @SequenceGenerator(
        name = "sponsorship_details_id_seq",
        sequenceName = "sponsorship_details_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "fees_required")
    var feesRequired: BigDecimal? = null,

    @Column(name = "bursary_received")
    var bursaryReceived: BigDecimal? = null,

    @Column(name = "fees_balance")
    var feesBalance: BigDecimal? = null
)
