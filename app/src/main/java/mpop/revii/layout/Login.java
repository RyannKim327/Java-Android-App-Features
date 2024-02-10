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

		form.setOrientation(LinearLayout.VERTICAL);

		if(!sp.getString("mpop.revii.login.USER_PASSWORD", "").isEmpty()){
			new Handler().postDelayed(new Runnable(){
				addView(form);
			}, 500)
		}
	}
}
