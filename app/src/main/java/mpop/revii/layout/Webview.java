package mpop.revii.layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import mpop.revii.utils.index;

public class Webview extends LinearLayout {
	Context ctx;
	
	LinearLayout urlForm, nav;
	EditText url;
	ImageButton go, prev, reload, next;
	ProgressBar load;
	WebView web;
	WebSettings webSettings;
	
	boolean type = false;
	
	public Webview(Context a){
		super(a);
		init(a);
	}
	public Webview(Context a, AttributeSet b){
		super(a, b);
		init(a);
	}
	
	void init(Context a){
		
		ctx = a;
		
		// Url
		
		urlForm = new LinearLayout(ctx);
		url = new EditText(ctx);
		go = new ImageButton(ctx);
		
		load = new ProgressBar(ctx, null, android.R.attr.progressBarStyleHorizontal);
		
		web = new WebView(ctx);
		webSettings = web.getSettings();
		
		// Footer
		nav = new LinearLayout(ctx);
		prev = new ImageButton(ctx);
		reload = new ImageButton(ctx);
		next = new ImageButton(ctx);
		
		// Main Configurations
		setOrientation(LinearLayout.VERTICAL);
		
		// Load configuration
		// load.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		// load.setProgressDrawable(new ColorDrawable(Color.parseColor("#ff0090")));
		
		// Url Configuration
		urlForm.setOrientation(LinearLayout.HORIZONTAL);
		urlForm.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		float size = url.getTextSize();
		url.setSingleLine();
		url.setHint("Enter URL or search something");
		url.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
		url.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
		url.setBackgroundColor(Color.TRANSPARENT);
		url.setTextSize(size * 0.5f);
		url.setTypeface(Typeface.SERIF);
		
		// go.setText("Go");
		go.setImageResource(index.setResources(ctx, "ic_go", "drawable", android.R.drawable.ic_media_ff));
		go.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		go.setBackgroundColor(Color.parseColor("#50000000"));
		
		urlForm.addView(url);
		urlForm.addView(go);
		
		// WebView
		web.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
		web.loadUrl("https://free.facebook.com"); //); pref.getString("mpop.revii.data.WEBVIEW","https://google.com"));
		web.clearCache(true);
		web.clearHistory();
		web.clearFormData();
		
		// Web Settings
		webSettings.setJavaScriptEnabled(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setSupportZoom(false);
		final String userAgent = webSettings.getUserAgentString();
		
		// Client
		web.setWebViewClient(new WebViewClient());
		web.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView wv, int p){
				String link = url.getText().toString();
				if(p >= 100){
					type = false;
					reload.setImageResource(index.setResources(ctx, "ic_reload", "drawable", android.R.drawable.ic_menu_rotate));
					if(web.getUrl().startsWith("http")){
						url.setText(web.getUrl());
					}
					url.setEnabled(true);
					load.setVisibility(View.GONE);
					go.setVisibility(View.VISIBLE);
					if(link.startsWith("http://") || link.startsWith("https://") || (!link.trim().contains(" ") && link.contains("."))){
						go.setImageResource(index.setResources(ctx, "ic_go", "drawable", android.R.drawable.ic_media_ff));
					}else{
						go.setImageResource(index.setResources(ctx, "ic_google", "drawable", android.R.drawable.ic_media_ff));
					}
				}else{
					type = true;
					reload.setImageResource(index.setResources(ctx, "ic_stop", "drawable", 0));
					url.setText(String.format("Please wait: %d", p));
					url.setEnabled(false);
					load.setVisibility(View.VISIBLE);
					load.setProgress(p);
					go.setVisibility(View.GONE);
					if(web.getUrl().contains("facebook.com") && web.getUrl().contains("/messages/")){
						if(web.getUrl().contains("/blocked")){
							web.loadUrl(web.getUrl().replace("/blocked", ""));
						}
						webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 13; SL112C Build/TP1A.220624.014; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/123.0.6312.40 Mobile Safari/537.36[FBAN/EMA;FBLC/en_US;FBAV/398.0.0.13.113;]");
					}else{
						webSettings.setUserAgentString(userAgent);
					}
				}
				prev.setVisibility(View.GONE);
				next.setVisibility(View.GONE);
				if(web.canGoBack()){
					prev.setVisibility(View.VISIBLE);
				}
				if(web.canGoForward()){
					next.setVisibility(View.VISIBLE);
				}
			}
			@Override
			public boolean onJsAlert(WebView wv, String url, String msg, JsResult result){
				return super.onJsAlert(wv, url, msg, result);
			}
			@Override
			public boolean onJsConfirm(WebView wv, String url, String msg, JsResult result){
				return super.onJsConfirm(wv, url, msg, result);
			}
			@Override
			public boolean onJsPrompt(WebView wv, String url, String msg, String def, JsPromptResult result){
				return super.onJsPrompt(wv, url, msg, def, result);
			}
		});
		
		
		// Navigation
		nav.setOrientation(LinearLayout.HORIZONTAL);
		nav.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		// prev.setText("Back");
		prev.setImageResource(index.setResources(ctx, "ic_prev", "drawable", android.R.drawable.ic_media_previous));
		prev.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
		prev.setBackgroundColor(Color.parseColor("#50000000"));
		
		// reload.setText("Reload");
		reload.setImageResource(index.setResources(ctx, "ic_reload", "drawable", android.R.drawable.ic_menu_rotate));
		reload.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
		reload.setBackgroundColor(Color.parseColor("#50000000"));
		
		// next.setText("Next");
		next.setImageResource(index.setResources(ctx, "ic_next", "drawable", android.R.drawable.ic_media_next));
		next.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
		next.setBackgroundColor(Color.parseColor("#50000000"));
		
		// EditText Action
		url.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View p1, int p2, KeyEvent p3) {
				if(p2 == KeyEvent.KEYCODE_ENTER){
					go();
				}
				return false;
			}
		});
		url.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {}
			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
				String link = url.getText().toString();
				if(link.startsWith("http://") || link.startsWith("https://") || (!link.trim().contains(" ") && link.contains("."))){
					go.setImageResource(index.setResources(ctx, "ic_go", "drawable", android.R.drawable.ic_media_ff));
				}else{
					go.setImageResource(index.setResources(ctx, "ic_google", "drawable", android.R.drawable.ic_media_ff));
				}
			}
			@Override
			public void afterTextChanged(Editable p1) {
				String link = url.getText().toString();
				if(link.startsWith("http://") || link.startsWith("https://") || (!link.trim().contains(" ") && link.contains("."))){
					go.setImageResource(index.setResources(ctx, "ic_go", "drawable", android.R.drawable.ic_media_ff));
				}else{
					go.setImageResource(index.setResources(ctx, "ic_google", "drawable", android.R.drawable.ic_media_ff));
				}
			}
		});
		
		// Buttons Action
		go.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				go();
			}
		});
		
		prev.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				if(web.canGoBack()){
					web.goBack();
				}
			}
		});
		
		reload.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				if(type){
					web.stopLoading();
					reload.setImageResource(index.setResources(ctx, "ic_reload", "drawable", android.R.drawable.ic_menu_rotate));
					url.setEnabled(true);
					if(web.getUrl().startsWith("http")){
						url.setText(web.getUrl());
					}
					go.setVisibility(View.VISIBLE);
				}else{
					web.reload();
					reload.setImageResource(index.setResources(ctx, "ic_stop", "drawable", android.R.drawable.ic_menu_rotate));
					url.setEnabled(false);
					url.setText("Please wait...");
					go.setVisibility(View.GONE);
				}
			}
		});
			
		next.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				if(web.canGoForward()){
					web.goForward();
				}
			}
		});
		
		nav.addView(prev);
		nav.addView(reload);
		nav.addView(next);
		
		addView(urlForm);
		addView(load);
		addView(web);
		addView(nav);
	}
	
	void go(){
		String link = url.getText().toString();
		if(link.trim().isEmpty()){
			Toast.makeText(ctx, "Please enter your url", 1).show();
		}else if(link.startsWith("http://") || link.startsWith("https://")){
			web.loadUrl(link);
		}else if(link.trim().contains(" ") || !link.contains(".")){
			web.loadUrl(String.format("https://www.google.com/search?q=%s", link));
		}else{
			web.loadUrl(String.format("https://%s", link));
		}
	}
}
