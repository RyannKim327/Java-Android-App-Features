package mpop.revii.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.os.Handler;
import android.graphics.Color;

public class Toggles extends LinearLayout {
	Context ctx;
	public Toggles(Context context){
		super(context);
		ctx = context;
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				init();
			}
		}, 500);
	}
	
	public Toggles(Context context, AttributeSet attr){
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
		setBackgroundColor(Color.argb(0.7f, 0.33f, 0.33f, 0.33f));
		for(int i = 0; i < this.getChildCount(); i++){
			if(this.getChildAt(i) instanceof Switch){
				final Switch v = (Switch) getChildAt(i);
				final int j = i;
				if((i % 2) == 0){
					v.setBackgroundColor(Color.argb(0.7f, 0.33f, 0.33f, 0.33f));
					v.setTextColor(Color.WHITE);
				}else{
					v.setBackgroundColor(Color.argb(0.7f, 1f, 1f, 1f));
					v.setTextColor(Color.BLACK);
				}
				v.setOnCheckedChangeListener(new OnCheckedChangeListener(){
					@Override
					public void onCheckedChanged(CompoundButton p1, boolean p2) {
						String id = v.getText().toString().toLowerCase().replace(" ", "");
						Intent intent = new Intent(String.format("mpop.revii.CONTAINER_%s", id));
						int visible = p2 ? View.VISIBLE : View.GONE;
						if((j % 2) == 0){
							v.setBackgroundColor(p2 ? Color.argb(0.7f, 0.33f, 0.75f, 0.33f) : Color.argb(0.7f, 0.33f, 0.33f, 0.33f));
							v.setTextColor(Color.WHITE);
						}else{
							v.setBackgroundColor(p2 ? Color.argb(0.7f, 0.6f, 1f, 0.6f) : Color.argb(0.7f, 1f, 1f, 1f));
							v.setTextColor(Color.BLACK);
						}
						intent.putExtra(id, visible);
						ctx.sendBroadcast(intent);
					}
				});
			}
		}
	}
	
}
