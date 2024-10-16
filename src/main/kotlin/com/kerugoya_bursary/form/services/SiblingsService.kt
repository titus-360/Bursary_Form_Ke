package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.models.Siblings
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SiblingsService {

    fun getAllSiblings(pageable: Pageable): Page<Siblings>

    fun createSiblings(siblingsDto: SiblingsDto): SiblingsDto

    fun getSiblingsById(id: Long): Siblings

    fun updateSiblings(id : Long, siblingsDto: SiblingsDto): SiblingsDto

    fun deleteSiblings(id: Long)
}
