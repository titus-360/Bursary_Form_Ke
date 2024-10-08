package com.kerugoya_bursary.form.dtos

/**
 *
 * @author Titus Murithi Bundi
 */
data class PersonalDetailsDto(
    val surname: String,
    val firstName: String,
    val otherNames: String?,
    val idNumber: String?,
    val gender: String,
    val dob: String,
    val phone: String,
    val county: String?,
    val subCounty: String?,
    val ward: String?,
    val school: String,
    val educationLevel: String,
    val admissionNumber: String?,
    val course: String?,
    val disability: Boolean?,
    val educationFinancier: String
)
