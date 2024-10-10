package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.FamilyDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.FamilyDetails
import com.kerugoya_bursary.form.repositories.FamilyDetailsRepository
import com.kerugoya_bursary.form.services.FamilyDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FamilyDetailsServiceImpl(
    private val familyDetailsRepository: FamilyDetailsRepository
) : FamilyDetailsService {
    override fun getAllFamilyDetails(pageable: Pageable): Page<FamilyDetails> {
        return familyDetailsRepository.findAll(pageable)
    }

    override fun getFamilyDetailsById(id: Long): FamilyDetails? {
        return familyDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Family Details with id $id not found")
        }
    }

    override fun createFamilyDetails(familyDetailsDto: FamilyDetailsDto): FamilyDetailsDto {
        val familyDetails = FamilyDetails(
            parents = familyDetailsDto.parents?.map { it.toEntity() },
            siblings = familyDetailsDto.siblings?.toEntity()
        )
        val savedFamilyDetails = familyDetailsRepository.save(familyDetails)
        return FamilyDetailsDto(
            id = savedFamilyDetails.id,
            parents = savedFamilyDetails.parents?.map { it.toDto() },
            siblings = savedFamilyDetails.siblings?.toDto()
        )
    }

    override fun updateFamilyDetails(id: Long, familyDetailsDto: FamilyDetailsDto): FamilyDetailsDto {
        val existingFamilyDetails = familyDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Family Details with id $id not found")
        }

        val updatedFamilyDetails = existingFamilyDetails.apply {
            this.parents = familyDetailsDto.parents?.map { it.toEntity() }
            this.siblings = familyDetailsDto.siblings?.toEntity()
        }

        val savedFamilyDetails = familyDetailsRepository.save(updatedFamilyDetails)
        return FamilyDetailsDto(
            id = savedFamilyDetails.id,
            parents = savedFamilyDetails.parents?.map { it.toDto() },
            siblings = savedFamilyDetails.siblings?.toDto()
        )
    }

    override fun deleteFamilyDetails(id: Long) {
        val familyDetails = familyDetailsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Family Details with id $id not found")
        }
        if (familyDetails != null) {
            familyDetailsRepository.delete(familyDetails)
        }
    }
}
