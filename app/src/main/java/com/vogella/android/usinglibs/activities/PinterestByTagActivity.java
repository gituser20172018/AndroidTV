package com.vogella.android.usinglibs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.pinterest.android.pdk.PDKCallback;
import com.pinterest.android.pdk.PDKClient;
import com.pinterest.android.pdk.PDKException;
import com.pinterest.android.pdk.PDKResponse;
import com.vogella.android.usinglibs.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zxm on 7/16/2017.
 */

public class PinterestByTagActivity extends AppCompatActivity {
    private PDKClient pdkClient;
    @BindView(R.id.edtHashtag)
    TextView mTxtUsername;
    private String mStrAppID = "4912183659106156114";
    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pdkClient = PDKClient.configureInstance(this, mStrAppID);
        pdkClient.getInstance().onConnect(this);
        pdkClient.setDebugMode(true);
        mTxtUsername.setText("Cuilongzhe");
    }

    @OnClick(R.id.btnViewImage)
    public void OnViewBtnClick(){
        List scopes = new ArrayList<String>();
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_READ_PUBLIC);
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_WRITE_PUBLIC);
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_READ_RELATIONSHIPS);
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_WRITE_RELATIONSHIPS);

        pdkClient.login(this, scopes, new PDKCallback() {
            @Override
            public void onSuccess(PDKResponse response) {
                Log.d(getClass().getName(), response.getData().toString());
   //             onLoginSuccess();
            }

            @Override
            public void onFailure(PDKException exception) {
                Log.e(getClass().getName(), exception.getDetailMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pdkClient.getInstance().onOauthResponse(requestCode, resultCode, data);


    }
}


