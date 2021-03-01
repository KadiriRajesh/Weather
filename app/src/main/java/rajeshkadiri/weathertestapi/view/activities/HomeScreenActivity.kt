package rajeshkadiri.weathertestapi.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import rajeshkadiri.weathertestapi.R
import rajeshkadiri.weathertestapi.base.BaseFragmentActivity
import rajeshkadiri.weathertestapi.view.fragments.ListofLoctionsfragment
import rajeshkadiri.weathertestapi.view.fragments.MapsFragment
import rajeshkadiri.weathertestapi.view.fragments.MapsFragment2

class HomeScreenActivity : BaseFragmentActivity(), View.OnClickListener {
    private var mFragmentTag: String? = null
    private var mFragment: Fragment? = null
    var map: AppCompatButton? = null
    var locations: AppCompatButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        initViews()
        initObjects()
        registerViewListeners()
    }

    override fun initViews() {
        map = findViewById(R.id.map)
        locations = findViewById(R.id.locations)
        mFragmentTag = "Listofloctaions"
        pushFragment(R.id.real_content, mFragmentTag, getFragment(mFragmentTag!!), true)
    }

    override fun registerViewListeners() {
        map!!.setOnClickListener(this)
        locations!!.setOnClickListener(this)
    }

    override fun initObjects() {}
    private fun getFragment(mFragmentTag: String): Fragment? {
        when (mFragmentTag) {
            "MapsFragment" -> mFragment = MapsFragment()
            "Listofloctaions" -> mFragment = ListofLoctionsfragment()
        }
        return mFragment
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.map -> {
                mFragmentTag = "MapsFragment"
                pushFragment(R.id.real_content, mFragmentTag, getFragment(mFragmentTag!!), true)
            }
            R.id.locations -> {
                mFragmentTag = "Listofloctaions"
                pushFragment(R.id.real_content, mFragmentTag, getFragment(mFragmentTag!!), true)
            }
        }
    }
}