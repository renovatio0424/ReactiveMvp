package com.reno.reactivemvp.rx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.Item
import com.reno.reactivemvp.MainModel
import com.reno.reactivemvp.R
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class RxActivity : AppCompatActivity(), RxContract.View {
    private lateinit var presenter: RxPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subject = PublishSubject.create<Pair<SeekBarStatus, Item>>()
        val adapter: RxContract.Adapter.Model = RxAdapter(subject)
        val model: RxContract.Model = MainModel()
        presenter = RxPresenter(
            view = this,
            model = model,
            adapterModel = adapter
        )

        rvMain.adapter = adapter as RecyclerView.Adapter<*>
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}