package com.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BroadCastSampleActivity extends Activity {
	/** Called when the activity is first created. */
	TextView tv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView)findViewById(R.id.tv);
		
		this.registerReceiver(this.mConnReceiver,
				new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
			boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

			NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

			if(currentNetworkInfo.isConnected()){
				tv.setText("NetWork Connection Available");
			}else{
				tv.setText("NetWork Connection not Available");
			}
		}
	};
}
