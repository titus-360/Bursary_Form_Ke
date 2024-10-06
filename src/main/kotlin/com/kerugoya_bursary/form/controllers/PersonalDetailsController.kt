package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.PersonalDetails
import com.kerugoya_bursary.form.services.PersonalDetailsService
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

    @GetMapping
    fun getAll(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<List<PersonalDetails>> {
        return ResponseEntity.ok(service.getAllPersonalDetails(pageable).content)
    }

    @PostMapping
    fun create(
        @RequestBody personalDetails: PersonalDetails
    ): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.savePersonalDetails(personalDetails))
    }

    @GetMapping("get-by-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.getPersonalDetailsById(id))
    }

    @PutMapping("update")
    fun update(
        @RequestBody personalDetails: PersonalDetails
    ): ResponseEntity<PersonalDetails> {
        return ResponseEntity.ok(service.updatePersonalDetails(personalDetails))
    }


    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long):ResponseEntity<Void>  {
        service.deletePersonalDetailsById(id)
        return ResponseEntity.noContent().build()
    }

}