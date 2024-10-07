package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.PersonalDetails
import org.springframework.data.jpa.repository.JpaRepository

interface PersonalDetailsRepository: JpaRepository<PersonalDetails, Long>
