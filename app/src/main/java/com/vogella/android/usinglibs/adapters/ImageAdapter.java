package com.vogella.android.usinglibs.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vogella.android.usinglibs.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxm on 7/14/2017.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int mImageCount = 0;
    private int mImageWidth = 100, mImageHeight = 100;
    private ArrayList<String> mImageUrlList;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public int getCount() {
        return mImageCount;
    }

    public void setCount(int value) {
        mImageCount = value;
    }

    public void setImageSize(int sizeX, int sizeY)
    {
        mImageWidth = sizeX;
        mImageHeight = sizeY;
    }
    public void setImageResource(ArrayList<String> mList)
    {
        mImageUrlList = mList;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams( mImageWidth, mImageHeight));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        //show image in imageview
        Picasso.with(imageView.getContext()).load(mImageUrlList.get(position)).into(imageView);

        return imageView;
    }
}
