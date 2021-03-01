package rajeshkadiri.weathertestapi.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import rajeshkadiri.weathertestapi.R
import rajeshkadiri.weathertestapi.model.Cities
import java.util.*

class MyCitiesAdapter(private val context: Context, private val mMenuList: ArrayList<Cities>, var myClickListener: MyClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_citieslist, null) as ViewGroup
        return ViewHolder(itemLayoutView)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderView = holder as ViewHolder
        holderView.tvIconLabel.text = mMenuList[position].place
        holderView.rlMain.tag = position
    }

    override fun getItemCount(): Int {
        return mMenuList.size
    }

    inner class ViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView) {
        var tvIconLabel: TextView
        protected var ivIcon: ImageView
        var rlMain: LinearLayout
        protected var btn_info: AppCompatButton? = null

        init {
            tvIconLabel = convertView.findViewById<View>(R.id.cititext) as TextView
            ivIcon = convertView.findViewById<View>(R.id.delete) as ImageView
            rlMain = convertView.findViewById<View>(R.id.ll) as LinearLayout
            rlMain.setOnClickListener { v -> myClickListener.onInfoButtonClick(v, adapterPosition) }
            ivIcon.setOnClickListener { view -> myClickListener.onIconClick(view, adapterPosition) }
        }
    }

    override fun onClick(v: View) {
//        int position = (int) v.getTag();
        //((HomeScreenActivity) context).iScreenCodes.sendScreenId(mMenuList.get(position).getmScreenId());
    }

    interface MyClickListener {
        fun onInfoButtonClick(view: View?, p: Int)
        fun onIconClick(view: View?, p: Int)
    }
}