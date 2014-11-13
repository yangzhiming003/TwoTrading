package com.yzm.fuckapp;

import java.util.ArrayList;

import com.yzm.model.GridItem;
import com.yzm.widget.GridAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity implements
		NavigationBar.CallBacks {

	private final static int FLUSH_SLIDE_VP = 1;
	private static final String TAG = "MainActivity";
	private RelativeLayout mLinearLayout = null;
	private ViewPager mMainPager = null;
	private LayoutInflater mInflater = null;
	private FragmentManager mManager = null;
	private Context mContext = null;
	private GridView mGridView = null;
	private int mIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
		findView();
		initWidget();
		setListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FLUSH_SLIDE_VP:
				// flushSlide();
				break;

			default:
				break;
			}
		};
	};

	private void initData() {
		// TODO Auto-generated method stub
		mManager = getFragmentManager();
		mContext = getApplicationContext();
		mInflater = LayoutInflater.from(this);
	}

	private void findView() {
		mLinearLayout = (RelativeLayout) findViewById(R.id.main_ll_advertise);
		mGridView = (GridView) findViewById(R.id.main_grid_item_classified);
	}

	private void initWidget() {
		Fragment fragment = new SlideAdvertise();
		FragmentTransaction transaction = mManager.beginTransaction();
		transaction.replace(R.id.main_ll_advertise, fragment);
		transaction.commit();
		new InitGridItem().execute();
	}

	private void setListener(){
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println(position);
			}
		});
	}
	
	@Override
	public void tabSelected(int tabIndex) {
		// TODO Auto-generated method stub
		Log.v(TAG, tabIndex + "");

	}

	private class InitGridItem extends
			AsyncTask<Void, Void, ArrayList<GridItem>> {

		@Override
		protected ArrayList<GridItem> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			ArrayList<GridItem> arrayList = new ArrayList<GridItem>();
			GridItem item ;
			Resources r = getResources();
			String[] tvStr = r.getStringArray(R.array.main_grid_item_tv);
			TypedArray ivArray = r.obtainTypedArray(R.array.main_grid_item_iv);
			for (int i = 0; i < tvStr.length; i++) {
				item = new GridItem();
				item.setDrawable(ivArray.getDrawable(i));
				item.setTv(tvStr[i]);
				arrayList.add(item);
			}
			ivArray.recycle();
			return arrayList;
		}
		@Override
		protected void onPostExecute(ArrayList<GridItem> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			GridAdapter adapter  =new GridAdapter(mContext, result);
			mGridView.setAdapter(adapter);
		}
	}
}
