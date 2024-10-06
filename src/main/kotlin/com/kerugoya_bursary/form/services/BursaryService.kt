package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.models.BursaryApplication
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BursaryService {
    fun getAllBursaryApplications(pageable: Pageable): Page<BursaryApplication>

    fun createBursaryApplication(bursaryApplication: BursaryApplication): BursaryApplication

    fun getBursaryApplicationById(id: Long): BursaryApplication

    fun updateBursaryApplication(bursaryApplication: BursaryApplication): BursaryApplication

    fun deleteBursaryApplication(id: Long)
}