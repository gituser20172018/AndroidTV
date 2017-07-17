package com.vogella.android.usinglibs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vogella.android.usinglibs.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxm on 7/16/2017.
 */

public class PinterestByUserActivity extends AppCompatActivity {
    @BindView(R.id.edtHashtag)
    TextView mTxtUsername;

    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTxtUsername.setText("Cuilongzhe");
    }

    @OnClick(R.id.btnViewImage)
    public void OnViewBtnClick(){

    }
}

