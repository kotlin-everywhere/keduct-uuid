package com.minek.kotlin.everywhere.keduct.uuid

import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.Result
import java.util.*

data class Uuid(private val uuid: UUID) {
    companion object {
        fun fromString(string: String): Result<String, Uuid> {
            return try {
                Ok(Uuid(UUID.fromString(string)))
            } catch (e: IllegalArgumentException) {
                Err(e.message ?: e.toString())
            }
        }

        fun randomUuid(): Uuid {
            return Uuid(UUID.randomUUID())
        }
    }

    override fun toString(): String {
        return uuid.toString()
    }
}


