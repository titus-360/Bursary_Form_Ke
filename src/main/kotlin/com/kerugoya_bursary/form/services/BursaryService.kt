package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.dtos.BursaryApplicationDto
import com.kerugoya_bursary.form.models.BursaryApplication
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BursaryService {
    fun getAllBursaryApplications(pageable: Pageable): Page<BursaryApplication>

    fun createBursaryApplication(bursaryApplicationDto: BursaryApplicationDto): BursaryApplicationDto

    fun getBursaryApplicationById(id: Long): BursaryApplication

    fun updateBursaryApplication(bursaryApplicationDto: BursaryApplicationDto): BursaryApplicationDto

    fun deleteBursaryApplication(id: Long)
}
