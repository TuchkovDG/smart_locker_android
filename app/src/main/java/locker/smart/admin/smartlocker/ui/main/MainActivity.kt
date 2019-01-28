package locker.smart.admin.smartlocker.ui.main

import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.model.UserModel
import locker.smart.admin.smartlocker.ui.base.BaseActivity
import locker.smart.admin.smartlocker.ui.locker.LockActivity
import locker.smart.admin.smartlocker.ui.main.adapter.LockerListAdapter
import locker.smart.admin.smartlocker.ui.search.SearchActivity
import locker.smart.admin.smartlocker.util.SPUtil
import org.jetbrains.anko.*

class MainActivity : BaseActivity(), MainContract.View, LockerListAdapter.MainListActivityListener {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: LockerListAdapter

    private var userModel: UserModel? = null

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViewsAndData() {
        presenter = MainPresenterImpl(this)
        initSwipeRefreshLayout()
        initListAdapter()
        initOnClickListener()
        userModel = SPUtil.getUserModel()
        if (userModel == null) {
            presenter.getOrCreateUser(this.contentResolver)
        }
    }

    override fun onResume() {
        super.onResume()
        if (userModel != null) {
            presenter.getAllUserLockersBD()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onClickToLockerItem(model: LockerModel) {
        startActivity(intentFor<LockActivity>("locker" to Gson().toJson(model)))
    }

    override fun updateUserModel(model: UserModel) {
        userModel = model
    }

    override fun updateAllLockers(lockers: ArrayList<LockerModel>) {
        adapter.updateLockers(lockers)
    }

    override fun setRefreshing(boolean: Boolean) {
        srl_user_lockers.isRefreshing = boolean
    }

    override fun showError(text: String) {
        toast(text)
    }

    private fun initSwipeRefreshLayout() {
        srl_user_lockers.setOnRefreshListener {
            presenter.getAllUserLockersRest()
        }
    }

    private fun initListAdapter() {
        adapter = LockerListAdapter(this, ArrayList(), this)
        rv_user_lockers.layoutManager = LinearLayoutManager(this)
        rv_user_lockers.adapter = adapter
    }

    private fun initOnClickListener() {
        ll_search_new_locker.setOnClickListener {
            startActivity<SearchActivity>()
        }
        ll_delete_all_locker.setOnClickListener { _ ->
            alert(getString(R.string.free_all_locks)) {
                yesButton { presenter.unReserveAllUserLockers() }
                noButton {}
            }.show()
        }
    }
}
