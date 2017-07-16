package com.vogella.android.usinglibs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.vogella.android.usinglibs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxm on 7/16/2017.
 */

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);

        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSimple)
    public void OnButtonSimple(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
