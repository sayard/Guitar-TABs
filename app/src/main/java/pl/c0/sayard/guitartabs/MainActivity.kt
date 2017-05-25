package pl.c0.sayard.guitartabs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import pl.c0.sayard.guitartabs.networking.TabsCrawlerTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar: EditText = findViewById(R.id.search_bar) as EditText
        val searchButton: Button = findViewById(R.id.search_button) as Button

        searchButton.setOnClickListener {
            val searchQuery = searchBar.text.replace("\\s".toRegex(), "+")
            TabsCrawlerTask(this, this).execute("https://www.ultimate-guitar.com/search.php?search_type=title&order=&value=$searchQuery")
        }
    }
}
