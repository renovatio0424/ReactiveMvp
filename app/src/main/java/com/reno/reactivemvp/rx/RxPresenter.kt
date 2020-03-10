package com.reno.reactivemvp.rx

import com.reno.reactivemvp.Item
import com.reno.reactivemvp.rx.SeekBarStatus.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxPresenter(
    private val view: RxContract.View,
    private val model: RxContract.Model,
    private val adapterModel: RxContract.Adapter.Model
) : RxContract.Presenter {
    private val disposable: Disposable

    init {

        adapterModel.addItems(model.items)
        disposable = adapterModel.subject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val status = it.first
                val item = it.second
                when (status) {
                    ON_START -> onStartTouch(item)
                    ON_PROGRESS -> onProgressTouch(item)
                    ON_STOP -> onStopTouch(item)
                }
            }

    }

    private fun onStartTouch(item: Item) {
        view.showToast("start touch : $item")
    }

    private fun onProgressTouch(item: Item) {
        view.showToast("progress touch : $item")
    }

    private fun onStopTouch(item: Item) {
        view.showToast("stop touch : $item")
    }

    override fun dispose() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}

enum class SeekBarStatus {
    ON_START,
    ON_PROGRESS,
    ON_STOP
}