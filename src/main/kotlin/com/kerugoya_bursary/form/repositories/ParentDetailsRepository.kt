package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.ParentDetails
import org.springframework.data.jpa.repository.JpaRepository

interface ParentDetailsRepository : JpaRepository<ParentDetails, Long>