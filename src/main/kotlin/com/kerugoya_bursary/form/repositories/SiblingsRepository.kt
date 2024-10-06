package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.Siblings
import org.springframework.data.jpa.repository.JpaRepository

interface SiblingsRepository : JpaRepository<Siblings, Long> {
}