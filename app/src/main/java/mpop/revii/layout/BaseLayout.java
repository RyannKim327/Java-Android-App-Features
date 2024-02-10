package mpop.revii.layout;
import android.widget.RelativeLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.os.Handler;
import android.view.Gravity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.Color;

public class BaseLayout extends RelativeLayout {
	Context ctx;
	public BaseLayout(Context c){
		super(c);
		ctx = c;
		init();
	}
	public BaseLayout(Context c, AttributeSet a){
		super(c, a);
		ctx = c;
		init();
	}
	void init(){
		setGravity(Gravity.BOTTOM);
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				drop_layout();
				Login login = new Login(ctx);
				addView(login);
			}
		}, 500);
	}
	void drop_layout(){
		RelativeLayout b = new RelativeLayout(ctx);
		
		FileChooser file = new FileChooser(ctx);
		ShapeDrawable d = new ShapeDrawable(new RoundRectShape(new float[]{
			15, 15, 15, 15,
			0, 0, 0, 0
		}, null, null));
		d.getPaint().setColor(Color.parseColor("#dedede"));
		file.setBackgroundDrawable(d);
		b.setPadding(0, 250, 0, 0);
		
		b.addView(file);
		addView(b);
	}
}
