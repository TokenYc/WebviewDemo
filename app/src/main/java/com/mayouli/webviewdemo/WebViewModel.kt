package com.mayouli.webviewdemo

import android.app.Application
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WebViewModel(application: Application) : AndroidViewModel(application) {
    var urlLiveData: MutableLiveData<String> = MutableLiveData()
    var webViewClient: MutableLiveData<WebViewClient> = MutableLiveData()

    init {
        webViewClient.value = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request?.url.toString())
                    }
                }
                return true
            }


            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    var url = request?.url.toString()
                    if (url.contains(".png")) {
                        var response = WebResourceResponse(
                            "image/png",
                            "UTF-8",
                            view?.resources?.assets?.open("ic_launcher.png")
                        )
                        return response
                    }
                    Log.d("web resource", "web resource-->" + request?.url.toString())
                }
                return super.shouldInterceptRequest(view, request)
            }
        }
        urlLiveData.value = "https://www.baidu.com"
    }


}