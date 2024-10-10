package com.kerugoya_bursary.form.models

import jakarta.persistence.*

/**
 *
 * @author Titus Murithi Bundi
 */

@Entity
@Table(name = "family_details")
data class FamilyDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "family_details_id_seq")
    @SequenceGenerator(
        name = "family_details_id_seq",
        sequenceName = "family_details_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "family_details_id")
    var parents: List<ParentDetails>?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "siblings_id")
    var siblings: Siblings?
)
