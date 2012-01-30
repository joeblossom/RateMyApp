package com.RateMyAppDemo.utilities;

import com.RateMyAppDemo.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RateMyApp {
    private final static String APP_TITLE = "RateMyApp";
    private final static String APP_PACKAGENAME = "com.RateMyAppDemo";
    
    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 3;
    
    private Activity callerActivity;
    
    private Context mContext;
    
    private AlertDialog ratemyappDialog;
    
    public RateMyApp(Context context, Activity caller) {
		callerActivity = caller;
		mContext = context;
	}
    
    public void app_launched() {
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) { return; }
        
        SharedPreferences.Editor editor = prefs.edit();
        
        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }
        
        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
        	showRateDialog(editor);
        }
        else if(System.currentTimeMillis() >= date_firstLaunch + 
                (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
            showRateDialog(editor);
        }
        
        editor.commit();
    }   
    
    public void showRateDialog(final SharedPreferences.Editor editor) {
    	
    	AlertDialog.Builder builder;
    	
    	LayoutInflater inflater = callerActivity.getLayoutInflater();
		View layout = inflater.inflate(R.layout.ratemyapp_dialog, null);
		
		Button rateButton = (Button) layout.findViewById(R.id.ratemyapp_dialog_accept_button);
		Button laterButton = (Button) layout.findViewById(R.id.ratemyapp_dialog_later_button);
		Button cancelButton = (Button) layout.findViewById(R.id.ratemyapp_dialog_cancel_button);
		TextView titleTextView = (TextView) layout.findViewById(R.id.ratemyapp_dialog_title_textview);
		TextView messageTextView = (TextView) layout.findViewById(R.id.ratemyapp_dialog_info);
		
		titleTextView.setText("Rate " + APP_TITLE);
		
		String msg = "If you enjoy using " + APP_TITLE + ", please take a moment to rate it. Thank you for your support!";

		Spanned message = Html.fromHtml(msg); 

		messageTextView.setText(message);
		
		rateButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PACKAGENAME)));
                ratemyappDialog.dismiss();
            }
        });
		
        laterButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if (editor != null) {
            		editor.putLong("launch_count", 0);
                    editor.commit();
                }
            	ratemyappDialog.dismiss();
            }
        });
        
        cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                ratemyappDialog.dismiss();
            }
        });
        
        builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);

		ratemyappDialog = builder.create();
		ratemyappDialog.show();     
    }
}
