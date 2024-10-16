package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.repositories.ParentDetailsRepository
import com.kerugoya_bursary.form.services.ParentDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ParentDetailsServiceImp(
    private val parentDetailsRepository: ParentDetailsRepository
) : ParentDetailsService {
    override fun getAllParentDetails(pageable: Pageable): Page<ParentDetails> {
        return parentDetailsRepository.findAll(pageable)
    }

    override fun deleteParentDetails(id: Long) {
        val parentDetails = parentDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Parent Details with id $id not found")
        }
        if (parentDetails != null) {
            parentDetailsRepository.delete(parentDetails)
        }
    }

    override fun createParentDetails(parentDetailsDto: ParentDetailsDto): ParentDetailsDto {
        val parentDetails = parentDetailsDto.toEntity()
        val savedParentDetails = parentDetailsRepository.save(parentDetails)
        return savedParentDetails.toDto()
    }

    override fun getParentDetailsById(id: Long): ParentDetails {
        return parentDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Parent Details with id $id not found") }
    }

    override fun updateParentDetails(id: Long, parentDetailsDto: ParentDetailsDto): ParentDetails {
        val parentDetails = parentDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Parent Details with id $id not found") }

        parentDetails.apply {
            surname = parentDetailsDto.surname
            otherNames = parentDetailsDto.otherNames
            idNumber = parentDetailsDto.idNumber
            phone = parentDetailsDto.phone
            county = parentDetailsDto.county
            subCounty = parentDetailsDto.subCounty
            ward = parentDetailsDto.ward
            occupation = parentDetailsDto.occupation
            age = parentDetailsDto.age
            status = parentDetailsDto.status
            relationship = parentDetailsDto.relationship
            firstName = parentDetailsDto.firstName
            type = parentDetailsDto.type

        }
        return parentDetailsRepository.save(parentDetails)
    }
}
