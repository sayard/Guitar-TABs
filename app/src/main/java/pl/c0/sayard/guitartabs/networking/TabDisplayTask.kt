package pl.c0.sayard.guitartabs.networking

import android.app.Activity
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import pl.c0.sayard.guitartabs.R

/**
 * Created by Karol on 25.05.2017.
 */
class TabDisplayTask constructor(val activity: Activity) : AsyncTask<String, Void, Element>() {

    val progressBar = activity.findViewById(R.id.tab_display_progress_bar) as ProgressBar
    val errorMessage = activity.findViewById(R.id.tab_display_error_message) as TextView

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
    }

    override fun doInBackground(vararg params: String?): Element? {
        try{
            val primaryDocument: Document = Jsoup.connect(params[0]).get()
            val element: Element = primaryDocument.getElementsByClass("js-tab-content").first()
            return element
        }catch (e: HttpStatusException){
            return null
        }
    }

    override fun onPostExecute(result: Element?) {
        progressBar.visibility = View.GONE
        if(result != null){
            val tabContent = activity.findViewById(R.id.tab_content) as TextView
            tabContent.text = result.text()
        }else{
            errorMessage.visibility = View.VISIBLE
        }
    }
}