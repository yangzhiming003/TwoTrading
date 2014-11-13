package com.yzm.fuckapp;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;


public class NavigationBar extends Fragment implements OnTabChangeListener{

	private static final int TAB_COUNTS = 3;
	private Context mContext = null;
	private TabHost tabHost = null;
	private LayoutInflater mInflater = null;
	private View[] mTabViews = new View[TAB_COUNTS];
	private String[] mTag = { "frist_tab_item", "second_tab_item", "thrid_tab_item" };
	private int[] mId = { R.id.tab_item_one, R.id.tab_item_two,
			R.id.tab_item_three };
	private int[] mTabBg = { R.drawable.tab_item_frist_bg,
			R.drawable.tab_item_second_bg, R.drawable.tab_item_thrid_bg };
	private CallBacks mCallBacks = null;

	public interface CallBacks{
		public void tabSelected(int tabIndex);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof CallBacks)) {
			throw new IllegalStateException("tabhost所在的activity必须实现CallBacks");
		}
		mCallBacks = (CallBacks) activity;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.navigation_bar, null);
		tabHost = (TabHost) view.findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.setOnTabChangedListener(this);
		for (int i = 0; i < TAB_COUNTS; i++) {
			tabHost.addTab(getTabSpec(i));
		}
		tabHost.setCurrentTab(0);
		return view;
	}

	private TabSpec getTabSpec(int i) {
		mTabViews[i] = mInflater.inflate(R.layout.tab_item, null);
		mTabViews[i].findViewById(R.id.tab_item_tv_bg).setBackgroundResource(
				mTabBg[i]);
		TabSpec tabSpec = tabHost.newTabSpec(mTag[i]);
		tabSpec.setIndicator(mTabViews[i]).setContent(mId[i]);
		return tabSpec;
	}
	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		mCallBacks.tabSelected(tabHost.getCurrentTab());
	}
	private void setNotification(int i) {
		for (int j = 0; j < TAB_COUNTS; j++) {
			if (j == i) {
				mTabViews[i].findViewById(R.id.tab_item_tv_new_msg).setVisibility(View.VISIBLE);
			}else {
				mTabViews[i].findViewById(R.id.tab_item_tv_new_msg).setVisibility(View.GONE);
			}
		}
	}
}
