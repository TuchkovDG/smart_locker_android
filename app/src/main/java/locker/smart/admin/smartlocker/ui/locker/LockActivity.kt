package locker.smart.admin.smartlocker.ui.locker

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_locker.*
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.ui.base.BaseActivity
import org.jetbrains.anko.*

class LockActivity : BaseActivity(), LockContract.View {

    private lateinit var presenter: LockContract.Presenter

    private var locker: LockerModel? = null

    override fun layoutId(): Int {
        return R.layout.activity_locker
    }

    override fun initViewsAndData() {
        presenter = LockPresenterImpl(this)
        locker = Gson().fromJson(intent.getStringExtra("locker"), LockerModel::class.java)
        setLockerStatusOnView()
        initOnClickListener()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun lockerUnReserve() {
        onBackPressed()
    }

    override fun showError(text: String) {
        toast(text)
    }

    private fun setLockerStatusOnView() {
        if (locker?.status == 0) {
            siv_status_locker.image = getDrawable(R.drawable.ic_lock_open_black_24dp)
        } else if (locker?.status == 1) {
            siv_status_locker.image = getDrawable(R.drawable.ic_lock_outline_black_24dp)
        }
    }

    private fun initOnClickListener() {
        siv_status_locker.setOnClickListener {
            if (locker?.status == 0) {
                locker?.status = 1
            } else if (locker?.status == 1) {
                locker?.status = 0
            }
            setLockerStatusOnView()
        }
        ll_delete_this_locker.setOnClickListener { _ ->
            alert("UnReserve this locker?") {
                yesButton {
                    presenter.unReserveLocker(locker)
                }
                noButton {}
            }.show()
        }
    }
}