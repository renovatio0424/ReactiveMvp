package com.reno.reactivemvp.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.Item
import com.reno.reactivemvp.R

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MainContract.Adapter.Model {
    override lateinit var presenter: MainContract.Presenter
    private var items: List<Item> = arrayListOf()

    override fun addItems(items: List<Item>) {
        (this.items as ArrayList).addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(view, presenter)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainViewHolder).onBind(items[position])
    }

}

class MainViewHolder(view: View, val presenter: MainContract.Presenter) : RecyclerView.ViewHolder(view) {
    private val tvName:TextView = view.findViewById(R.id.tvName)
    private val seekBar:SeekBar = view.findViewById(R.id.sbProgress)

    fun onBind(currentItem: Item) {
        tvName.text = currentItem.name
        seekBar.progress = currentItem.value
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                currentItem.value = progress
                presenter.onProgressTouch(currentItem)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                presenter.onStartTouch(currentItem)
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                presenter.onStopTouch(currentItem)
            }
        })
    }
}
