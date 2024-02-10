package mpop.revii.layout;
import android.widget.ListView;
import android.content.Context;
import android.util.AttributeSet;
import java.io.File;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.view.View;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import android.widget.ListAdapter;
import android.view.animation.TranslateAnimation;

public class FileChooser extends ListView {
	Context ctx;
	String dir = "/storage/emulated/0/";
	String datatypes = "jpg,png,jpeg";
	String _class;
	public FileChooser(Context context){
		super(context);
		ctx = context;
		a();
	}
	public FileChooser(Context context, AttributeSet attr){
		super(context, attr);
		ctx = context;
		a();
	}
	void a(){
		setVisibility(View.GONE);
		ctx.registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context p1, Intent p2) {
				if(p2.getStringExtra("mpop.revii.feature.FileChooser.DATATYPES") != null){
					if(p2.getStringExtra("mpop.revii.feature.FileChooser.DATATYPES").trim().toLowerCase() != ""){
						datatypes = p2.getStringExtra("mpop.revii.feature.FileChooser.DATATYPES").trim().toLowerCase();
					}
				}
				if(p2.getStringExtra("mpop.revii.features.FileChooser.CLASS") != null){
					if(p2.getStringExtra("mpop.revii.features.FileChooser.CLASS").trim() != ""){
						_class = p2.getStringExtra("mpop.revii.features.FileChooser.CLASS").trim();
					}
				}
				if(getVisibility() == View.GONE){
					TranslateAnimation anim = new TranslateAnimation(0, 0, 1000, 0);
					anim.setDuration(500);
					setVisibility(View.VISIBLE);
					setAnimation(anim);
					b();
				}
			}
		}, new IntentFilter("mpop.revii.features.FileChooser.Activity"));
	}
	void b(){
		File file = new File(dir);
		if(file.isDirectory()){
			final ArrayList dirs = new ArrayList(); // file.list();
			String[] ds = file.list();
			dirs.add("...");
			for(int i = 0; i < ds.length; i++){
				dirs.add(ds[i]);
			}
			Collections.sort(dirs);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_expandable_list_item_1, dirs);
			setAdapter(adapter);
			setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					if(p3 == 0){
						if(dir == "/storage/emulated/0/"){
							TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 1000);
							anim.setDuration(500);
							setVisibility(View.GONE);
							setAnimation(anim);
						}else{
							c();
						}
					}else{
						dir += p1.getItemAtPosition(p3);
						if(new File(dir).isDirectory()){
							dir += "/";
						}else{
							Intent intent = new Intent();
							intent.putExtra("", dir);
							ctx.sendBroadcast(intent);
						}
						b();
					}
				}
			});
		}else{
			String type = dir.split("/")[dir.split("/").length - 1]; // [dir.split(".").length - 1];
			String last = type.split("\\.")[type.split("\\.").length - 1].toLowerCase();
			if(datatypes.contains(last)){
				TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 1000);
				anim.setDuration(500);
				setVisibility(View.GONE);
				setAnimation(anim);
				Intent intent = new Intent(String.format("mpop.revii.features.FileChooser.%s_FILE", _class));
				intent.putExtra(String.format("mpop.revii.features.FileChooser.%s_DATA", _class), dir);
				ctx.sendBroadcast(intent);
				String[] dirs = dir.split("/");
				dir = "/";
				for(int i = 0; i < dirs.length - 1; i++){
					dir += dirs[i] + "/";
				}
			}else{
				c();
			}
		}
	}
	void c(){
		String[] dirs = dir.split("/");
		dir = "/";
		for(int i = 0; i < dirs.length - 1; i++){
			dir += dirs[i] + "/";
		}
		b();
	}
}
