package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.exception.ResourceNotFoundException
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

    override fun createParentDetails(parentDetails: ParentDetails): ParentDetails {
        return parentDetailsRepository.save(parentDetails)
    }

    override fun getParentDetailsById(id: Long): ParentDetails {
        return parentDetailsRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Parent Details with id $id not found") }
    }

    override fun updateParentDetails(parentDetails: ParentDetails): ParentDetails {
        return parentDetailsRepository.save(parentDetails)
    }
}