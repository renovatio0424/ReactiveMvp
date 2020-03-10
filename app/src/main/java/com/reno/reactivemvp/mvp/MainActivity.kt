package com.reno.reactivemvp.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reno.reactivemvp.*
import com.reno.reactivemvp.rx.RxActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: MainContract.Adapter.Model =
            MainAdapter()
        val model: MainContract.Model =
            MainModel()

        MainPresenter(
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goToRx -> startActivity(Intent(this, RxActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
