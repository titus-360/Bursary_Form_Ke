package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.dtos.FamilyDetailsDto
import com.kerugoya_bursary.form.models.FamilyDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FamilyDetailsService {

    fun getAllFamilyDetails(pageable: Pageable): Page<FamilyDetails>

    fun getFamilyDetailsById(id: Long): FamilyDetails?

    fun createFamilyDetails(familyDetails: FamilyDetails): FamilyDetails

    fun updateFamilyDetails(id : Long, familyDetailsDto: FamilyDetailsDto): FamilyDetailsDto

    fun deleteFamilyDetails(id: Long)
}
