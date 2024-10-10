package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.dtos.BursaryApplicationDto
import com.kerugoya_bursary.form.mappers.BursaryApplicationMapper.toDto
import com.kerugoya_bursary.form.mappers.BursaryApplicationMapper.toEntity
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.mappers.PersonalDetailsMapper.toEntity
import com.kerugoya_bursary.form.mappers.SponsorShipDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.BursaryApplication
import com.kerugoya_bursary.form.repositories.BursaryApplicationRepository
import com.kerugoya_bursary.form.services.BursaryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BursaryServiceImpl(
    private val repository: BursaryApplicationRepository
) : BursaryService {
    override fun getAllBursaryApplications(pageable: Pageable): Page<BursaryApplication> {
        return repository.findAll(pageable)
    }

    override fun createBursaryApplication(bursaryApplicationDto: BursaryApplicationDto): BursaryApplicationDto {
        val bursaryApplication = bursaryApplicationDto.toEntity()
        return repository.save(bursaryApplication).toDto()
    }

    override fun getBursaryApplicationById(id: Long): BursaryApplication {
        return repository.findById(id).orElseThrow {
            throw IllegalArgumentException("Bursary Application with id $id not found")
        }
    }

    override fun updateBursaryApplication(bursaryApplicationDto: BursaryApplicationDto): BursaryApplicationDto {
        val existingBursaryApplication = repository.findById(bursaryApplicationDto.id).orElseThrow {
            throw IllegalArgumentException("Bursary Application with id ${bursaryApplicationDto.id} not found")
        }
        existingBursaryApplication.apply {
            id = bursaryApplicationDto.id
            personalDetails = bursaryApplicationDto.personalDetails.toEntity()
            familyDetails = bursaryApplicationDto.familyDetails.toEntity()
            sponsorshipDetails = bursaryApplicationDto.sponsorshipDetails.toEntity()
            declaration = bursaryApplicationDto.declaration
        }
        return repository.save(existingBursaryApplication).toDto()
    }

    override fun deleteBursaryApplication(id: Long) {
        val bursaryApplication = repository.findById(id).orElseThrow {
            throw IllegalArgumentException("Bursary Application with id $id not found")
        }
        if (bursaryApplication != null) {
            repository.delete(bursaryApplication)
        }
    }

}
