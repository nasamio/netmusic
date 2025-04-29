import android.util.Log

/**
 * 能够根据堆栈信息 获取log具体所在的代码位置
 */
object LogUtils {
    private const val DEBUG = true

    // 过滤全部日志
    private const val TAG = "net_music"

    private fun d() {
        if (DEBUG) {
            Log.d("$TAG ", "${getAutoJumpLogInfos()[1]}:${getAutoJumpLogInfos()[2]}")
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Log.d("$tag ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun v(tag: String, msg: String) {
        if (DEBUG) {
            Log.v("$tag ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun v(msg: String) {
        if (DEBUG) {
            Log.v("$TAG ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }


    fun d(msg: String) {
        if (DEBUG) {
            Log.d("$TAG ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Log.i("$tag ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun i(msg: String) {
        if (DEBUG) {
            Log.i("$TAG ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) {
            Log.w("$tag ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun w(msg: String) {
        if (DEBUG) {
            Log.w("$TAG ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Log.e("$tag ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    fun e(msg: String) {
        if (DEBUG) {
            Log.e("$TAG ", "${getAutoJumpLogInfos()[1]}:$msg${getAutoJumpLogInfos()[2]}")
        }
    }

    private fun getAutoJumpLogInfos(): Array<String> {
        val infos = arrayOf("", "", "")
        val elements = Thread.currentThread().stackTrace
        if (elements.size < 5) {
            Log.e("MyLogger", "Stack is too shallow!!!")
            return infos
        } else {
            infos[0] = elements[4].className.substringAfterLast(".")
            infos[1] = "${elements[4].methodName}()"
            infos[2] = " at(${elements[4].className}.kt:${elements[4].lineNumber})"
            return infos
        }
    }
}
