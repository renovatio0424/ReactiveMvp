package com.reno.reactivemvp

import com.reno.reactivemvp.mvp.MainContract
import com.reno.reactivemvp.rx.RxContract

class MainModel : MainContract.Model, RxContract.Model {
    override val items: List<Item>
        get() = arrayListOf(
            Item("one", 10),
            Item("two", 20),
            Item("three", 30),
            Item("four", 40),
            Item("five", 50),
            Item("six", 60),
            Item("seven", 70),
            Item("eight", 80),
            Item("nine", 90),
            Item("ten", 100),
            Item("one", 10),
            Item("two", 20),
            Item("three", 30),
            Item("four", 40),
            Item("five", 50),
            Item("six", 60),
            Item("seven", 70),
            Item("eight", 80),
            Item("nine", 90),
            Item("ten", 100),
            Item("one", 10),
            Item("two", 20),
            Item("three", 30),
            Item("four", 40),
            Item("five", 50),
            Item("six", 60),
            Item("seven", 70),
            Item("eight", 80),
            Item("nine", 90),
            Item("ten", 100)
        )

}
