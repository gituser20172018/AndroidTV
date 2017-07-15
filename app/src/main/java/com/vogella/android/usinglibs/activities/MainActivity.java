package com.vogella.android.usinglibs.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vogella.android.usinglibs.R;
import com.vogella.android.usinglibs.pojo.InstagramProfileData;
import com.vogella.android.usinglibs.pojo.Item;
import com.vogella.android.usinglibs.rest.RestClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Response;


public class MainActivity extends AppCompatActivity {

    public String TAG = this.getClass().getName();
    String imageUrl;
    int mImageCount = 0;

    //ArrayList
    String[] urls = new String[256];

    @BindView(R.id.edtHashtag)
    TextView mTxtInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return mImageCount;
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

    @OnClick(R.id.btnViewImage)
    public void onSubmit() {
        CharSequence str;
        str =  mTxtInstagram.getText();

        RestClient.InstagramApiInterface service = RestClient.getClient();
        Call<InstagramProfileData> call = service.fetchProfileData("lizixing118");//cuilongzhe
        call.enqueue(new retrofit.Callback<InstagramProfileData>() {
            @Override
            public void onResponse(Response<InstagramProfileData> response) {
//                dialog.dismiss();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    InstagramProfileData result = response.body();

                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
                    Log.d("MainActivity", "Items = " + result.getItems().size());

                    mImageCount = result.getItems().size();
                    Item temp_item = new Item();

                    if(mImageCount > 0){
                        for (int i = 0; i < mImageCount; i++)
                        {
                            temp_item = result.getItems().get(i);
                            imageUrl = temp_item.getImages().getThumbnail().getUrl();
                            urls[i] = imageUrl;
                            Log.d("MainActivity", "img = " + urls[i]);
                        }


                    }
                    else{
                        Log.d(TAG,"Perhaps there isn't any pictre");
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Throwable t) {
 //               dialog.dismiss();
                Log.d(TAG, "Failed to fetch media data from instagram profile");
                t.printStackTrace();
            }
        });

 //       Picasso.with(this).load(imageUrl).into(mImv);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, " " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
