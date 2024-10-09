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
    var surname: String,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "other_names")
    var otherNames: String?,

    @Column(name = "id_number")
    var idNumber: String?,

    @Column(name = "gender")
    var gender: String,

    @Column(name = "dob")
    var dob: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "county")
    var county: String?,

    @Column(name = "sub_county")
    var subCounty: String?,

    @Column(name = "ward")
    var ward: String?,

    @Column(name = "school")
    var school: String,

    @Column(name = "education_level")
    var educationLevel: String,

    @Column(name = "admission_number")
    var admissionNumber: String?,

    @Column(name = "course")
    var course: String?,

    @Column(name = "disability")
    var disability: Boolean?,

    @Column(name = "education_financier")
    var educationFinancier: String
)
