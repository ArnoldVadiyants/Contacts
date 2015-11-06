package com.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class SplashScreenFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Thread logoTimer = new Thread() {
			int progressStatus = 0;

			public void run() {
				try {
					while (progressStatus < 2000) {
						sleep(100);
						progressStatus = progressStatus + 100;
					}
					startActivity(new Intent(getActivity(),
							MainPagerActivity.class));
				} catch (InterruptedException e) {
					// TODO Auto-generated method stub
					e.printStackTrace();
				} finally {
					getActivity().finish();
				}
			}
		};
		logoTimer.start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.splash_screen_fragment, container,
				false);
		getActivity().getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		return v;
	}
}
