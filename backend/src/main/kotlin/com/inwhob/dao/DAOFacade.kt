package com.inwhob.dao

import com.inwhob.commonmodels.Member

interface DAOFacade {
    suspend fun allMembers(): List<Member>
    suspend fun member(id: Int): Member?
    suspend fun addNewMember(firstName: String,
                             lastName: String,
                             email: String,
                             phoneNumber: String,
                             businessName: String,
                             businessDescription: String): Member?
    suspend fun editMember(id: Int, member: Member): Boolean
    suspend fun deleteMember(id: Int): Boolean
}