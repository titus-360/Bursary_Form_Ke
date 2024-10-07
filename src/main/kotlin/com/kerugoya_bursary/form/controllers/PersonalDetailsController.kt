package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.services.PersonalDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("kerugoya_bursary/personal-details/")
class PersonalDetailsController(
    private val service: PersonalDetailsService
) {

    @GetMapping("get-all-personal-details")
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

    @PostMapping("save-personal-details")
    fun createPersonalDetail(
        @RequestBody personalDetails: PersonalDetails
    ): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.savePersonalDetails(personalDetails))
    }

    @GetMapping("get-personal-details-by-id/{id}")
    fun getPersonalDetailById(@PathVariable id: Long): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.getPersonalDetailsById(id))
    }

    @PutMapping("update-personal-details")
    fun updatePersonalDetail(
        @RequestBody personalDetails: PersonalDetails
    ): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.updatePersonalDetails(personalDetails))
    }


    @DeleteMapping("delete-personal-details-by-id/{id}")
    fun deletePersonalDetailById(@PathVariable id: Long):ResponseEntity<Void>  {
        service.deletePersonalDetailsById(id)
        return ResponseEntity.noContent().build()
    }

}
