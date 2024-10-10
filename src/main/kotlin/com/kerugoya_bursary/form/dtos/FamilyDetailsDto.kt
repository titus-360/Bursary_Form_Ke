package com.kerugoya_bursary.form.dtos

/**
 *
 * @author Titus Murithi Bundi
 */
data class FamilyDetailsDto(
    var id: Long? = 0,
    var parents: List<ParentDetailsDto>? = emptyList(),
    var siblings: SiblingsDto?,
)
