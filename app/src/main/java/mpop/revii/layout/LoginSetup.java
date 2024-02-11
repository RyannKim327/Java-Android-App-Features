package mpop.revii.layout;

public class LoginSetup extends LinearLayout{
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
		password.setTransformMethod(new PasswordTransformMethod());
		password.setHint("Enter your password here");
		
		
		confirm.setSingleLine();
		password.setTransformMethod(new PasswordTransformMethod());
		confirm.setHint("Confirm your current password to proceed");
		
		base.addView(password);
		base.addView(confirm);
		base.addView(send);
	}
}