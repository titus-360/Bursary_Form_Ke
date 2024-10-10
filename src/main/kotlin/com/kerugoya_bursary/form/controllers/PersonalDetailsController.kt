package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.dtos.PersonalDetailsDto
import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.services.PersonalDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("personal-details")
class PersonalDetailsController(
    private val service: PersonalDetailsService
) {

    @GetMapping
    fun getAllPersonalDetails(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<Page<PersonalDetails>> {
        return ResponseEntity.ok(service.getAllPersonalDetails(pageable))
    }

    @PostMapping
    fun createPersonalDetail(
        @RequestBody personalDetails: PersonalDetails
    ): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.savePersonalDetails(personalDetails))
    }

    @GetMapping("{id}")
    fun getPersonalDetailById(@PathVariable id: Long): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.getPersonalDetailsById(id))
    }

    @PutMapping("{id}")
    fun updatePersonalDetail(
        @PathVariable id: Long,
        @RequestBody personalDetailsDto: PersonalDetailsDto
    ): ResponseEntity<PersonalDetailsDto> {
        return ResponseEntity.ok(service.updatePersonalDetails(id, personalDetailsDto))
    }


    @DeleteMapping("{id}")
    fun deletePersonalDetailById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deletePersonalDetailsById(id)
        return ResponseEntity.noContent().build()
    }

}
