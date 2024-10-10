package com.kerugoya_bursary.form.services

import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.models.PersonalDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

interface PersonalDetailsService {

    fun savePersonalDetails(personalDetailsDto: PersonalDetailsDto): PersonalDetailsDto

    fun getPersonalDetailsById(id: Long): PersonalDetails?

    fun updatePersonalDetails(id : Long, personalDetailsDto: PersonalDetailsDto): PersonalDetailsDto

    fun deletePersonalDetailsById(id: Long)

    fun getAllPersonalDetails(pageable: Pageable): Page<PersonalDetails>
}
