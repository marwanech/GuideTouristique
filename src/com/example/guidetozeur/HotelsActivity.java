package com.example.guidetozeur;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.guidetozeur.utils.CustomAdapter;
import com.example.guidetozeur.utils.JSONParser;
import com.example.guidetozeur.utils.Place;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class HotelsActivity extends Activity {

	ListView hotels;
	
	// Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<Place> hotelsList;
 
    // url to get all products list
    private static String url_all_hotels = "http://ahmedd.byethost16.com/getallHotels.php";
    		
 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Hotels = "hotels";
    private static final String TAG_PID = "ID";
    private static final String TAG_NAME = "Name";
    private static final String TAG_DESCRIPTION = "Description";
    private static final String TAG_IMAGE = "Image";
    private static final String TAG_ICON = "Icon";
    private static final String TAG_LONGITUDE = "Longitude";
    private static final String TAG_LATITUDE = "Latitude";
    
    
 // products JSONArray
    JSONArray hotelsArray = null;
    
    CustomAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotels);
		
		// Permission StrictMode
		if (android.os.Build.VERSION.SDK_INT > 9) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		}
		
		hotelsList = new ArrayList<Place>();
		
		hotels = (ListView) findViewById(R.id.hotels);
		
		new LoadAllHotels().execute();
		
		hotels.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Place p = hotelsList.get(position);
				
				Intent goToInformations = new Intent(getApplicationContext(),InformationActivity.class);
				
				goToInformations.putExtra("pImage", p.getImage());
				goToInformations.putExtra("pName", p.getName());
				goToInformations.putExtra("pDescription", p.getDescription());
				goToInformations.putExtra("pLongitude", p.getLongitude());
				goToInformations.putExtra("pLatitude", p.getLatitude());
				
				startActivity(goToInformations);

			}
		});
		
		//String[] myHotels = new String[] {"Hotel 1", "Hotel 2"};
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, myHotels);
		
		//hotels.setAdapter(adapter);
		
	}


    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllHotels extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(AllProductsActivity.this);
            //pDialog.setMessage("Loading products. Please wait...");
            //pDialog.setIndeterminate(false);
            //pDialog.setCancelable(false);
            //pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_hotels, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Hotels: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    hotelsArray = json.getJSONArray(TAG_Hotels);
 
                    // looping through All Products
                    for (int i = 0; i < hotelsArray.length(); i++) {
                        JSONObject c = hotelsArray.getJSONObject(i);
 
                        // Storing each json item in variable
                        int id = c.getInt(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String description = c.getString(TAG_DESCRIPTION);
                        String image = c.getString(TAG_IMAGE);
                        String icon = c.getString(TAG_ICON);
                        Double longitude = c.getDouble(TAG_LONGITUDE);
                        Double latitude = c.getDouble(TAG_LATITUDE);
                        
                        Place p = new Place();
                        p.setId(id);
                        p.setName(name);
                        p.setDescription(description);
                        p.setImage(image);
                        p.setIcon(icon);
                        p.setLongitude(longitude);
                        p.setLatitude(latitude);
 
                        // creating new HashMap
                        //ArrayList<String> data = new ArrayList<String>();
 
                        // adding each child node to HashMap key => value
                        //map.put(TAG_PID, id);
                        hotelsList.add(p);
                        
 
                        // adding HashList to ArrayList
                        //productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    //Intent i = new Intent(getApplicationContext(),NewProductActivity.class);
                    // Closing all previous activities
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            //pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    
            		adapter = new CustomAdapter(getApplicationContext(), hotelsList);
            		
            		hotels.setAdapter(adapter);
                    
                }
            });
 
        }
        
    }
}
