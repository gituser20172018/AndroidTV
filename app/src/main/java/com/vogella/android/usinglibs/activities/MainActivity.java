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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;
import com.vogella.android.usinglibs.R;
import com.vogella.android.usinglibs.adapters.ImageAdapter;
import com.vogella.android.usinglibs.pojo.InstagramProfileData;
import com.vogella.android.usinglibs.pojo.Item;
import com.vogella.android.usinglibs.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Response;


public class MainActivity extends AppCompatActivity {

    public String TAG = this.getClass().getName();
    int mImageCount = 0;
    ArrayList<String> mImageUrls = new ArrayList<>();

    @BindView(R.id.edtHashtag)
    TextView mTxtInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTxtInstagram.setText("lizixing118");
    }

    @OnClick(R.id.btnViewImage)
    public void onSubmit() {

        final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        CharSequence str;
        str =  mTxtInstagram.getText();

        RestClient.InstagramApiInterface service = RestClient.getClient();
        Call<InstagramProfileData> call = service.fetchProfileData(str.toString());//cuilongzhe
        call.enqueue(new retrofit.Callback<InstagramProfileData>() {
            @Override
            public void onResponse(Response<InstagramProfileData> response) {

                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    InstagramProfileData result = response.body();

                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
                    Log.d("MainActivity", "Items = " + result.getItems().size());

                    mImageCount = result.getItems().size();

                    Item temp_item = new Item();
                    String temp_strUrl;
                    if (mImageCount > 0) {
                        if (mImageUrls.size() != 0)mImageUrls.clear();
                        for (int i = 0; i < mImageCount; i++) {
                            temp_item = result.getItems().get(i);
                            temp_strUrl = temp_item.getImages().getThumbnail().getUrl();
                            mImageUrls.add(temp_strUrl);

                            Log.d("MainActivity", "img = " + temp_strUrl);
                        }
                        hud.dismiss();
                        showData();
                    }
                    else{
                        Log.d(TAG,"Perhaps there isn't any pictre");
                    }
                }
                else {
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
    }

    private void showData() {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter mGridviewImageAdapter = new ImageAdapter(this);
        mGridviewImageAdapter.setCount(mImageCount);
        mGridviewImageAdapter.setImageResource(mImageUrls);

        int iDisplayWidth = getResources().getDisplayMetrics().widthPixels ;
        int ImgWidth = iDisplayWidth / 3 ;
        int ImgHeight = (int) Math.round(ImgWidth * 1.2);
        mGridviewImageAdapter.setImageSize( ImgWidth, ImgHeight);
        gridview.setAdapter(mGridviewImageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
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
