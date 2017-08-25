package com.minek.kotlin.everywhere.keduct

import com.minek.kotlin.everywhere.keduct.uuid.Uuid
import com.minek.kotlin.everywhere.kelibs.result.err
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
}

