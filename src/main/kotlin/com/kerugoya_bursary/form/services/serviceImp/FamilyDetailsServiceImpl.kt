package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.exception.ResourceNotFoundException
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

    override fun createFamilyDetails(familyDetails: FamilyDetails): FamilyDetails {
        return familyDetailsRepository.save(familyDetails)
    }

    override fun updateFamilyDetails(updatedFamilyDetails: FamilyDetails): FamilyDetails {
        return familyDetailsRepository.save(updatedFamilyDetails)
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