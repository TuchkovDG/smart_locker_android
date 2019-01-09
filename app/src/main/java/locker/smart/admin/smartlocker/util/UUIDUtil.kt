package locker.smart.admin.smartlocker.util

import java.util.*

object UUIDUtil {

    fun getRandomUUID(): String {
        return UUID.randomUUID().toString()
    }
}