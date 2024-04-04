 package mpop.revii.utils;

import android.content.Context;

public class index {
	public static int setResources(Context ctx, String name,String type, int _default){
		try{
			int n = ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
			if(n == 0){
				return _default;
			}
			return n;
		}catch(Exception e){
			return _default;
		}
	}
}
