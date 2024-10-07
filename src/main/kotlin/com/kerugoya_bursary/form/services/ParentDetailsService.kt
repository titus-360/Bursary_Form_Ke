package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.models.ParentDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ParentDetailsService {

    fun getAllParentDetails(pageable: Pageable): Page<ParentDetails>

    fun createParentDetails(parentDetails: ParentDetails): ParentDetails

    fun getParentDetailsById(id: Long): ParentDetails

    fun updateParentDetails(parentDetails: ParentDetails): ParentDetails

    fun deleteParentDetails(id: Long)
}