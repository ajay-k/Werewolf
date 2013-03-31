package com.kumar.werewolf;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class Splash extends Activity {
	MediaPlayer ourSound;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		ourSound= MediaPlayer.create(Splash.this, R.raw.splash_intro);
		ourSound.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(4000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMainActivity = new Intent(Splash.this, MainActivity.class);
					Splash.this.startActivity(openMainActivity);
					//Intent openMainActivity = new Intent("crew.labs.werewolf.MAINACTIVITY");
					//startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSound.release();
		finish();
	}

}
