package mpop.revii.layout;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
		final LinearLayout form = new LinearLayout(ctx);
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

		draw.getPaint().setColor(Color.parseColor("#333333"));
		pass.getPaint().setColor(Color.parseColor("#eeeeee"));

		form.setOrientation(LinearLayout.VERTICAL);
		form.setBackgroundDrawable(draw);

		password.setPadding(5, 3, 5, 3);
		password.setBackgroundDrawable(pass);

		form.addView(password);

		if(!sp.getString("mpop.revii.login.USER_PASSWORD", "").isEmpty()){
			new Handler().postDelayed(new Runnable(){
				@Override
				public void run(){
					addView(form);
				}
			}, 500);
		}
	}
}
