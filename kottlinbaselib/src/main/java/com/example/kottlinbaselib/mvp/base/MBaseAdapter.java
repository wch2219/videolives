package com.example.kottlinbaselib.mvp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mList;

    public MBaseAdapter(Context context,List<T> list) {
       this.mContext = context;
       this.mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(null == mList)
        return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if(null == mList)
        return null;
        return mList.get(position);

    }

    @Override
    public long getItemId(int position) {
        if(null == mList)
        return 0;
        return position;
    }

    public void setmList(List<T> list) {
        if (null != list) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    public List<T> getList() {
        return mList;
    }

    public void add(int position, T item) {
        if (mList == null) {
            mList = new ArrayList<T>();
            mList.add(item);
            notifyDataSetChanged();
            return;
        }
        if (position < 0 || position > mList.size())
            return;
        mList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (null == mList)
            mList = list;
        else
            mList.addAll(list);

        notifyDataSetChanged();
    }

    public void addAll(int postion, List<T> list) {
        if (null == mList)
            mList = list;
        else
            mList.addAll(postion, list);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        if (mList != null && item != null) {
            mList.remove(item);
            notifyDataSetChanged();
        }
    }

    public void remove(int postion) {
        if (mList == null)
            return;
        if (postion >= 0 && postion < mList.size()) {
            mList.remove(postion);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

}
