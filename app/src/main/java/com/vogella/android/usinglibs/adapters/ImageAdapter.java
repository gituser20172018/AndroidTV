package com.vogella.android.usinglibs.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by zxm on 7/14/2017.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public int getCount() {
        return n_image;
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

        int iDisplayWidth = getResources().getDisplayMetrics().widthPixels ;
        int ImgWidth = iDisplayWidth / 3 ;
        int ImgHeight = (int) Math.round(ImgWidth * 1.2);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams( ImgWidth, ImgHeight));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        //show image in imageview
        Picasso.with(imageView.getContext()).load(urls[position]).into(imageView);

        return imageView;
    }
}
