package com.inwhob.servermodels

import com.inwhob.commonmodels.MemberKeys
import org.jetbrains.exposed.sql.*

object Members : Table() {
    val id = integer(MemberKeys.ID.value).autoIncrement()
    val firstName = varchar(MemberKeys.FIRSTNAME.value, 128)
    val lastName = varchar(MemberKeys.LASTNAME.value, 128)
    val email = varchar(MemberKeys.EMAIL.value, 128)
    val phoneNumber = varchar(MemberKeys.PHONENUMBER.value, 10)
    val businessName = varchar(MemberKeys.BUSINESSNAME.value, 128)
    val businessDescription = varchar(MemberKeys.BUSINESSDESCRIPTION.value, 1024)

    override val primaryKey = PrimaryKey(id)
}