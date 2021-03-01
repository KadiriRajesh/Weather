package rajeshkadiri.weathertestapi.base

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import rajeshkadiri.weathertestapi.utils.DBHelper

/**
 * This class acts as a base class for all the fragments. This class is extending
 * Fragment class, which in turn allows this call to use all the features of
 * Fragment class.
 */
abstract class BaseFragment : Fragment(), FragmentListener {
    var mHandler: DBHelper? = null
    protected var mActivity: WHBaseEventListener? = null
    abstract fun initViews()
    abstract fun registerViewListeners()
    abstract fun initObjects()
    private var mFragTag: String? = null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mActivity = activity as WHBaseEventListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mActivity != null) {
            mActivity!!.setFragment(this)
        }
    }

    override fun backPressed(): Boolean {
        return false
    }

    /**
     * Method to push the fragment
     *
     * @param containerId
     * @param tag
     * @param fragment
     * @param clearStack
     */
    fun pushFragment(containerId: Int, tag: String?, fragment: Fragment?, clearStack: Boolean) {
        val ft = fragmentManager!!.beginTransaction()
        if (clearStack) {
            fragmentManager!!.popBackStack(tag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        mFragTag = tag
        ft.replace(containerId, fragment!!)
        ft.addToBackStack(tag)
        ft.commit()
    }

    /**
     * This is an overridden method of fragment class in this method we are
     * un-registering the the broadcast receiver once its task is completed.
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    fun showCustomToast(message: String) {
        val toast = Toast.makeText(activity, "" + message, Toast.LENGTH_LONG)
        val toastView = toast.view // This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
        val toastMessage = toastView!!.findViewById<View>(R.id.message) as TextView
        toastMessage.textSize = 15f
        // toastMessage.setTextColor(getResources().getColor(R.color.color_white));
        toastMessage.gravity = Gravity.CENTER
        toastMessage.compoundDrawablePadding = 10
        //toastView.setBackgroundColor(getResources().getColor(R.color.color_black));
        toast.show()
    }
}