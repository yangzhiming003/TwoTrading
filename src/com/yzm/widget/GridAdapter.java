package com.yzm.widget;

import java.util.ArrayList;

import com.yzm.fuckapp.R;
import com.yzm.model.GridItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private ArrayList<GridItem> mArrayList = null;
	private Context mContext = null;
	private LayoutInflater mInflater = null;

	public GridAdapter(Context context, ArrayList<GridItem> arrayList) {
		mArrayList = arrayList;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.gird_item, null);
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.grid_item_iv);
			holder.tv = (TextView) convertView.findViewById(R.id.gird_item_tv);
			holder.iv.setBackgroundDrawable(mArrayList.get(position).getDrawable());
			holder.tv.setText(mArrayList.get(position).getTv());
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	private class ViewHolder{
		ImageView iv;
		TextView tv;
	}
}
