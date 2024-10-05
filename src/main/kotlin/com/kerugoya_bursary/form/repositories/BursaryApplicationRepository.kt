package com.kerugoya_bursary.form.repositories

import com.kerugoya_bursary.form.models.BursaryApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 *
 * @author Titus Murithi Bundi
 */
@Repository
interface BursaryApplicationRepository : JpaRepository<BursaryApplication, Long> {
}
