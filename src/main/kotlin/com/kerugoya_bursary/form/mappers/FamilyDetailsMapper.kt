package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.models.Siblings

/**
 *
 * @author Titus Murithi Bundi
 */
object FamilyDetailsMapper {

    fun ParentDetailsDto.toEntity(): ParentDetails {
        return ParentDetails(
            id = this.id,
            type = this.type,
            surname = this.surname,
            firstName = this.firstName,
            otherNames = this.otherNames,
            idNumber = this.idNumber,
            age = this.age,
            phone = this.phone,
            county = this.county,
            subCounty = this.subCounty,
            ward = this.ward,
            occupation = this.occupation,
            status = this.status,
            relationship = this.relationship
        )
    }

    fun SiblingsDto.toEntity(): Siblings {
        return Siblings(
            id = this.id,
            primarySchool = this.primarySchool,
            secondarySchool = this.secondarySchool,
            tertiaryCollege = this.tertiaryCollege,
            university = this.university
        )
    }

    fun ParentDetails.toDto(): ParentDetailsDto {
        return ParentDetailsDto(
            id = this.id,
            type = this.type,
            surname = this.surname,
            firstName = this.firstName,
            otherNames = this.otherNames,
            idNumber = this.idNumber,
            age = this.age,
            phone = this.phone,
            county = this.county,
            subCounty = this.subCounty,
            ward = this.ward,
            occupation = this.occupation,
            status = this.status,
            relationship = this.relationship
        )
    }

    fun Siblings.toDto(): SiblingsDto {
        return SiblingsDto(
            id = this.id,
            primarySchool = this.primarySchool,
            secondarySchool = this.secondarySchool,
            tertiaryCollege = this.tertiaryCollege,
            university = this.university
        )
    }
}
