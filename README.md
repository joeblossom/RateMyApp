#RateMyApp for Android
RateMyApp allows you to ask your users to rate your app after a specified number of days or launches. (or both) 
You can also quickly style the dialog box to match your app.

##Install
Add the following files to your project:
- RateMyApp.java (from /src/com/RateMyAppDemo/utilities)
- ratemyapp_dialog.xml (from /res/layout)
- And the following strings to your strings.xml:
<pre>
<string name="ratemyapp_accept_button_label">Rate Now</string>
<string name="ratemyapp_later_button_label">Remind me later</string>
<string name="ratemyapp_cancel_button_label">No, thank you</string>
</pre>

##Usage
<pre>
		RateMyApp rmaTemp = new RateMyApp(this);
        rmaTemp.app_launched();
</pre>

When app_launched is called, it will increment the launch counter, check to see if the 
correct conditions are met to show the dialog, and show the dialog if necessary.

You can also do something like the following to open up the dialog whenever you want (no conditions need to be met):
<pre>
	RateMyApp rmaTemp = new RateMyApp(this);
	rmaTemp.showRateDialog(null);
</pre>

The RateMyAppDemoActivity.java (/src/com/RateMyAppDemo/activities) will show an implementation of both.

##Example
You can run the sample RateMyApp Demo to see how the dialog works.