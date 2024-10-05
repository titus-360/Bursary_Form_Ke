package com.kerugoya_bursary.form.models


import jakarta.persistence.*

/**
 *
 * @author Titus Murithi Bundi
 */
@Entity
@Table(name = "personal_details")
data class PersonalDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_details_id_seq")
    @SequenceGenerator(
        name = "personal_details_id_seq",
        sequenceName = "personal_details_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "surname")
    val surname: String,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "other_names")
    val otherNames: String?,

    @Column(name = "id_number")
    val idNumber: String?,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "dob")
    val dob: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "county")
    val county: String?,

    @Column(name = "sub_county")
    val subCounty: String?,

    @Column(name = "ward")
    val ward: String?,

    @Column(name = "school")
    val school: String,

    @Column(name = "education_level")
    val educationLevel: String,

    @Column(name = "admission_number")
    val admissionNumber: String?,

    @Column(name = "course")
    val course: String?,

    @Column(name = "disability")
    val disability: Boolean?,

    @Column(name = "education_financier")
    val educationFinancier: String
)
