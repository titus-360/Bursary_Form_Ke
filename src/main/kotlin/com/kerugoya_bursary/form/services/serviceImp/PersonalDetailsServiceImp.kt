package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.repositories.PersonalDetailsRepository
import com.kerugoya_bursary.form.services.PersonalDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PersonalDetailsServiceImp(
    private val personalDetailsRepository: PersonalDetailsRepository
) : PersonalDetailsService {
    override fun savePersonalDetails(personalDetails: PersonalDetails): PersonalDetails {
        return personalDetailsRepository.save(personalDetails)
    }

    override fun getPersonalDetailsById(id: Long): PersonalDetails? {
        return personalDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Personal Details with id $id not found") }
    }

    override fun updatePersonalDetails(personalDetails: PersonalDetails): PersonalDetails {
        return personalDetailsRepository.save(personalDetails)
    }

    override fun deletePersonalDetailsById(id: Long) {
        val personalDetails = personalDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Personal Details with id $id not found") }
        if(personalDetails != null) {
            personalDetailsRepository.delete(personalDetails)
        }
    }

    override fun getAllPersonalDetails(pageable: Pageable): Page<PersonalDetails> {
        return personalDetailsRepository.findAll(pageable)
    }
}