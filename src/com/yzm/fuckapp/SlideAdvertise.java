package com.yzm.fuckapp;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SlideAdvertise extends Fragment {
	private final static int FLUSH_SLIDE_VP = 1;
	private ViewPager mSlidePager = null;
	private LinearLayout mLinearLayout = null;
	private LayoutInflater mInflater = null;
	private Context mContext = null;
	private int mIndex = 0;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = getActivity();
	}

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.slide_advertise, null);
		mSlidePager = (ViewPager) view
				.findViewById(R.id.slide_advertise_slide_vp);
		mLinearLayout = (LinearLayout) view
				.findViewById(R.id.slide_advertise_ll_point);
		initWidget();
		setTimer();
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void initWidget() {
		mInflater = LayoutInflater.from(getActivity().getApplicationContext());
		VpAdaper slideAdaper = new VpAdaper(getSlideData());
		mSlidePager.setAdapter(slideAdaper);
		addIndex(mIndex);
	}

	/**
	 * 
	 * @return 广告栏数据
	 */
	private ArrayList<View> getSlideData() {
		ArrayList<View> viewList = new ArrayList<View>();
		View view = new View(mContext);
		view.setBackgroundColor(Color.BLACK);
		View view2 = new View(mContext);
		view2.setBackgroundColor(Color.BLUE);
		View view3 = new View(mContext);
		view3.setBackgroundColor(Color.RED);
		viewList.add(view);
		viewList.add(view2);
		viewList.add(view3);
		return viewList;
	}

	/**
	 * 添加页面提示
	 * 
	 * @param slideviewpager的索引
	 */
	
	private void addIndex(int i) {
		mLinearLayout.removeAllViews();
		LinearLayout.LayoutParams params = new LayoutParams(10, 10);
		params.leftMargin = 10;
		for (int j = 0; j < getSlideData().size(); j++) {
			View view;
			if (i == j) {
				view = mInflater.inflate(R.layout.position_dot_focused, null);
			} else {
				view = mInflater.inflate(R.layout.position_dot_normal, null);
			}
			mLinearLayout.addView(view, params);
		}
	}

	/**
	 * 设置slidvp自动滚动
	 */
	private void setTimer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(FLUSH_SLIDE_VP);
			}
		};
		timer.schedule(task, 0, 3000);
	}

	private void flushSlide() {
		if (mIndex == (getSlideData().size() - 1)) {
			mIndex = 0;
		} else {
			mIndex++;
		}
		addIndex(mIndex);
		mSlidePager.setCurrentItem(mIndex);
	}


	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FLUSH_SLIDE_VP:
				flushSlide();
				break;

			default:
				break;
			}
		};
	};

	private class VpAdaper extends PagerAdapter {

		private ArrayList<View> mViews = null;

		public VpAdaper(ArrayList<View> arrayList) {
			mViews = arrayList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView(mViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(mViews.get(position));
			return mViews.get(position);
		}
	}
}
