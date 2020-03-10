package com.reno.reactivemvp.rx

import com.reno.reactivemvp.Item
import io.reactivex.rxjava3.subjects.PublishSubject

interface RxContract {
    interface View {
        fun showToast(message: String)
    }

    interface Presenter {
        fun dispose()
    }

    interface Model {
        val items: List<Item>
    }

    interface Adapter {
        interface Model {
            val subject: PublishSubject<Pair<SeekBarStatus, Item>>
            fun addItems(items: List<Item>)
        }
    }
}