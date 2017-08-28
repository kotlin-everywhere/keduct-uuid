package com.minek.kotlin.everywhere.keduct

import com.minek.kotlin.everywhere.keduct.uuid.Uuid
import com.minek.kotlin.everywhere.keduct.uuid.UuidJs
import com.minek.kotlin.everywhere.kelibs.result.err
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
}

