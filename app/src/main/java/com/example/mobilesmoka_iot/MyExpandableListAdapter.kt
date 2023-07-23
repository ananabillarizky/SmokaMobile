package com.example.mobilesmoka_iot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class MyExpandableListAdapter(
    private val context: Context,
    private val groupList: List<String>,
    private val childMap: Map<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return groupList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childMap[groupList[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childMap[groupList[groupPosition]]?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.expandable_group_item, null)
        }

        val title = view?.findViewById<TextView>(R.id.groupTitle)
        val arrowIcon = view?.findViewById<ImageView>(R.id.imageViewArrow)
        title?.text = getGroup(groupPosition).toString()

        if(isExpanded == true){
            arrowIcon?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24_white)
        } else{
            arrowIcon?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24_white)
        }

        return view!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view_anggota = convertView
        if (view_anggota == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view_anggota = inflater.inflate(R.layout.item_anggota, null)
        }

        val childContent = view_anggota?.findViewById<TextView>(R.id.namaAnggota)
        val childImage = view_anggota?.findViewById<ImageView>(R.id.imageAnggota)

        val memberName = getChild(groupPosition, childPosition).toString()
        childContent?.text = memberName



        // You need to replace "getMemberImage()" with your method to get the member's photo
        val memberImage = getMemberImage(memberName)
        if (memberImage != null) {
            childImage?.setImageResource(memberImage)
        }

        return view_anggota!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        // Semua anak-anak dalam grup dapat dipilih
        return true
    }


    private fun getMemberImage(memberName: String): Int {
        // Assuming your member photos are named member1_photo.png and member2_photo.png
        return when (memberName) {
            "Ananabilla Rizky Muhardanie" -> R.drawable.hansel
            "Hamdan Yuwafi" -> R.drawable.profil
            "Slamet Handoko, S.Kom., M.Kom." -> R.drawable.profil1
            "Kuwat Santoso, S.Kom., M.Kom." -> R.drawable.profil2
            else -> R.drawable.hansel // Default photo resource for unknown members
        }
    }

}



