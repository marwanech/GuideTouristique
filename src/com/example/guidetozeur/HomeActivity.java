package com.example.guidetozeur;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
    }
    
    public void Hotels_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), HotelsActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Restaurent_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), RestaurentActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Residence_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), ResidenceActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Bar_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), BarActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Agence_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), AgenceActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Loisire_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), LoisireActivity.class);
    	startActivity(goToNextActivity);
    }
    public void Autres_Click(View vi){
    	Intent goToNextActivity = new Intent(getApplicationContext(), AutresActivity.class);
    	startActivity(goToNextActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
