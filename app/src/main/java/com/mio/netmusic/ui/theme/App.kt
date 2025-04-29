package com.mio.netmusic.ui.theme

import android.content.Context
import android.widget.Toast
import com.mio.netmusic.utlils.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

object App {
    var qrKey: String = ""


    private lateinit var contextRef: WeakReference<Context>
    val context get() = contextRef.get() ?: throw IllegalStateException("context is null")


    val sp by lazy { SharedPreferencesHelper(context) }


    val cookie = MutableStateFlow("")

    val isLogin = MutableStateFlow(false)


    fun initApp(context: Context) {
        contextRef = WeakReference(context)
    }

    fun toast(msg: String) {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}