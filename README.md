#RateMyApp for Android
RateMyApp allows you to ask your users to rate your app after a specified number of days or launches. (or both) 
You can also quickly style the dialog box to match your app.

##Install
Add the following files to your project:

* RateMyApp.java (from /src/com/RateMyAppDemo/utilities)
* ratemyapp_dialog.xml (from /res/layout)
* And the following strings to your strings.xml:

<string name="ratemyapp_accept_button_label">Rate Now</string>
<string name="ratemyapp_later_button_label">Remind me later</string>
<string name="ratemyapp_cancel_button_label">No, thank you</string>

You can change the number of launches or days until prompt in the RateMyApp.java, by updating the 
variables LAUNCHES_UNTIL_PROMPT or DAYS_UNTIL_PROMPT, respectively. The DAYS_AND_LAUNCHES variable 
in RateMyApp.java specifies if you want both launches and days requirement to be met before the dialog 
is shown, or just one.

##Usage
<pre><code>
RateMyApp rmaTemp = new RateMyApp(this);
rmaTemp.app_launched();
</code></pre>

When app_launched is called, it will increment the launch counter, check and show the dialog if the
correct conditions are met.

You can also do something like the following to open up the dialog whenever you want (no conditions need to be met):
<pre>
RateMyApp rmaTemp = new RateMyApp(this);
rmaTemp.showRateDialog(null);
</code></pre>

The RateMyAppDemoActivity.java (/src/com/RateMyAppDemo/activities) will show an implementation of both.

##Example
You can run the sample RateMyApp Demo to see how the dialog works.