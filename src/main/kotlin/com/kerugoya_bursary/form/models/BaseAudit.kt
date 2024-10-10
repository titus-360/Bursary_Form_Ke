package com.kerugoya_bursary.form.models

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 *
 * @author Titus Murithi Bundi
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAudit {

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "last_modified_date")
    lateinit var lastModifiedDate: LocalDateTime

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null

    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: String? = null


}
