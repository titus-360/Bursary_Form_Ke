package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toEntity
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

    override fun savePersonalDetails(personalDetailsDto: PersonalDetailsDto): PersonalDetailsDto {
        val personalDetails = personalDetailsDto.toEntity()
        val savedPersonalDetails = personalDetailsRepository.save(personalDetails)
        return savedPersonalDetails.toDto()
    }

    override fun getPersonalDetailsById(id: Long): PersonalDetails? {
        return personalDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Personal Details with id $id not found") }
    }

    override fun updatePersonalDetails(id: Long, personalDetailsDto: PersonalDetailsDto): PersonalDetailsDto {
        val personalDetails = personalDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Personal Details with id $id not found") }

        personalDetails.apply {
            firstName = personalDetailsDto.firstName
            surname = personalDetailsDto.surname
            otherNames = personalDetailsDto.otherNames
            idNumber = personalDetailsDto.idNumber
            gender = personalDetailsDto.gender
            dob = personalDetailsDto.dob
            phone = personalDetailsDto.phone
            county = personalDetailsDto.county
            subCounty = personalDetailsDto.subCounty
            ward = personalDetailsDto.ward
            school = personalDetailsDto.school
            educationLevel = personalDetailsDto.educationLevel
            admissionNumber = personalDetailsDto.admissionNumber
            course = personalDetailsDto.course
            disability = personalDetailsDto.disability
            educationFinancier = personalDetailsDto.educationFinancier
        }
        val updatedPersonalDetails = personalDetailsRepository.save(personalDetails)
        return PersonalDetailsDto(
            firstName = updatedPersonalDetails.firstName,
            surname = updatedPersonalDetails.surname,
            otherNames = updatedPersonalDetails.otherNames,
            idNumber = updatedPersonalDetails.idNumber,
            gender = updatedPersonalDetails.gender,
            dob = updatedPersonalDetails.dob,
            phone = updatedPersonalDetails.phone,
            county = updatedPersonalDetails.county,
            subCounty = updatedPersonalDetails.subCounty,
            ward = updatedPersonalDetails.ward,
            school = updatedPersonalDetails.school,
            educationLevel = updatedPersonalDetails.educationLevel,
            admissionNumber = updatedPersonalDetails.admissionNumber,
            course = updatedPersonalDetails.course,
            disability = updatedPersonalDetails.disability,
            educationFinancier = updatedPersonalDetails.educationFinancier
        )
    }

    override fun deletePersonalDetailsById(id: Long) {
        val personalDetails = personalDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Personal Details with id $id not found") }
        if (personalDetails != null) {
            personalDetailsRepository.delete(personalDetails)
        }
    }

    override fun getAllPersonalDetails(pageable: Pageable): Page<PersonalDetails> {
        return personalDetailsRepository.findAll(pageable)
    }
}
