package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.models.PersonalDetails

/**
 *
 * @author Titus Murithi Bundi
 */
object PersonalDetailsMapper {

    fun PersonalDetailsDto.toEntity(): PersonalDetails {
        return PersonalDetails(
            surname = this.surname,
            firstName = this.firstName,
            otherNames = this.otherNames,
            idNumber = this.idNumber,
            gender = this.gender,
            dob = this.dob,
            phone = this.phone,
            county = this.county,
            subCounty = this.subCounty,
            ward = this.ward,
            school = this.school,
            educationLevel = this.educationLevel,
            admissionNumber = this.admissionNumber,
            course = this.course,
            disability = this.disability,
            educationFinancier = this.educationFinancier
        )
    }

    fun PersonalDetails.toDto(): PersonalDetailsDto {
        return PersonalDetailsDto(
            surname = this.surname,
            firstName = this.firstName,
            otherNames = this.otherNames,
            idNumber = this.idNumber,
            gender = this.gender,
            dob = this.dob,
            phone = this.phone,
            county = this.county,
            subCounty = this.subCounty,
            ward = this.ward,
            school = this.school,
            educationLevel = this.educationLevel,
            admissionNumber = this.admissionNumber,
            course = this.course,
            disability = this.disability,
            educationFinancier = this.educationFinancier
        )
    }
}
