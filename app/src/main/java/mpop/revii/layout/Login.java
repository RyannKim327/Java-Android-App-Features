package mpop.revii.layout;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Login extends RelativeLayout {
	Context ctx;
	SharedPreferences sp;
	public Login(Context cx){
		super(cx);
		ctx = cx;
		init();
	}
	public Login(Context cx, AttributeSet attr){
		super(cx, attr);
		ctx = cx;
		init();
	}

	void init(){
		sp = ctx.getSharedPreferences("mpop.revii.login", ctx.MODE_PRIVATE);
		final RelativeLayout base = new RelativeLayout(ctx);
		final LinearLayout form = new LinearLayout(ctx);
		TextView title = new TextView(ctx);
		EditText password = new EditText(ctx);
		float d = 5f;
		float e = 25f;
		ShapeDrawable draw = new ShapeDrawable(new RoundRectShape(new float[]{
			d, d, d, d,
			d, d, d, d
		}, null, null));
		ShapeDrawable pass = new ShapeDrawable(new RoundRectShape(new float[]{
			e, e, e, e,
			e, e, e, e
		}, null, null));

		base.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT ));
		
		draw.getPaint().setColor(Color.parseColor("#333333"));
		pass.getPaint().setColor(Color.parseColor("#eeeeee"));
		
		form.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT ));
		form.setOrientation(LinearLayout.VERTICAL);
		form.setBackgroundDrawable(draw);
		form.setPadding(8, 10, 8, 5);
		
		base.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){

			}
		});

		title.setText(sp.getString("mpop.revii.login.HINT", "Enter your password here"));
		title.setTextSize(25);
		title.setTypeface(Typeface.SERIF);
		title.setTextColor(Color.parseColor("#ffffff"));

		password.setPadding(5, 3, 5, 3);
		password.setBackgroundDrawable(pass);

		form.addView(title);
		form.addView(password);
		base.addView(form);
		if(!sp.getString("mpop.revii.login.USER_PASSWORD", "").isEmpty()){
			new Handler().postDelayed(new Runnable(){
				@Override
				public void run(){
					addView(base);
				}
			}, 500);
		}
	}
}
