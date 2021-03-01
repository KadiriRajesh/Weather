package rajeshkadiri.weathertestapi.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import rajeshkadiri.weathertestapi.R

/**
 * This class acts as a base class for all the fragment activities. This class is
 * extending Fragment Activity class, which in turn allows this call to use all the
 * features of Fragment Activity class.
 */
open class BaseFragmentActivity : FragmentActivity(), WHBaseEventListener {
    private var mFragment: FragmentListener? = null
    private val mProgressDialog: ProgressDialog? = null
    var bitmap: Bitmap? = null
    private var mFragTag: String? = null
    var context: Activity? = null
     open fun initViews() {}
    open fun registerViewListeners(){}
     open fun initObjects(){}
    private val mSweepAngle = 0f
    private val mActualPercentage = 0
    private val mTextViewTimer: TextView? = null
    private val mInitialSweepAngle = -90.0f
    private val mContentView: View? = null

    fun Fragment(fragment: FragmentListener){
        mFragment = fragment
    }


    /**
     * Method hides the soft keyboard
     *
     * @param ctx
     * @param v
     */
    fun hideSoftKeyboard(ctx: Context, v: View) {
        val inputManager = ctx.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    /**
     * Method to push the fragment
     * @param containerId
     * @param tag
     * @param fragment
     * @param clearStack
     */
    fun pushFragment(containerId: Int, tag: String?, fragment: Fragment?, clearStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction()
        if (clearStack) {
            supportFragmentManager.popBackStack(tag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        mFragTag = tag
        ft.replace(containerId, fragment!!)
        ft.addToBackStack(tag)
        ft.commit()
    }

    override fun onBackPressed() {
        //     Debug.i(TAG, "mFragTag :::::: " + mFragTag);
        if (mFragment != null && !mFragment!!.backPressed()) {
            handleBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleBackPressed() {
        // Debug.i(TAG, String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
        }
        super.onBackPressed()
    }

    /**
     * Method to push the fragment
     *
     * @param containerId
     * @param tag
     * @param fragment
     */
    fun pushFragments(containerId: Int, tag: String?, fragment: Fragment?) {

        // Debug.v("TestFragment", "pushFragments() tag = " + tag);
        if (!isFinishing) {
            val manager = supportFragmentManager
            val ft = manager.beginTransaction()
            when (tag) {
            }
            manager.executePendingTransactions()
            val count = manager.backStackEntryCount
        }
    }

    /**
     * This method shows the toast.
     */
    fun showToast(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun showCustomToast(message: String) {
        val toast = Toast.makeText(this, "" + message, Toast.LENGTH_LONG)
        val toastView = toast.view // This'll return the default View of the Toast.
        /* And now you can get the TextView of the default View of the Toast. */
        val toastMessage = toastView!!.findViewById<View>(android.R.id.message) as TextView
        toastMessage.textSize = 15f
        toastMessage.setTextColor(resources.getColor(R.color.white))
        toastMessage.gravity = Gravity.TOP or Gravity.CENTER_VERTICAL or Gravity.CENTER
        toastMessage.compoundDrawablePadding = 10
        toastMessage.setPadding(10, 0, 10, 0)
        toastView.setBackgroundColor(resources.getColor(R.color.purple_200))
        toast.show()
    }

    companion object {
        const val TOOLTIPS_ID = 1
    }

    override fun setFragment(fragment: FragmentListener?) {

    }
}