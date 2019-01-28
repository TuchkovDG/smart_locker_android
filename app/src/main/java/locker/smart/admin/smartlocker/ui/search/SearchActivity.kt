package locker.smart.admin.smartlocker.ui.search

import kotlinx.android.synthetic.main.activity_search.*
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.ui.base.BaseActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class SearchActivity : BaseActivity(), SearchContract.View {

    private lateinit var presenter: SearchContract.Presenter

    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    override fun initViewsAndData() {
        presenter = SearchPresenterImpl(this)
        initOnClickListener()
    }

    override fun handleLock(lock: LockerModel) {
        if (lock.status == 1) {
            alert(getString(R.string.this_lock_is_reserved)) {
                yesButton {}
                noButton { onBackPressed() }
            }.show()
        } else if (lock.status == 0) {
            alert(getString(R.string.this_lock_is_free)) {
                yesButton { presenter.reserveLock(lock) }
                noButton {}
            }.show()
        }
    }

    override fun lockReserved() {
        onBackPressed()
    }

    override fun showError(text: String) {
        tv_status.text = text
    }

    private fun initOnClickListener() {
        ll_get_random_reserve.setOnClickListener {
            presenter.getRandomReservedLock()
        }
        ll_get_random_un_reserve.setOnClickListener {
            presenter.getRandomUnReservedLock()
        }
    }
}