package mpop.revii.layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Intent;
import android.os.Handler;
import mpop.revii.utils.ImageUtils;

public class Background extends RelativeLayout{
	Context ctx;
	SharedPreferences sp;
	public Background(Context ctxx){
		super(ctxx);
		ctx = ctxx;
		init();
	}
	
	public Background(Context ctxx, AttributeSet attr){
		super(ctxx, attr);
		ctx = ctxx;
		init();
	}
	
	void init(){
		final ImageView v = new ImageView(ctx);
		try{
			sp = ctx.getSharedPreferences("mpop.revii.features", ctx.MODE_PRIVATE);
			String img = "";
			img = sp.getString("mpop.revii.features.BACKGROUND_DATA", img);
			v.setImageBitmap(ImageUtils.fetchImage(img));
			v.setScaleType(ImageView.ScaleType.FIT_XY);
		}catch(Exception e){
			Toast.makeText(ctx, e.getMessage(), 1).show();
		}
		
		v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		addView(v);
		ctx.registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context p1, Intent p2) {
				String data = p2.getStringExtra("mpop.revii.features.FileChooser.BACKGROUND_DATA");
				sp.edit().putString("mpop.revii.features.BACKGROUND_DATA", ImageUtils.pushImage(data)).commit();
				String img = sp.getString("mpop.revii.features.BACKGROUND_DATA", "");
				v.setImageBitmap(ImageUtils.fetchImage(img));
				v.setScaleType(ImageView.ScaleType.FIT_XY);
			}
		}, new IntentFilter("mpop.revii.features.FileChooser.BACKGROUND_FILE"));
	}
}
