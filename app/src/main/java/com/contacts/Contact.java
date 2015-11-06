package com.contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

public class Contact {
	private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile(
			"^[A-Z0-9._%+-]{2,}@[A-Z0-9.-]{2,}\\.[A-Z]{1,6}$",
			Pattern.CASE_INSENSITIVE);

	private long mId;
	private String mFirstName;
	private String mLastName;
	private long mPhone;
	private String mEMail;

	public Contact() {
		super();
		mId = 0;
		mFirstName = "";
		mLastName = "";
		mPhone = 0;
		mEMail = "";
	}

	public long getId() {
		return mId;
	}

	public void setId(long mId) {
		this.mId = mId;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public long getPhone() {

		return mPhone;
	}

	public String phoneToString() {
		long phone = mPhone;
		String firstDigit = "";
		int length = 0;
		while (phone > 0) {
			phone = phone / 10;
			++length;
		}
		if (length == 9) {
			firstDigit = "0";
		}
		return firstDigit + mPhone;
	}

	public void setPhone(long mPhone) {
		this.mPhone = mPhone;
	}

	public boolean checkPhone(String phoneString, Context context) {
		boolean isCorrectNumber = false;
		char[] phone = phoneString.toCharArray();
		try {
			long phoneNumber = Long.parseLong(String.valueOf(phone));
		} catch (Exception e) {
			Toast.makeText(context,
					"Phone number must contain only digits",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (phone.length == 10) {
			isCorrectNumber = true;
		} else {
			Toast.makeText(context,
					"Phone number must contain 10 digits",
					Toast.LENGTH_SHORT).show();
		}
		return isCorrectNumber;
	}

	public boolean isValidEmail(String emailStr, Context c) {
		boolean isValidMail = false;
		Matcher matcher = VALID_EMAIL_ADDRESS.matcher(emailStr);
		if (emailStr.isEmpty()) {
			return true;
		}
		if (matcher.find()) {
			isValidMail = true;
		} else {
			Toast.makeText(c, "E-mail is not valid", Toast.LENGTH_SHORT).show();
		}
		return isValidMail;
	}

	public String getEMail() {
		return mEMail;
	}

	public void setEMail(String mEMail) {
		this.mEMail = mEMail;
	}

}