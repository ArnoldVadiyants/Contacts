package com.contacts;

import java.util.ArrayList;

import android.content.Context;

public class ContactLab {
	ArrayList<Contact> mContacts;
	private static ContactLab sContactLab;
	private Context mAppContext;
	private ContactDatabaseHelper mDBHelper;

	private ContactLab(Context appContext) {

		mAppContext = appContext;
		mDBHelper = new ContactDatabaseHelper(mAppContext);
		mContacts = mDBHelper.loadArrayList();
	}

	public static ContactLab get(Context c) {
		if (sContactLab == null) {
			sContactLab = new ContactLab(c.getApplicationContext());
		}
		return sContactLab;
	}

	public void addContact(Contact c) {
		long rowID = mDBHelper.insertContact(c, mAppContext);
		c.setId(rowID);
		mContacts.add(c);
	}

	public void updateContact(Contact c) {
		mDBHelper.updateContact(c, mAppContext);
	}

	public void deleteContact(Contact c) {
		mDBHelper.deleteContact(c.getId());
		mContacts.remove(c);
	}

	public ArrayList<Contact> getContacts() {
		return mContacts;
	}

	public Contact getContact(long id) {
		for (Contact c : mContacts) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}
}
