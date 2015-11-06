package com.contacts;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ContactDatabaseHelper extends SQLiteOpenHelper {
	
	private final static String TAG = "ContactDatabaseHelper";
	
	private static final String DB_NAME = "contacts.sqlite";
	private static final int VERSION = 1;
	private static final String TABLE_CONTACT = "contact";
	private static final String COLUMN_CONTACT_ID = "contact_id";
	private static final String COLUMN_FIRST_NAME = "first_name";
	private static final String COLUMN_LAST_NAME = "last_name";
	private static final String COLUMN_PHONE = "phone";
	private static final String COLUMN_EMAIL = "email";

	public ContactDatabaseHelper(Context context) {
	
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	
		db.execSQL("create table contact ("
				+ "contact_id integer primary key autoincrement,"
				+ "first_name text," + "last_name text," + "phone integer,"
				+ "email text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public ArrayList<Contact> loadArrayList() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_CONTACT, null, null, null, null, null, null);
		if (c.moveToFirst()) {

	
			int idColIndex = c.getColumnIndex(COLUMN_CONTACT_ID);
			int first_nameColIndex = c.getColumnIndex(COLUMN_FIRST_NAME);
			int last_nameColIndex = c.getColumnIndex(COLUMN_LAST_NAME);
			int phoneColIndex = c.getColumnIndex(COLUMN_PHONE);
			int emailColIndex = c.getColumnIndex(COLUMN_EMAIL);

			do {
				Contact contact = new Contact();
				contact.setId(c.getLong(idColIndex));
				contact.setFirstName(c.getString(first_nameColIndex));
				contact.setLastName(c.getString(last_nameColIndex));
				contact.setPhone(c.getLong(phoneColIndex));
				contact.setEMail(c.getString(emailColIndex));
				contacts.add(contact);
			} while (c.moveToNext());
		} else
			c.close();
		return contacts;
	}

	public long insertContact(Contact contact, Context context) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = getWritableDatabase();

		cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
		cv.put(COLUMN_LAST_NAME, contact.getLastName());
		cv.put(COLUMN_PHONE, contact.getPhone());
		cv.put(COLUMN_EMAIL, contact.getEMail());

		long rowID = db.insert(TABLE_CONTACT, null, cv);
		if(rowID == -1)
		{
			Toast.makeText(context, "Error, contact is not added",
					Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(context, "Contact was successfully added",
					Toast.LENGTH_SHORT).show();
			
		}
		return rowID;

	}

	public void updateContact(Contact c, Context context) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = getWritableDatabase();
		cv.put(COLUMN_FIRST_NAME, c.getFirstName());
		cv.put(COLUMN_LAST_NAME, c.getLastName());
		cv.put(COLUMN_PHONE, c.getPhone());
		cv.put(COLUMN_EMAIL, c.getEMail());

		int updCount = db.update(TABLE_CONTACT, cv, "contact_id = ?",
				new String[] { String.valueOf(c.getId()) });
		Toast.makeText(context, "Contact was successfully updated",
				Toast.LENGTH_SHORT).show();
	}

	public void deleteContact(long id) {
		SQLiteDatabase db = getWritableDatabase();
		String whereClause = (COLUMN_CONTACT_ID + " = " + id);
		int delCount = db.delete(TABLE_CONTACT, whereClause, null);
	}
}
