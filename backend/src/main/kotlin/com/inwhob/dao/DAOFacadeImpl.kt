package com.inwhob.dao

import com.inwhob.dao.DatabaseFactory.dbQuery
import com.inwhob.commonmodels.*
import com.inwhob.servermodels.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToMember(row: ResultRow) = Member(
        id = row[Members.id],
        firstName = row[Members.firstName],
        lastName = row[Members.lastName],
        email = row[Members.email],
        phoneNumber = row[Members.phoneNumber],
        businessName = row[Members.businessName],
        businessDescription = row[Members.businessDescription],
    )

    override suspend fun allMembers(): List<Member> = dbQuery {
        Members.selectAll().map(::resultRowToMember)
    }

    override suspend fun member(id: Int): Member? = dbQuery {
        Members
            .select { Members.id eq id }
            .map(::resultRowToMember)
            .singleOrNull()
    }

    override suspend fun addNewMember(firstName: String,
                                      lastName: String,
                                      email: String,
                                      phoneNumber: String,
                                      businessName: String,
                                      businessDescription: String): Member? = dbQuery {
        val insertStatement = Members.insert {
            it[Members.firstName] = firstName
            it[Members.lastName] = lastName
            it[Members.email] = email
            it[Members.phoneNumber] = phoneNumber
            it[Members.businessName] = businessName
            it[Members.businessDescription] = businessDescription
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToMember)
    }

    override suspend fun editMember(id: Int, member: Member): Boolean = dbQuery {
        require(id == member.id) {
            "Member Requested to be updated does not match given member, ID cannot be updated"
        }
        Members.update({ Members.id eq id }) {
            it[firstName] = member.firstName
            it[lastName] = member.lastName
            it[email] = member.email
            it[phoneNumber] = member.phoneNumber
            it[businessName] = member.businessName
            it[businessDescription] = member.businessDescription
        } > 0
    }

    override suspend fun deleteMember(id: Int): Boolean = dbQuery {
        Members.deleteWhere { Members.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allMembers().isEmpty()) {
            addNewMember(
                "Matthew",
                "Hjelm",
                "promisedlandtechnologies@proton.me",
                "2085123416",
                "Promised Land Technologies",
                "Custom data solutions for small businesses. Do you use excel... and hate it?")
        }
    }
}