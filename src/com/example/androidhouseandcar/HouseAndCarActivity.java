package com.example.androidhouseandcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class HouseAndCarActivity extends Activity {
	private HouseAndCarView houseCarView; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_and_car);
        
        houseCarView = new HouseAndCarView (this);
        setContentView(houseCarView);
        houseCarView.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.house_and_car, menu);
        return true;
    }
}
