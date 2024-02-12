package mpop.revii.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.text.method.PasswordTransformationMethod;

public class LoginSetup extends LinearLayout {
	Context ctx;
	public LoginSetup(Context c){
		super(c);
		ctx = c;
		init();
	}
	
	public LoginSetup(Context c, AttributeSet a){
		super(c, a);
		ctx = c;
		init();
	}

	void init(){
		EditText password = new EditText(ctx);
		EditText confirm = new EditText(ctx);
		Button send = new Button(ctx);

		setOrientation(LinearLayout.VERTICAL);

		password.setSingleLine();
		password.setTransformationMethod(new PasswordTransformationMethod());
		password.setHint("Enter your password here");
		password.setGravity(Gravity.CENTER);
		
		confirm.setSingleLine();
		confirm.setTransformationMethod(new PasswordTransformationMethod());
		confirm.setHint("Confirm your current password to proceed");
		confirm.setGravity(Gravity.CENTER);
		
		send.setText("Make changes");
		send.setGravity(Gravity.CENTER);

		addView(password);
		addView(confirm);
		addView(send);
	}
}
