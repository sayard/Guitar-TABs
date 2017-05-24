package pl.c0.sayard.guitartabs

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Created by Karol on 23.05.2017.
 */
class LinkSorter {

    fun sort(elements: Elements): MutableList<TabInfo>{
        val sortedList: MutableList<TabInfo> = mutableListOf()
        for (element: Element in elements){
            val tabLink: String = element.attr("href")
            if(isTabValid(tabLink)){
                sortedList.add(TabInfo(tabLink, element.text(), determineTabType(tabLink)))
            }
        }
        return sortedList
    }

    private fun isTabValid(link: String): Boolean{
        return !(link.contains("power_tab") || link.contains("guitar_pro"))
    }

    private fun determineTabType(link: String): String {
        if (link.contains("drum_tab")) {//Drum tab
            return "Drums"
        } else if (link.contains("btab")) {//Bass tab
            return "Bass"
        } else {//regular tab
            return "Guitar"
        }
    }
}