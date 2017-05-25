package pl.c0.sayard.guitartabs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class TabDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_display)
        val intent = intent
        val link = intent.getStringExtra(getString(R.string.extra_tab_link))
        val tabDisplayTask = TabDisplayTask(this)
        tabDisplayTask.execute(link)
    }
}
