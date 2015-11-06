package com.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//ContactSelectedFragment and InsertFragment extend  ContactFragment
public abstract class ContactFragment extends Fragment {
	

	Contact mContact;
	EditText first_nameEditText;
	EditText last_nameEditText;
	EditText phoneEditText;
	EditText emailEditText;
	Button insertButton;

	boolean isNewFragment = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mContact = new Contact();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.contact_fragment, container, false);
		first_nameEditText = (EditText) v.findViewById(R.id.first_nameEditText);
		last_nameEditText = (EditText) v.findViewById(R.id.last_nameEditText);
		phoneEditText = (EditText) v.findViewById(R.id.phoneEditText);
		emailEditText = (EditText) v.findViewById(R.id.emailEditText);
		insertButton = (Button) v.findViewById(R.id.insertButton);

		first_nameEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before,
					int count) {
					mContact.setFirstName(c.toString());
			}
			public void beforeTextChanged(CharSequence c, int start, int count,
					int after) {}
			public void afterTextChanged(Editable c) {}
		});

		last_nameEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before,
					int count) {
				mContact.setLastName(c.toString());
				}
			public void beforeTextChanged(CharSequence c, int start, int count,
					int after) {}
			public void afterTextChanged(Editable c) {}
		});

		phoneEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before,
					int count) {
				try {
					long phone = Long.parseLong(c.toString());
				} catch (NumberFormatException e) {
					// if the insertButton is pressed is not created toast
					if (isNewFragment) {
						isNewFragment = false;
					} else {
						Toast.makeText(getActivity(),
								"Enter please only digit", Toast.LENGTH_SHORT)
								.show();
					}
				}
			}
			public void beforeTextChanged(CharSequence c, int start, int count,
					int after) {}
			public void afterTextChanged(Editable c) {}
		});

		emailEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before,
					int count) {
				}
			public void beforeTextChanged(CharSequence c, int start, int count,
					int after) {}
			public void afterTextChanged(Editable c) {}
		});
		return v;
	}
}
