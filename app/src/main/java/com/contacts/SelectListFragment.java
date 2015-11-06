package com.contacts;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

public class SelectListFragment extends ListFragment {
	private ArrayList<Contact> mContacts;
	private static ContactAdapter sAdapter;
	private boolean isNewActivity = false;
	private final static String TAG = "SelectListFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isNewActivity = true;
		setHasOptionsMenu(true);
		mContacts = ContactLab.get(getActivity()).getContacts();
		checkSavedContacts();
		sAdapter = new ContactAdapter(getActivity(), mContacts);
		setListAdapter(sAdapter);
		setRetainInstance(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = super.onCreateView(inflater, container, savedInstanceState);
		ListView contactsListView = (ListView) v
				.findViewById(android.R.id.list);
		contactsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		contactsListView
				.setMultiChoiceModeListener(new MultiChoiceModeListener() {

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						return false;
					}

					@Override
					public void onDestroyActionMode(ActionMode mode) {
					}

					@Override
					public boolean onCreateActionMode(ActionMode mode, Menu menu) {
						MenuInflater inflater = mode.getMenuInflater();
						inflater.inflate(R.menu.contact_list_item_context, menu);
						return true;
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						switch (item.getItemId()) {
						case R.id.menu_item_delete_contact:
							ContactAdapter adapter = (ContactAdapter) getListAdapter();
							ContactLab contactLab = ContactLab
									.get(getActivity());
							for (int i = adapter.getCount() - 1; i >= 0; i--) {
								if (getListView().isItemChecked(i)) {
									contactLab.deleteContact(adapter.getItem(i));
								}
							}
							mode.finish();
							adapter.notifyDataSetChanged();
							checkSavedContacts();
							return true;
						default:
							return false;
						}
					}

					@Override
					public void onItemCheckedStateChanged(ActionMode mode,
							int position, long id, boolean checked) {
						// TODO Auto-generated method stub
					}
				});
		return v;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = info.position;
		ContactAdapter adapter = (ContactAdapter) getListAdapter();
		Contact contact = adapter.getItem(position);
		switch (item.getItemId()) {
		case R.id.menu_item_delete_contact:
			ContactLab.get(getActivity()).deleteContact(contact);
			adapter.notifyDataSetChanged();
			checkSavedContacts();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Contact contact = ((ContactAdapter) getListAdapter()).getItem(position);

		Intent intent = new Intent(getActivity(), ContactPagerActivity.class);
		intent.putExtra(ContactSelectedFragment.EXTRA_CONTACT_ID,
				contact.getId());
		startActivity(intent);
		if (isNewActivity) {
			Toast.makeText(getActivity(),
					"Swipe right or left to display another saved contacts",
					Toast.LENGTH_LONG).show();
			isNewActivity = false;
		}
	}

	private void checkSavedContacts() {
		if (mContacts.size() == 0) {
			Toast.makeText(getActivity(), R.string.no_contacts,
					Toast.LENGTH_LONG).show();
		}
	}

	public static void notifyAdapter() {
		sAdapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((ContactAdapter) getListAdapter()).notifyDataSetChanged();
	}
}
