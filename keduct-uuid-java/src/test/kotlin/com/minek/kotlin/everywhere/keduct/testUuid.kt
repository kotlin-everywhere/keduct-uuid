package com.minek.kotlin.everywhere.keduct

import com.minek.kotlin.everywehre.keuson.decode.decodeString
import com.minek.kotlin.everywehre.keuson.encode.encode
import com.minek.kotlin.everywhere.keduct.uuid.Uuid
import com.minek.kotlin.everywhere.kelibs.result.err
import com.minek.kotlin.everywhere.kelibs.result.map
import com.minek.kotlin.everywhere.kelibs.result.ok
import org.junit.Assert
import org.junit.Test
import java.util.*

class TestUuid {
    @Test
    fun testFromString() {
        val javaUuid = UUID.randomUUID()
        val uuid = Uuid.fromString(javaUuid.toString())
        Assert.assertEquals(ok(Uuid(javaUuid)), uuid)

        Assert.assertEquals(err("Invalid UUID string: invalid"), Uuid.fromString("invalid"))
    }

    @Test
    fun testToString() {
        val javaUuid = UUID.randomUUID()
        Assert.assertEquals(javaUuid.toString(), Uuid(javaUuid).toString())
    }

    @Test
    fun testRandomUuid() {
        val uuid = Uuid.randomUuid()
        Assert.assertEquals(uuid, Uuid(UUID.fromString(uuid.toString())))
    }

    @Test
    fun testDecoder() {
        Assert.assertEquals(err("Expecting a String but instead got: null"), decodeString(Uuid.decoder, "null"))
        Assert.assertEquals(err("Expecting a String but instead got: true"), decodeString(Uuid.decoder, "true"))
        Assert.assertEquals(err("Expecting a String but instead got: 42"), decodeString(Uuid.decoder, "42"))
        Assert.assertEquals(err("Expecting a String but instead got: 3.14"), decodeString(Uuid.decoder, "3.14"))
        Assert.assertEquals(err("Invalid UUID string: hello"), decodeString(Uuid.decoder, "\"hello\""))
        Assert.assertEquals(err("Expecting a String but instead got: {\"hello\":42}"), decodeString(Uuid.decoder, "{ \"hello\": 42 }"))
        Assert.assertEquals(Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a"), decodeString(Uuid.decoder, "\"87eb50f6-8bb7-11e7-814c-5b8cec49619a\""))
    }

    @Test
    fun testEncoder() {
        Assert.assertEquals(
                ok("\"87eb50f6-8bb7-11e7-814c-5b8cec49619a\""),
                Uuid.fromString("87eb50f6-8bb7-11e7-814c-5b8cec49619a").map { encode(Uuid.encoder(it)) }
        )
    }
}

