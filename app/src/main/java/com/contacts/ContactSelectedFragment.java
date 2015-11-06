package com.contacts;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ContactSelectedFragment extends ContactFragment {
	public static final String EXTRA_CONTACT_ID = "com.contacts.contact_id";
	boolean isClickable = false;
	private Contact mEditContact;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
		long contactId = getArguments().getLong(EXTRA_CONTACT_ID);
		mEditContact = ContactLab.get(getActivity()).getContact(contactId);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = super.onCreateView(inflater, container, savedInstanceState);

		if (NavUtils.getParentActivityName(getActivity()) != null) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		isNewFragment = true;
		setText();
		setClickable();
		insertButton.setVisibility(View.GONE);

		return v;
	}

	public void update() {
		mEditContact.setFirstName(mContact.getFirstName());
		mEditContact.setLastName(mContact.getLastName());
		ContactLab.get(getActivity()).updateContact(mEditContact);
	}

	public void setText() {
		first_nameEditText.setText(mEditContact.getFirstName());
		last_nameEditText.setText(mEditContact.getLastName());
		phoneEditText.setText(mEditContact.phoneToString());
		emailEditText.setText(mEditContact.getEMail());
	}

	public void setClickable() {
		first_nameEditText.setEnabled(isClickable);
		first_nameEditText.setCursorVisible(isClickable);
		first_nameEditText.setTextColor(Color.BLACK);

		last_nameEditText.setEnabled(isClickable);
		last_nameEditText.setCursorVisible(isClickable);
		last_nameEditText.setTextColor(Color.BLACK);

		phoneEditText.setEnabled(isClickable);
		phoneEditText.setCursorVisible(isClickable);
		phoneEditText.setTextColor(Color.BLACK);

		emailEditText.setEnabled(isClickable);
		emailEditText.setTextColor(Color.BLACK);
		emailEditText.setCursorVisible(isClickable);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		case R.id.menu_item_edit_contact:
			if (isClickable == false) {
				item.setTitle(R.string.save_contact);
				item.setIcon(android.R.drawable.ic_menu_save);
				isClickable = true;
				setClickable();
			} else {
				Context context = getActivity();
				String phoneNumber = phoneEditText.getText().toString();
				if (mContact.checkPhone(phoneNumber, context)) {
					mEditContact.setPhone(Long.parseLong(phoneNumber));
				} else {
					phoneEditText.setText("" + mEditContact.phoneToString());
				}
				String email = emailEditText.getText().toString();
				if(mContact.isValidEmail(email, context))
				{
					mEditContact.setEMail(email);
				}
				else
				{
					 emailEditText.setText(mEditContact.getEMail());
				}
				update();
				item.setTitle(R.string.edit_contact);
				item.setIcon(android.R.drawable.ic_menu_edit);
				isClickable = false;
				setClickable();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public static ContactSelectedFragment newInstance(long contactId) {
		Bundle args = new Bundle();
		args.putLong(EXTRA_CONTACT_ID, contactId);
		ContactSelectedFragment fragment = new ContactSelectedFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.selected_contact_fragment, menu);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setText();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
