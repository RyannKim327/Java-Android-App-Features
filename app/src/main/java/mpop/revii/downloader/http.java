package mpop.revii.downloader;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class http extends AsyncTask {

	Context ctx;
	public http(Context context){
		ctx = context;
	}

	@Override
	protected String doInBackground(Object[] p1) {
		// Toast.makeText(ctx, "Ey", 1).show();
		String str = "";
		String u = "";
		try {
			u = URLEncoder.encode(p1[0].toString(), "UTF-8"); // URLEncoder.encode(p1[0].toString(), "UTF-8");
			String url = "https://avd.vercel.app/convert";
			// String data = URLEncoder.encode("url", "UTF-8") + "=" + u;
			// data += "&" + URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode("d212c03880b50913395c866b2c3c6138c6d5a35454624e278676aa97b96e95b7", "UTF-8");
			// URL link = new URL(url + u);
			String data = String.format("{\"url\": \"%s\"}", p1[0]);
			URL link = new URL(url); // + URLEncoder.encode(p1[0].toString(), "UTF-8"));
			
			URLConnection conn = link.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
			w.write(data);
			w.flush();
			// conn.setDoInput(true);
			BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String s;
			while ((s = read.readLine()) != null) {
				str = s;
				break;
			}
			return str;
		} catch (Exception e) {
			// Toast.makeText(ctx, e.getMessage(), 1).show();
			// str = "Error " + u + "\n" + e.getMessage();
			return e.toString();
		}
	}
	

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		try{
			Toast.makeText(ctx, "test " + result.toString(), 1).show();
		}catch(Exception e){
			Toast.makeText(ctx, "test 2 " + e.getMessage(), 1).show();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
}
