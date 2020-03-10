package com.reno.reactivemvp.rx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.Item
import com.reno.reactivemvp.R
import com.reno.reactivemvp.rx.SeekBarStatus.*
import io.reactivex.rxjava3.subjects.PublishSubject

class RxAdapter(
    override val subject: PublishSubject<Pair<SeekBarStatus, Item>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RxContract.Adapter.Model {
    private var items: List<Item> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RxViewHolder(view, subject)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RxViewHolder).onBind(items[position])
    }

    override fun addItems(items: List<Item>) {
        (this.items as ArrayList).addAll(items)
    }

}

class RxViewHolder(
    view: View,
    val subject: PublishSubject<Pair<SeekBarStatus, Item>>
) : RecyclerView.ViewHolder(view) {
    private val tvName: TextView = view.findViewById(R.id.tvName)
    private val seekBar: SeekBar = view.findViewById(R.id.sbProgress)

    fun onBind(currentItem: Item) {
        tvName.text = currentItem.name
        seekBar.progress = currentItem.value
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                currentItem.value = progress
                subject.onNext(Pair(ON_PROGRESS, currentItem))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                subject.onNext(Pair(ON_START, currentItem))
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                subject.onNext(Pair(ON_STOP, currentItem))
            }

        })
    }
}
