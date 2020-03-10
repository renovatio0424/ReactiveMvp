package com.reno.reactivemvp

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.rx.RxAdapter
import com.reno.reactivemvp.rx.RxContract
import com.reno.reactivemvp.rx.RxPresenter
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(rxModule)
        }
    }
}

val rxModule = module {
    factory<RxContract.Adapter.Model> { RxAdapter(PublishSubject.create()) } bind RecyclerView.Adapter::class
    factory<RxContract.Model> { MainModel() }
    factory<RxContract.Presenter> { (view: RxContract.View) ->
        RxPresenter(view, get(), get())
    }
}