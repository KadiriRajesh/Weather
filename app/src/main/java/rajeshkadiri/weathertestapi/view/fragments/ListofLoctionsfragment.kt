package rajeshkadiri.weathertestapi.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rajeshkadiri.weathertestapi.R
import rajeshkadiri.weathertestapi.base.BaseFragment
import rajeshkadiri.weathertestapi.model.Cities
import rajeshkadiri.weathertestapi.utils.DBHelper
import rajeshkadiri.weathertestapi.view.activities.CityScreenActivity
import rajeshkadiri.weathertestapi.view.adapters.MyCitiesAdapter
import rajeshkadiri.weathertestapi.view.adapters.MyCitiesAdapter.MyClickListener
import java.util.*

@SuppressLint("ValidFragment")
class ListofLoctionsfragment : BaseFragment() {
    var mRootView: ViewGroup? = null
    var recyclerView: RecyclerView? = null
    var listAdapter: MyCitiesAdapter? = null
    var dbHelper: DBHelper? = null
    var citiesArrayList: ArrayList<Cities>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_locations, null) as ViewGroup
            initViews()
            registerViewListeners()
            initObjects()
        } else {
            if (mRootView!!.parent != null) {
                (mRootView!!.parent as ViewGroup).removeView(mRootView)
            }
        }
        return mRootView
    }

    override fun initViews() {
        dbHelper = DBHelper(activity)
        recyclerView = mRootView!!.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.setLayoutManager(layoutManager)
        citiesArrayList = ArrayList()
        val cursor = dbHelper!!.allRecords
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val cities = Cities()
                cities.lat = cursor.getString(cursor.getColumnIndex("lat"))
                cities.lng = cursor.getString(cursor.getColumnIndex("lng"))
                cities.place = cursor.getString(cursor.getColumnIndex("place"))
                cities.timestamp = cursor.getString(cursor.getColumnIndex("timestamp"))
                citiesArrayList!!.add(cities)
                //  cursor.close();
            } while (cursor.moveToNext())
        }
        listAdapter = MyCitiesAdapter(requireActivity(), getcities()!!, object : MyClickListener {
            override fun onInfoButtonClick(view: View?, position: Int) {
                val i = Intent(activity, CityScreenActivity::class.java)
                i.putExtra("lat", "" + getcities()!![position].lat)
                i.putExtra("lng", "" + getcities()!![position].lng)
                startActivity(i)
            }

            override fun onIconClick(view: View?, position: Int) {
                dbHelper!!.deleteContact(getcities()!![position].timestamp!!)
                getcities()!!.removeAt(position)
                listAdapter!!.notifyDataSetChanged()
            }
        })
        recyclerView?.setAdapter(listAdapter)
    }

    private fun getcities(): ArrayList<Cities>? {
        return citiesArrayList
    }

    override fun registerViewListeners() {}
    override fun initObjects() {}
    override fun onResume() {
        super.onResume()
        //        notifyAll();
        listAdapter!!.notifyDataSetChanged()
    }
}