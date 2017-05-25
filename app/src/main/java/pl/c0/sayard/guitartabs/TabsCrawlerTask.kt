package pl.c0.sayard.guitartabs

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
class TabsCrawlerTask constructor(val activity: Activity, val context: Context): AsyncTask<String, Void, Elements>() {

    val progressBar = activity.findViewById(R.id.progress_bar) as ProgressBar
    val errorMessage = activity.findViewById(R.id.error_message) as TextView

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
    }

    override fun doInBackground(vararg params: String?): Elements? {
        try{
            val primaryDocument: Document = Jsoup.connect(params[0]).get()
            val html: String = primaryDocument.getElementsByClass("search-version--link").html()
            val elements: Elements = Jsoup.parse(html).select("a")
            return elements
        }catch (e: HttpStatusException){
            return null
        }
    }

    override fun onPostExecute(results: Elements?) {
        progressBar.visibility = View.GONE
        if(results != null){
            val recyclerView: RecyclerView = activity.findViewById(R.id.tabs_list) as RecyclerView
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            val adapter: TabsAdapter = TabsAdapter(context, results)
            recyclerView.adapter = adapter
        }else{
            errorMessage.visibility = View.VISIBLE
        }
    }
}