package mpop.revii.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Container extends FrameLayout{
	Context ctx;
	public Container(Context context){
		super(context);
		ctx = context;
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				init();
			}
		}, 500);
	}
	
	public Container(Context context, AttributeSet attr){
		super(context, attr);
		ctx = context;
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				init();
			}
		}, 500);
	}
	
	void init(){
		setVisibility(View.GONE);
		if(getChildCount() > 0){
			View view = getChildAt(0);
			if(view instanceof TextView){
				TextView v = (TextView) view;
				v.setVisibility(View.GONE);
				final String id = v.getText().toString().toLowerCase().replace(" ", "");
				ctx.registerReceiver(new BroadcastReceiver(){
					@Override
					public void onReceive(Context p1, Intent p2) {
						final int b = p2.getIntExtra(id, View.GONE);
						new Handler().postDelayed(new Runnable(){
							@Override
							public void run() {
								setVisibility(b);
							}
						}, 100);
					}
				}, new IntentFilter(String.format("mpop.revii.CONTAINER_%s", id)));
			}
		}
	}
	
}
