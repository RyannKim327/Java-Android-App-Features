package mpop.revii.widget;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

public class Tester extends View{
	public Tester(final Context ctx, AttributeSet attr){
		super(ctx, attr);
		setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p1) {
				Intent intent = new Intent("mpop.revii.features.FileChooser.Activity");
				intent.putExtra("mpop.revii.features.FileChooser.CLASS", "BACKGROUND");
				ctx.sendBroadcast(intent);
			}
		});
	}
}
