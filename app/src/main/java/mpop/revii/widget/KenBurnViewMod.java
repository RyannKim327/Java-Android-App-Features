package mpop.revii.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import mpop.revii.utils.ImageUtils;

public class KenBurnViewMod extends KenBurnsView {
	Context ctx;
	SharedPreferences sp;
	public KenBurnViewMod(Context ctx1){
		super(ctx1);
		ctx = ctx1;
		init();
	}
	public KenBurnViewMod(Context ctx1, AttributeSet attr){
		super(ctx1, attr);
		ctx = ctx1;
		init();
	}
	void init(){
		sp = ctx.getSharedPreferences("mpop.revii.features", ctx.MODE_PRIVATE);
		try{
			setImageBitmap(ImageUtils.fetchImage(sp.getString("mpop.revii.features.KENBURNMOD_DATA", "")));
		}catch(Exception e){
			e.printStackTrace();
		}
		setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				Intent intent = new Intent("mpop.revii.features.FileChooser.Activity");
				intent.putExtra("mpop.revii.features.FileChooser.CLASS", "KENBURNMOD");
				ctx.sendBroadcast(intent);
			}
		});
		ctx.registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context p1, Intent p2) {
				try{
					String data = p2.getStringExtra("mpop.revii.features.FileChooser.KENBURNMOD_DATA");
					sp.edit().putString("mpop.revii.features.KENBURNMOD_DATA", ImageUtils.pushImage(data)).commit();
					String img = sp.getString("mpop.revii.features.KENBURNMOD_DATA", "");
					setImageBitmap(ImageUtils.fetchImage(img));
					setScaleType(ImageView.ScaleType.FIT_XY);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}, new IntentFilter("mpop.revii.features.FileChooser.KENBURNMOD_FILE"));
		setTransitionListener(new TransitionListener(){
			@Override
			public void onTransitionStart(Transition transition) {
			}
			@Override
			public void onTransitionEnd(Transition transition) {
			}
		});
		setTransitionGenerator(new RandomTransitionGenerator());
	}
}
