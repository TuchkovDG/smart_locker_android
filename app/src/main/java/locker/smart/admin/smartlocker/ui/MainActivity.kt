package locker.smart.admin.smartlocker.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.util.SPUtil


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_user_uuid.text = SPUtil.getUserUUID()
    }
}
