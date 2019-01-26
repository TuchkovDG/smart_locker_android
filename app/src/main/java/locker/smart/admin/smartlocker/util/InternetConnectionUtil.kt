package locker.smart.admin.smartlocker.util

import android.content.Context
import android.net.ConnectivityManager

object InternetConnectionUtil {

    fun isConnectedOrConnecting(context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}