package com.minek.kotlin.everywhere.keduct.uuid

import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.Result

data class Uuid(private val uuid: String) {
    companion object {
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


