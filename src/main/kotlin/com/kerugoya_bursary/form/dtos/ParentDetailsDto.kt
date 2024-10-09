package com.kerugoya_bursary.form.dtos

/**
 *
 * @author Titus Murithi Bundi
 */
data class ParentDetailsDto(
    val id: Long? = 0,
    val type: String,
    val surname: String?,
    val firstName: String?,
    val otherNames: String?,
    val idNumber: String?,
    val age: Int?,
    val phone: String?,
    val county: String?,
    val subCounty: String?,
    val ward: String?,
    val occupation: String?,
    val status: String?,
    val relationship: String?
)
