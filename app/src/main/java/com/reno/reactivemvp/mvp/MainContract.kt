package com.reno.reactivemvp.mvp

import com.reno.reactivemvp.Item

interface MainContract {
    interface View {
        fun showToast(message: String)
    }

    interface Presenter {
        fun onStartTouch(item: Item)
        fun onProgressTouch(item: Item)
        fun onStopTouch(item: Item)
    }

    interface Model {
        val items: List<Item>
    }

    interface Adapter {
        interface Model {
            var presenter: Presenter
            fun addItems(items: List<Item>)
        }
    }
}