package com.contacts;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.WindowManager;

public class ContactPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Contact> mContacts;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		mContacts = ContactLab.get(this).getContacts();
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mContacts.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				Contact contact = mContacts.get(arg0);
				return ContactSelectedFragment.newInstance(contact.getId());
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Contact contact = mContacts.get(arg0);
				if (contact.getFirstName() != null
						&& contact.getLastName() != null) {
					setTitle(contact.getFirstName() + " "
							+ contact.getLastName());
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		long contactId = getIntent().getLongExtra(
				ContactSelectedFragment.EXTRA_CONTACT_ID, 1);
		for (int i = 0; i < mContacts.size(); i++) {
			if (mContacts.get(i).getId() == contactId) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}

}
