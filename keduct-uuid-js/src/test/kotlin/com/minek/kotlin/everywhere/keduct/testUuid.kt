package com.minek.kotlin.everywhere.keduct

import com.minek.kotlin.everywehre.keuson.convert.decoder
import com.minek.kotlin.everywehre.keuson.decode.decodeString
import com.minek.kotlin.everywehre.keuson.encode.encode
import com.minek.kotlin.everywhere.keduct.uuid.Uuid
import com.minek.kotlin.everywhere.keduct.uuid.UuidJs
import com.minek.kotlin.everywhere.kelibs.result.andThen
import com.minek.kotlin.everywhere.kelibs.result.err
import com.minek.kotlin.everywhere.kelibs.result.map
import com.minek.kotlin.everywhere.kelibs.result.ok
import org.junit.Test
import kotlin.test.assertEquals

class TestUuid {
    @Test
    fun testFromString() {
        val uuidjs = UuidJs.generate().toString()
        val uuid = Uuid.fromString(uuidjs)

        assertEquals(ok(Uuid(uuidjs)), uuid)
        assertEquals(err("Invalid UUID string: invalid"), Uuid.fromString("invalid"))
    }

    @Test
    fun testToString() {
        val uuidjs = UuidJs.generate().toString()
        assertEquals(uuidjs, Uuid(uuidjs).toString())
    }

    @Test
    fun testRandomUuid() {
        val uuid = Uuid.randomUuid()
        assertEquals(uuid, Uuid(UuidJs.parse(uuid.toString()).toString()))
    }

    @Test
    fun testDecoder() {
        assertEquals(err("Expecting a String but instead got: null"), decodeString(Uuid.decoder, "null"))
        assertEquals(err("Expecting a String but instead got: true"), decodeString(Uuid.decoder, "true"))
        assertEquals(err("Expecting a String but instead got: 42"), decodeString(Uuid.decoder, "42"))
        assertEquals(err("Expecting a String but instead got: 3.14"), decodeString(Uuid.decoder, "3.14"))
        assertEquals(err("Invalid UUID string: hello"), decodeString(Uuid.decoder, "\"hello\""))
        assertEquals(err("Expecting a String but instead got: {\"hello\":42}"), decodeString(Uuid.decoder, "{ \"hello\": 42 }"))
        assertEquals(Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a"), decodeString(Uuid.decoder, "\"87eb50f6-8bb7-11e7-814c-5b8cec49619a\""))
    }

    @Test
    fun testEncoder() {
        assertEquals(
                ok("\"87eb50f6-8bb7-11e7-814c-5b8cec49619a\""),
                Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a").map { encode(Uuid.encoder(it)) }
        )
    }

    @Test
    fun testConverter() {
        assertEquals(
                Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a"),
                Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a")
                        .map { encode(Uuid.converter.first(it)) }
                        .andThen { decodeString(Uuid.converter.decoder, it) }
        )
    }
}

