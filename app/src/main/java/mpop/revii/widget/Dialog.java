package mpop.revii.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Dialog extends FrameLayout{
	Context ctx;
	LinearLayout base;
	TextView title, message;
	Button positive, negative, neutral;
	
	public Dialog(Context context){
		super(context);
		ctx = context;
		init();
	}
	public Dialog(Context context, AttributeSet attr){
		super(context, attr);
		ctx = context;
		init();
	}
	void init(){
		base = new LinearLayout(ctx);
		
		base.setOrientation(View.GONE);
	}
	public void show(){
		base.setVisibility(View.VISIBLE);
	}
	public void close(){
		base.setVisibility(View.GONE);
	}
}
