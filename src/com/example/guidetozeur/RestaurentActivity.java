package com.example.guidetozeur;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.guidetozeur.BarActivity.LoadAllBARS;
import com.example.guidetozeur.utils.CustomAdapter;
import com.example.guidetozeur.utils.JSONParser;
import com.example.guidetozeur.utils.Place;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RestaurentActivity extends ActionBarActivity {
ListView Restaurent;
	
	// Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<Place> RestaurentList;
    
 
    // url to get all products list
    private static String url_all_Restaurent = "http://ahmedd.byethost16.com/getallRestaurent.php";
    		
 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Restaurent = "Restaurent";
    private static final String TAG_PID = "ID";
    private static final String TAG_NAME = "Name";
    private static final String TAG_DESCRIPTION = "Description";
    private static final String TAG_IMAGE = "Image";
    private static final String TAG_ICON = "Icon";
    
 // products JSONArray
    JSONArray RestaurentArray = null;
    
    CustomAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurent);
		
		// Permission StrictMode
				if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				}
		
		RestaurentList = new ArrayList<Place>();
		
		Restaurent = (ListView) findViewById(R.id.Restaurent);
		
		new LoadAllRestaurent().execute();
		

		
		//String[] myHotels = new String[] {"Hotel 1", "Hotel 2"};
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, myHotels);
		
		//hotels.setAdapter(adapter);
		
	}


    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllRestaurent extends AsyncTask<String, String, String> {
 
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
            JSONObject json = jParser.makeHttpRequest(url_all_Restaurent, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Restaurent: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	RestaurentArray = json.getJSONArray(TAG_Restaurent);
 
                    // looping through All Products
                    for (int i = 0; i < RestaurentArray.length(); i++) {
                        JSONObject c = RestaurentArray.getJSONObject(i);
 
                        // Storing each json item in variable
                        int id = c.getInt(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String description = c.getString(TAG_DESCRIPTION);
                        String image = c.getString(TAG_IMAGE);
                        String icon = c.getString(TAG_ICON);
                        
                        Place p = new Place();
                        p.setId(id);
                        p.setName(name);
                        p.setDescription(description);
                        p.setImage(image);
                        p.setIcon(icon);
 
                        // creating new HashMap
                        //ArrayList<String> data = new ArrayList<String>();
 
                        // adding each child node to HashMap key => value
                        //map.put(TAG_PID, id);
                        RestaurentList.add(p);
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
                	adapter = new CustomAdapter(getApplicationContext(), RestaurentList);
            		
            		Restaurent.setAdapter(adapter);
                    
                }
            });
 
        }
    }
}
