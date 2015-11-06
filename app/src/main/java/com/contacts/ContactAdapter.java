package com.contacts;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
	Context c;

	public ContactAdapter(Context context, ArrayList<Contact> contacts) {
		super(context, 0, contacts);
		c = context;
		setNotifyOnChange(true);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item_contact, null);
		}
		Contact c = getItem(position);
		TextView fisrtNameTextView = (TextView) convertView
				.findViewById(R.id.first_nameTextView);
		fisrtNameTextView.setText(c.getFirstName());

		TextView lastNameTextView = (TextView) convertView
				.findViewById(R.id.last_nameTextView);
		lastNameTextView.setText(c.getLastName());

		TextView phoneTextView = (TextView) convertView
				.findViewById(R.id.phoneTextView);

		phoneTextView.setText(c.phoneToString());

		TextView emailTextView = (TextView) convertView
				.findViewById(R.id.emailTextView);
		emailTextView.setText(c.getEMail());

		return convertView;

	}
}