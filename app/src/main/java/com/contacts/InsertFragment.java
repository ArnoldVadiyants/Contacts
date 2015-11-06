package com.contacts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class InsertFragment extends ContactFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = super.onCreateView(inflater, container, savedInstanceState);
		insertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Context context = getActivity();
				String phoneNumber = phoneEditText.getText().toString();
				String email = emailEditText.getText().toString();
				if (mContact.checkPhone(phoneNumber, context) && mContact.isValidEmail(email, context)) {
					mContact.setPhone(Long.parseLong(phoneNumber));
					mContact.setEMail(email);
					ContactLab.get(getActivity()).addContact(mContact);
					mContact = new Contact();
					isNewFragment = true;
					first_nameEditText.setText("");
					first_nameEditText.setFocusableInTouchMode(true);
					first_nameEditText.requestFocus();
					last_nameEditText.setText("");
					phoneEditText.setText("");
					emailEditText.setText("");
					SelectListFragment.notifyAdapter();
				}

			}

		});
		return v;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}
}
