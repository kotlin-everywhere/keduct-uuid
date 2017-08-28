package com.minek.kotlin.everywhere.keduct.uuid

import com.minek.kotlin.everywehre.keuson.decode.Decoder
import com.minek.kotlin.everywehre.keuson.decode.Decoders
import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.Result
import com.minek.kotlin.everywhere.kelibs.result.andThen
import java.util.*

data class Uuid(private val uuid: UUID) {
    companion object {
        val decoder: Decoder<Uuid> = { Decoders.string(it).andThen { fromString(it) } }

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


