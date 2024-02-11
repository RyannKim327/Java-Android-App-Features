package mpop.revii.layout;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.os.Handler;
import android.content.SharedPreferences;

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
		LinearLayout form = new LinearLayout(ctx);
		EditText password = new EditText(ctx);
		float d = 5f;
		float e = 25f;
		ShapeDrawable draw = new ShapeDrawable(new RoundRectShape(new float[]{
			d, d, d, d,
			d, d, d, d
		}, null, null))
		ShapeDrawable pass = new ShapeDrawable(new RoundRectShape(new float[]{
			e, e, e, e,
			e, e, e, e
		}, null, null))

		draw.getPaint().setColor(Color.parse("#333333"));
		pass.getPaint().setColor(Color.parse("#eeeeee"));

		form.setOrientation(LinearLayout.VERTICAL);
		form.setBackgroundDrawable(draw);

		password.setPadding(5, 3, 5, 3);
		pass.setBackgroundDrawable(pass);

		form.addView(pass);

		if(!sp.getString("mpop.revii.login.USER_PASSWORD", "").isEmpty()){
			new Handler().postDelayed(new Runnable(){
				addView(form);
			}, 500)
		}
	}
}
