package alfianyusufabdullah.pagingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainAdapter = MainAdapter()

        val listName = findViewById<RecyclerView>(R.id.list_name)
        listName.hasFixedSize()
        listName.layoutManager = LinearLayoutManager(this)
        listName.adapter = mainAdapter

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getData().observe(this, Observer {
            mainAdapter.submitList(it)
        })

        val tvState = findViewById<TextView>(R.id.tvState)

        mainViewModel.getState().observe(this, Observer {
            tvState.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "RESET")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == 0) {
            mainAdapter.submitList(null)
            mainAdapter.notifyDataSetChanged()
        }

        return super.onOptionsItemSelected(item)
    }
}
