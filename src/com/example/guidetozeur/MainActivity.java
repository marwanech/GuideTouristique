package com.example.guidetozeur;

import android.support.v7.app.ActionBarActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	/** Called when the activity is first created. */
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // create the TabHost that will contain the Tabs
            TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


            TabSpec tab1 = tabHost.newTabSpec("First Tab");
            TabSpec tab2 = tabHost.newTabSpec("Second Tab");
            TabSpec tab3 = tabHost.newTabSpec("Third tab");

           // Set the Tab name and Activity
           // that will be opened when particular Tab will be selected
            tab1.setIndicator("About");
            tab1.setContent(new Intent(this,AboutActivity.class));
            
            tab2.setIndicator("Home");
            tab2.setContent(new Intent(this,HomeActivity.class));

            tab3.setIndicator("Map");
            tab3.setContent(new Intent(this,MapActivity.class));
            
            /** Add the tabs  to the TabHost to display. */
            tabHost.addTab(tab1);
            tabHost.addTab(tab2);
            tabHost.addTab(tab3);

    }
}