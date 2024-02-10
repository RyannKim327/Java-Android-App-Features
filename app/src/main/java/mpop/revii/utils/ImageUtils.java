package mpop.revii.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

public class ImageUtils {
	public static String pushImage(String path){
		Bitmap factor = BitmapFactory.decodeFile(path);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		factor.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bytes = stream.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
	public static Bitmap fetchImage(String data){
		byte[] bytes = Base64.decode(data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}
}
