package com.minek.kotlin.everywhere.keduct.uuid

import com.minek.kotlin.everywehre.keuson.convert.Converter
import com.minek.kotlin.everywehre.keuson.decode.Decoder
import com.minek.kotlin.everywehre.keuson.decode.Decoders
import com.minek.kotlin.everywehre.keuson.encode.Encoder
import com.minek.kotlin.everywehre.keuson.encode.Encoders
import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.Result
import com.minek.kotlin.everywhere.kelibs.result.andThen
import java.util.*

data class Uuid(private val uuid: UUID) {
    val javaUuid: UUID = uuid

    override fun toString(): String {
        return uuid.toString()
    }

    companion object {
        val decoder: Decoder<Uuid> = { Decoders.string(it).andThen { fromString(it) } }
        val encoder: Encoder<Uuid> = { Encoders.string(it.toString()) }
        val converter: Converter<Uuid> = encoder to decoder

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
}


