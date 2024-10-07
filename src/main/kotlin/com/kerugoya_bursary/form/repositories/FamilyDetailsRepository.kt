package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.FamilyDetails
import org.springframework.data.jpa.repository.JpaRepository

interface FamilyDetailsRepository : JpaRepository<FamilyDetails, Long>