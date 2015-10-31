package com.example.simple_sqlite;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_Helper db = new DB_Helper(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..........."); 
        db.addInfo(new Info("Ravi", "9100000000"));        
        db.addInfo(new Info("Srinivas", "9199999999"));
         
        // Reading all contacts
        Log.d("Reading: ", "Reading all Info ............"); 
        List<Info> info = db.getAllInfo();       
         
        for (Info cn : info) {
            String log = "ID is : "+cn.getID() +" ,Name is : " + cn.getName() + " ,Address : " + cn.getAddress();
                // Writing Contacts to log
        Log.d("Name: ", log);
            Toast.makeText(getApplicationContext(), "Data is : " + log, Toast.LENGTH_SHORT).show();
        }
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
