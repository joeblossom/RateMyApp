package com.RateMyAppDemo.activities;

import com.RateMyAppDemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.RateMyAppDemo.utilities.RateMyApp;

public class RateMyAppDemoActivity extends Activity implements OnClickListener {
	private Button ratemyappButton;
	private RateMyApp rmaTemp;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        rmaTemp = new RateMyApp(this);
        rmaTemp.app_launched();
        
        ratemyappButton = (Button) findViewById(R.id.ratemyapp_button);
        ratemyappButton.setOnClickListener(this);
    }
    
    @Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.ratemyapp_button:
			ratemyappButtonClicked();
			break;
		}
	}
    
    private void ratemyappButtonClicked() {
    	rmaTemp.showRateDialog(null);
	}
}