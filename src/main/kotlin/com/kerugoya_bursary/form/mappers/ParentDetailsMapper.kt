package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.models.ParentDetails

/**
 *
 * @author Titus Murithi Bundi
 */
object ParentDetailsMapper {

    private fun ParentDetails.toDto(): ParentDetailsDto {
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
}
