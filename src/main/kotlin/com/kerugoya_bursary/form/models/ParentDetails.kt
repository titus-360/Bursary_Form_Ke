package com.kerugoya_bursary.form.models

import jakarta.persistence.*

/**
 *
 * @author Titus Murithi Bundi
 */
@Entity
@Table(name = "parent_details")
data class ParentDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_details_id_seq")
    @SequenceGenerator(
        name = "parent_details_id_seq",
        sequenceName = "parent_details_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "type")
    val type: String,

    @Column(name = "surname")
    val surname: String?,

    @Column(name = "first_name")
    val firstName: String?,

    @Column(name = "other_names")
    val otherNames: String?,

    @Column(name = "id_number")
    val idNumber: String?,

    @Column(name = "age")
    val age: Int?,

    @Column(name = "phone")
    val phone: String?,

    @Column(name = "county")
    val county: String?,

    @Column(name = "sub_county")
    val subCounty: String?,

    @Column(name = "ward")
    val ward: String?,

    @Column(name = "occupation")
    val occupation: String?,

    @Column(name = "status")
    val status: String?,

    @Column(name = "relationship")
    val relationship: String?
)
