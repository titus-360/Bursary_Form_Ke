package com.kerugoya_bursary.form.models

import jakarta.persistence.*

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
    val feesRequired: Int,

    @Column(name = "bursary_received")
    val bursaryReceived: Int?,

    @Column(name = "fees_balance")
    val feesBalance: Int
)
