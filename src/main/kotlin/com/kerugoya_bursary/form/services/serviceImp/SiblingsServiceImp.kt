package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.repositories.SiblingsRepository
import com.kerugoya_bursary.form.services.SiblingsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SiblingsServiceImp(
    private val siblingsRepository: SiblingsRepository
): SiblingsService
{
    override fun getAllSiblings(pageable: Pageable): Page<Siblings> {
        return siblingsRepository.findAll(pageable)
    }

    override fun createSiblings(siblings: Siblings): Siblings {
        return siblingsRepository.save(siblings)
    }

    override fun getSiblingsById(id: Long): Siblings {
        return siblingsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Siblings with id $id not found")
        }
    }

    override fun updateSiblings(siblings: Siblings): Siblings {
        return siblingsRepository.save(siblings)
    }

    override fun deleteSiblings(id: Long) {
        val siblings = siblingsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Siblings with id $id not found")
        }
        if (siblings != null) {
            siblingsRepository.delete(siblings)
        }
    }


}