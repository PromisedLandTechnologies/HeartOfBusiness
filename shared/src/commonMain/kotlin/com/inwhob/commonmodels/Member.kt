package com.inwhob.commonmodels

import kotlinx.serialization.Serializable

@Serializable
data class Member(val id: Int,
                  val firstName: String,
                  val lastName: String,
                  val email: String,
                  val phoneNumber: String,
                  val businessName: String,
                  val businessDescription: String) {
    val name = "$firstName $lastName"
    val displayPhoneNumber: String = StringBuilder(phoneNumber).insert(3, "-").insert(7, "-").toString()
}

enum class MemberKeys(val value: String) {
    ID("id"), FIRSTNAME("firstName"), LASTNAME("lastName"), EMAIL("email"), PHONENUMBER("phoneNumber"),
    BUSINESSNAME("businessName"), BUSINESSDESCRIPTION("businessDescription");
}