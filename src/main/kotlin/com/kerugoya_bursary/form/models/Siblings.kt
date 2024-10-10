package com.kerugoya_bursary.form.models

import jakarta.persistence.*

/**
 *
 * @author Titus Murithi Bundi
 */
@Entity
@Table(name = "siblings")
data class Siblings(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siblings_id_seq")
    @SequenceGenerator(
        name = "siblings_id_seq",
        sequenceName = "siblings_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "primary_school")
    var primarySchool: Int?,

    @Column(name = "secondary_school")
    var secondarySchool: Int?,

    @Column(name = "tertiary_college")
    var tertiaryCollege: Int?,

    @Column(name = "university")
    var university: Int?
) : BaseAudit()
