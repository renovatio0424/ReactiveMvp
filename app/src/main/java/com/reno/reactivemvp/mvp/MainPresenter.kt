package com.reno.reactivemvp.mvp

import com.reno.reactivemvp.Item

class MainPresenter(
    private val view: MainContract.View,
    private val model: MainContract.Model,
    private val adapterModel: MainContract.Adapter.Model
) : MainContract.Presenter {
    init {
        adapterModel.presenter = this
        adapterModel.addItems(model.items)
    }

    override fun onStartTouch(item: Item) {
        view.showToast("start touch : $item")
    }

    override fun onProgressTouch(item: Item) {
        view.showToast("progress touch : $item")
    }

    override fun onStopTouch(item: Item) {
        view.showToast("stop touch : $item")
    }

}
