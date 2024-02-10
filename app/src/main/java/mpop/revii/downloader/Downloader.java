package mpop.revii.downloader;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Downloader extends RelativeLayout {
	Context ctx;
	public Downloader(Context cntx){
		super(cntx);
		ctx = cntx;
		init();
	}
	public Downloader(Context cntx, AttributeSet attr){
		super(cntx, attr);
		ctx = cntx;
		init();
	}
	void init(){
		try{
			http h = new http(ctx);
			h.execute("https://www.youtube.com/watch?v=HcHCCanP3W4");
		}catch(Exception e){
			Toast.makeText(ctx, e.getMessage(), 1).show();
		}
		Toast.makeText(ctx, "Test", 1).show();
	}
}
