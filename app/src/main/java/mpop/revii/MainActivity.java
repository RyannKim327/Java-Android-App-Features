package mpop.revii;
 
import android.app.Activity;
import android.os.Bundle;
import mpop.revii.feature.R;
import mpop.revii.layout.Webview;

public class MainActivity extends Activity { 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Material_NoActionBar);
		// setContentView(R.layout.activity_main);
		setContentView(new Webview(this));
	}
}
