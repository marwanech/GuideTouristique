package com.example.guidetozeur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends Activity {

	ImageView imageView;
	TextView nameTV;
	TextView descriptionTV;
	//TextView Longitude;
	//TextView Latitude;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		imageView = (ImageView) findViewById(R.id.pImage);
		nameTV = (TextView) findViewById(R.id.pName);
		descriptionTV = (TextView) findViewById(R.id.pDiscription);
		//Longitude= (TextView) findViewById(R.id.pLongitude);
		//Latitude = (TextView) findViewById(R.id.pLatitude);
		
		Intent info = getIntent();
		
		String image = info.getStringExtra("pImage");
		String name = info.getStringExtra("pName");
		String description = info.getStringExtra("pDescription");
		//Double Longitude = info.getDoubleExtra("pLongitude", 0);
		//Double Latitude = info.getDoubleExtra("pLatitude", 0);
		
		imageView.setImageBitmap(loadBitmap(image));
		nameTV.setText(name);
		descriptionTV.setText(description);
		//Longitude.setText(String.valueOf(longitude));
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private static final String TAG = "ERROR";
	private static final int IO_BUFFER_SIZE = 4 * 1024;
	public static Bitmap loadBitmap(String url) {
	    Bitmap bitmap = null;
	    InputStream in = null;
	    BufferedOutputStream out = null;

	    try {
	        in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);

	        final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
	        out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
	        copy(in, out);
	        out.flush();

	        final byte[] data = dataStream.toByteArray();
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        //options.inSampleSize = 1;

	        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,options);
	    } catch (IOException e) {
	        Log.e(TAG, "Could not load Bitmap from: " + url);
	    } finally {
	        closeStream(in);
	        closeStream(out);
	    }

	    return bitmap;
	}

	 private static void closeStream(Closeable stream) {
	        if (stream != null) {
	            try {
	                stream.close();
	            } catch (IOException e) {
	                android.util.Log.e(TAG, "Could not close stream", e);
	            }
	        }
	    }
	 
	 private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }
}
