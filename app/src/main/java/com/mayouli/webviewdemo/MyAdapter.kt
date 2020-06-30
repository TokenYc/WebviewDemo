package com.mayouli.webviewdemo

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter


@BindingAdapter("android:url")
fun loadUrl(webview: WebView, url: String) {
    webview.loadUrl(url)
}

@BindingAdapter("android:webView_client")
fun setWebviewClient(webview: WebView,clent:WebViewClient){
    webview.webViewClient = clent
}

