package com.contacts;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

public class MainPagerActivity extends FragmentActivity {
	
	ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
	
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						
						getActionBar().setSelectedNavigationItem(position);
					}
				});

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		};

		actionBar.addTab(actionBar.newTab().setText(R.string.select)
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(R.string.insert)
				.setTabListener(tabListener));

		setContentView(mViewPager);
		// ViewPager and its adapters use support library
		// fragments, so use getSupportFragmentManager.
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public Fragment getItem(int i) {
				Fragment fragment = null;
				switch (i) {
				case 0:
					fragment = new SelectListFragment();
					break;
				case 1:
					fragment = new InsertFragment();
					break;
				}
				return fragment;
			}

			@Override
			public int getCount() {
				return 2;
			}

		});
	}
}
