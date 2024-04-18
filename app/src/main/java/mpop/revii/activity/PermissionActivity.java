package mpop.revii.activity;
import android.app.Activity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import android.content.Intent;

public class PermissionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context p1, Intent p2) {
				requestPermissions(new String[]{p2.getStringExtra("mpop.revii.extra.PERMISSION")},0);
			}
		}, new IntentFilter("mpop.revii.activity.PERMISSION"));
	}
	
}
