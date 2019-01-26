package locker.smart.admin.smartlocker.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_main.view.*
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.model.LockerModel

class LockerListAdapter(private val context: Context,
                        private var lockers: ArrayList<LockerModel>,
                        private var listener: MainListActivityListener) : RecyclerView.Adapter<LockerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_main, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lockers[position])
        if (position == lockers.size - 1) {
            holder.itemView.view_bottom_line.visibility = View.INVISIBLE
        } else {
            holder.itemView.view_bottom_line.visibility = View.VISIBLE
        }
        holder.itemView.setOnClickListener { listener.onClickToLockerItem(lockers[position]) }
    }

    override fun getItemCount() = lockers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bind(locker: LockerModel) {
            view.tv_name.text = locker.uid
        }
    }

    interface MainListActivityListener {

        fun onClickToLockerItem(model: LockerModel)
    }

    fun updateLockers(lockers: ArrayList<LockerModel>) {
        this.lockers = lockers
        notifyDataSetChanged()
    }
}