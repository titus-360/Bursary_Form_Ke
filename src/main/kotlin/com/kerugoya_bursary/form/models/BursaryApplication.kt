package com.kerugoya_bursary.form.models

import jakarta.persistence.*

/**
 *
 * @author Titus Murithi Bundi
 */

@Entity
@Table(name = "bursary_application")
data class BursaryApplication(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bursary_application_id_seq")
    @SequenceGenerator(
        name = "bursary_application_id_seq",
        sequenceName = "bursary_application_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "personal_details_id")
    val personalDetails: PersonalDetails,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "family_details_id")
    val familyDetails: FamilyDetails,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sponsorship_details_id")
    val sponsorshipDetails: SponsorshipDetails,

    @Column(name = "declaration")
    val declaration: String
)
