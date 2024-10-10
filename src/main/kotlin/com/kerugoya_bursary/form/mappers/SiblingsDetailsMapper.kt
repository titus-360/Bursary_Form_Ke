package com.kerugoya_bursary.form.mappers

import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.models.Siblings

/**
 *
 * @author Titus Murithi Bundi
 */
object SiblingsDetailsMapper {
    private fun Siblings.toDto(): SiblingsDto {
        return SiblingsDto(
            primarySchool = this.primarySchool,
            secondarySchool = this.secondarySchool,
            university = this.university,
            tertiaryCollege = this.tertiaryCollege
        )
    }

    fun SiblingsDto.toEntity(): Siblings {
        return Siblings(
            primarySchool = this.primarySchool,
            secondarySchool = this.secondarySchool,
            university = this.university,
            tertiaryCollege = this.tertiaryCollege
        )
    }
}
