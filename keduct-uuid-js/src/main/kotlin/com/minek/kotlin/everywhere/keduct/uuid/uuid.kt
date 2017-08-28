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

data class Uuid(private val uuid: String) {
    companion object {
        val decoder: Decoder<Uuid> = { Decoders.string(it).andThen { fromString(it) } }
        val encoder: Encoder<Uuid> = { Encoders.string(it.toString()) }
        val converter: Converter<Uuid> = encoder to decoder

        fun fromString(string: String): Result<String, Uuid> {
            val uuidjs = UuidJs.parse(string)
            return if (uuidjs != null) {
                Ok(Uuid(uuidjs.toString()))
            } else {
                Err("Invalid UUID string: $string")
            }
        }

        fun randomUuid(): Uuid {
            return Uuid(UuidJs.generate().toString())
        }
    }

    override fun toString(): String {
        return uuid
    }
}

@JsModule("uuidjs")
external class UuidJs {
    companion object {
        fun generate(): UuidJs
        fun parse(string: String): UuidJs?
    }
}


