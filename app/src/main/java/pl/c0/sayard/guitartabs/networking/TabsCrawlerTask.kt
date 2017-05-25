package pl.c0.sayard.guitartabs.networking

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by Karol on 23.05.2017.
 */
class TabsCrawlerTask constructor(val activity: android.app.Activity, val context: android.content.Context): android.os.AsyncTask<String, Void, Elements>() {

    val progressBar = activity.findViewById(pl.c0.sayard.guitartabs.R.id.progress_bar) as android.widget.ProgressBar
    val errorMessage = activity.findViewById(pl.c0.sayard.guitartabs.R.id.error_message) as android.widget.TextView

    override fun onPreExecute() {
        progressBar.visibility = android.view.View.VISIBLE
        errorMessage.visibility = android.view.View.GONE
    }

    override fun doInBackground(vararg params: String?): org.jsoup.select.Elements? {
        try{
            val primaryDocument: org.jsoup.nodes.Document = org.jsoup.Jsoup.connect(params[0]).get()
            val html: String = primaryDocument.getElementsByClass("search-version--link").html()
            val elements: org.jsoup.select.Elements = org.jsoup.Jsoup.parse(html).select("a")
            return elements
        }catch (e: org.jsoup.HttpStatusException){
            return null
        }
    }

    override fun onPostExecute(results: org.jsoup.select.Elements?) {
        progressBar.visibility = android.view.View.GONE
        if(results != null){
            val recyclerView: android.support.v7.widget.RecyclerView = activity.findViewById(pl.c0.sayard.guitartabs.R.id.tabs_list) as android.support.v7.widget.RecyclerView
            val layoutManager: android.support.v7.widget.RecyclerView.LayoutManager = android.support.v7.widget.LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            val adapter: pl.c0.sayard.guitartabs.TabsAdapter = pl.c0.sayard.guitartabs.TabsAdapter(context, results)
            recyclerView.adapter = adapter
        }else{
            errorMessage.visibility = android.view.View.VISIBLE
        }
    }
}