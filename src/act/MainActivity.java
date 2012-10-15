package act;

import cl.utfsm.aemf.BehaviorService;
import cl.utfsm.monitotingframework.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Iniciar servicio listener
		Intent intent = new Intent(this, BehaviorService.class);
		startService(intent);
	}
	
	
	public class StartupIntentReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Intent serviceIntent = new Intent();
			serviceIntent
					.setAction("cl.utfsm.aemf.BehaviorService");
			context.startService(serviceIntent);
		}

	}

	@Override
	public void onDestroy() {
		finish();
		super.onDestroy();
	}

}
