package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.SiblingsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.SiblingsDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.repositories.SiblingsRepository
import com.kerugoya_bursary.form.services.SiblingsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SiblingsServiceImp(
    private val siblingsRepository: SiblingsRepository
) : SiblingsService {
    override fun getAllSiblings(pageable: Pageable): Page<Siblings> {
        return siblingsRepository.findAll(pageable)
    }

    override fun createSiblings(siblingsDto: SiblingsDto): SiblingsDto {
        val siblings = siblingsDto.toEntity()
        val savedSiblings = siblingsRepository.save(siblings)
        return savedSiblings.toDto()
    }

    override fun getSiblingsById(id: Long): Siblings {
        return siblingsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Siblings with id $id not found")
        }
    }

    override fun updateSiblings(id: Long, siblingsDto: SiblingsDto): SiblingsDto {
        val existingSiblings = siblingsRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Siblings with id $id not found")
        }
        existingSiblings.primarySchool = siblingsDto.primarySchool
        existingSiblings.secondarySchool = siblingsDto.secondarySchool
        existingSiblings.university = siblingsDto.university
        existingSiblings.tertiaryCollege = siblingsDto.tertiaryCollege

        val updatedSiblings = siblingsRepository.save(existingSiblings)
        return SiblingsDto(
            id = updatedSiblings.id,
            primarySchool = updatedSiblings.primarySchool ?: 0,
            secondarySchool = updatedSiblings.secondarySchool ?: 0,
            university = updatedSiblings.university ?: 0,
            tertiaryCollege = updatedSiblings.tertiaryCollege ?: 0
        )
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
