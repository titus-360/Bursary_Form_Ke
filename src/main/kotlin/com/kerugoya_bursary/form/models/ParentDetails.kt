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
    var type: String,

    @Column(name = "surname")
    var surname: String?,

    @Column(name = "first_name")
    var firstName: String?,

    @Column(name = "other_names")
    var otherNames: String?,

    @Column(name = "id_number")
    var idNumber: String?,

    @Column(name = "age")
    var age: Int?,

    @Column(name = "phone")
    var phone: String?,

    @Column(name = "county")
    var county: String?,

    @Column(name = "sub_county")
    var subCounty: String?,

    @Column(name = "ward")
    var ward: String?,

    @Column(name = "occupation")
    var occupation: String?,

    @Column(name = "status")
    var status: String?,

    @Column(name = "relationship")
    var relationship: String?
) : BaseAudit()
