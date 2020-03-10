package com.reno.reactivemvp.rx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RxActivity : AppCompatActivity(), RxContract.View {
    private val presenter: RxContract.Presenter by inject { parametersOf(this) }
    private val adapter: RxContract.Adapter.Model by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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