package com.kerugoya_bursary.form.services.serviceImp

import com.kerugoya_bursary.form.models.BursaryApplication
import com.kerugoya_bursary.form.repositories.BursaryApplicationRepository
import com.kerugoya_bursary.form.services.BursaryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BursaryServiceImpl(
    private val repository: BursaryApplicationRepository
): BursaryService {
    override fun getAllBursaryApplications(pageable: Pageable): Page<BursaryApplication> {
        return repository.findAll(pageable)
    }

    override fun createBursaryApplication(bursaryApplication: BursaryApplication): BursaryApplication {
        return repository.save(bursaryApplication)
    }

    override fun getBursaryApplicationById(id: Long): BursaryApplication {
        return repository.findById(id).orElseThrow {
            throw IllegalArgumentException("Bursary Application with id $id not found")
        }
    }

    override fun updateBursaryApplication(bursaryApplication: BursaryApplication): BursaryApplication {
        return repository.save(bursaryApplication)
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