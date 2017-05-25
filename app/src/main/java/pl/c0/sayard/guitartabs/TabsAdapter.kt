package pl.c0.sayard.guitartabs

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import org.jsoup.select.Elements

/**
 * Created by Karol on 24.05.2017.
 */

class TabsAdapter(private val context: Context, elements: Elements) : RecyclerView.Adapter<TabsAdapter.ViewHolder>() {
    private val sortedTabs: List<TabInfo>

    init {
        val linkSorter = LinkSorter()
        sortedTabs = linkSorter.sort(elements)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tabs_adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val info = sortedTabs[position]

        viewHolder.tabName.text = info.name
        viewHolder.tabType.text = info.type

        val onClickListener = View.OnClickListener {
            val intent: Intent = Intent(context, TabDisplayActivity::class.java)
            intent.putExtra(context.getString(R.string.extra_tab_link), info.link)
            context.startActivity(intent)
        }

        viewHolder.tabName.setOnClickListener(onClickListener)
        viewHolder.tabType.setOnClickListener(onClickListener)
    }

    override fun getItemCount(): Int {
        return sortedTabs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tabName = itemView.findViewById(R.id.tab_name_text_view) as TextView
        internal var tabType = itemView.findViewById(R.id.tab_type_text_view) as TextView

    }
}
