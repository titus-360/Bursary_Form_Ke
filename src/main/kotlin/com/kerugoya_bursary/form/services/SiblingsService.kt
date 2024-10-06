package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.models.Siblings
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SiblingsService {

    fun getAllSiblings(pageable: Pageable): Page<Siblings>

    fun createSiblings(siblings: Siblings): Siblings

    fun getSiblingsById(id: Long): Siblings

    fun updateSiblings(siblings: Siblings): Siblings

    fun deleteSiblings(id: Long)
}