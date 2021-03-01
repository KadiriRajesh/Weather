package rajeshkadiri.weathertestapi.view.activities

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import rajeshkadiri.weathertestapi.R

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val wv: WebView
        wv = findViewById<View>(R.id.webview) as WebView
        wv.loadUrl("file:///android_asset/help.html")
    }
}