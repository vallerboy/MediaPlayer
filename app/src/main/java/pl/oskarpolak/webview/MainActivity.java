package pl.oskarpolak.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {


    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebClient());
       // webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(this, "Android");
        webView.loadUrl("http://google.pl");

        //webView.zoomIn(); Przybliżanie zawrtości strony
        //webView.goBack(); // Wracanie do poprzednij strony (historia)
        //webView.goForward(); // Przejście do
        //webView.canGoForward() //
        //webView.canGoBack()
    }

    @JavascriptInterface
    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void changeText(String text){
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        v.vibrate(2000);
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
             if(!url.equals("http://google.pl")){
                 Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.pl"));
                 startActivity(i);
                 return true;
             }
            return false;
        }
    }
}
