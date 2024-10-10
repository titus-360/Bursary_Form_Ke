package com.kerugoya_bursary.form.parent_details.services.serviceImp

import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.exception.ResourceNotFoundException
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toDto
import com.kerugoya_bursary.form.mappers.FamilyDetailsMapper.toEntity
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.repositories.ParentDetailsRepository
import com.kerugoya_bursary.form.services.serviceImp.ParentDetailsServiceImp
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.test.Test

class ParentDetailsServiceImpTest {
    @Mock
    private lateinit var repository: ParentDetailsRepository

    private lateinit var service: ParentDetailsServiceImp

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = ParentDetailsServiceImp(repository)
    }

    @Test
    @DisplayName("Should return all parent details")
    fun getAllParentDetails() {
        val pageable = PageRequest.of(0, 10)
        val parentDetails = createTestParentDetails()
        val page: Page<ParentDetails> = PageImpl(listOf(parentDetails))

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.getAllParentDetails(pageable)

        assertEquals(1, result.content.size)
        assertEquals(parentDetails, result.content[0])
    }

    @Test
    @DisplayName("Should create a new parent detail")
    fun createParentDetails() {
        val parentDetailsDto = createTestParentDetailsDto()
        val parentDetails = parentDetailsDto.toEntity()

        `when`(repository.save(parentDetails)).thenReturn(parentDetails)

        val result = service.createParentDetails(parentDetails.toDto())

        assertEquals(parentDetailsDto, result)
    }

    @Test
    @DisplayName("Should return a parent detail by id")
    fun getParentDetailsById() {
        val parentDetails = createTestParentDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(parentDetails))

        val result = service.getParentDetailsById(id)

        assertEquals(parentDetails, result)
    }

    @Test
    @DisplayName("Should throw an exception when parent detail not found by id")
    fun getParentDetailsByIdNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.getParentDetailsById(id)
        }
    }

    @Test
    @DisplayName("Should update a parent detail")
    fun updateParentDetails() {
        val parentDetails = createTestParentDetails()
        val parentDetailsDto = ParentDetailsDto(
            id = parentDetails.id,
            idNumber = parentDetails.idNumber,
            phone = parentDetails.phone,
            occupation = parentDetails.occupation,
            relationship = parentDetails.relationship,
            age = parentDetails.age,
            county = parentDetails.county,
            subCounty = parentDetails.subCounty,
            ward = parentDetails.ward,
            firstName = parentDetails.firstName,
            surname = parentDetails.surname,
            otherNames = parentDetails.otherNames,
            status = parentDetails.status,
            type = parentDetails.type
        )
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(parentDetails))
        `when`(repository.save(parentDetails)).thenReturn(parentDetails)

        val result = service.updateParentDetails(id, parentDetailsDto)

        assertEquals(parentDetailsDto, result.toDto())
    }

    @Test
    @DisplayName("Should delete a parent detail by id")
    fun deleteParentDetails() {
        val parentDetails = createTestParentDetails()
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.of(parentDetails))

        Mockito.doNothing().`when`(repository).delete(parentDetails)

        assertDoesNotThrow { service.deleteParentDetails(id) }
    }

    @Test
    @DisplayName("Should throw an exception when deleting a non-existing parent detail")
    fun deleteParentDetailsNotFound() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows(ResourceNotFoundException::class.java) {
            service.deleteParentDetails(id)
        }
    }

}

private fun createTestParentDetails(): ParentDetails {
    return ParentDetails(
        id = 1,
        idNumber = "12345678",
        phone = "0712345678",
        occupation = "farmer",
        relationship = "mother",
        age = 50,
        county = "Kirinyaga",
        subCounty = "Kirinyaga Central",
        ward = "Kerugoya",
        firstName = "Jane",
        surname = "Doe",
        otherNames = "Doe",
        status = "married",
        type = "guardian",
    )
}

private fun createTestParentDetailsDto(): ParentDetailsDto {
    return ParentDetailsDto(
        id = 1,
        idNumber = "12345678",
        phone = "0712345678",
        occupation = "farmer",
        relationship = "mother",
        age = 50,
        county = "Kirinyaga",
        subCounty = "Kirinyaga Central",
        ward = "Kerugoya",
        firstName = "Jane",
        surname = "Doe",
        otherNames = "Doe",
        status = "married",
        type = "guardian",
    )
}
