package com.github.tatsuyafujisaki.listviewsample

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.github.tatsuyafujisaki.listviewsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.listView) {
            addHeaderView(createTextView(R.string.header1))
            addHeaderView(createTextView(R.string.header2))
            addFooterView(createTextView(R.string.footer1))
            addFooterView(createTextView(R.string.footer2))
            adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                listOf("a", "b", "c")
            )
            setOnItemClickListener { _, view, position, id ->
                Snackbar.make(
                    view,
                    "id=$id, position=$position, item=${getItemAtPosition(position)}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            Log.d(TAG, "ListView#count: $count")
            Log.d(TAG, "ListView#headerViewsCount: $headerViewsCount")
            Log.d(TAG, "ListView#headerViewsCount: $footerViewsCount")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toggle_header_dividers -> {
                with(binding.listView) {
                    setHeaderDividersEnabled(!areHeaderDividersEnabled())
                    Log.d(TAG, "ListView#areHeaderDividersEnabled(): ${areHeaderDividersEnabled()}")
                }
                true
            }
            R.id.toggle_footer_dividers -> {
                with(binding.listView) {
                    setFooterDividersEnabled(!areFooterDividersEnabled())
                    Log.d(TAG, "ListView#areFooterDividersEnabled(): ${areFooterDividersEnabled()}")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createTextView(@StringRes id: Int) =
        ((layoutInflater.inflate(android.R.layout.simple_list_item_1, null)) as TextView)
            .apply {
                text = context.getString(id)
            }

    companion object {
        const val TAG = "MainActivity"
    }
}
