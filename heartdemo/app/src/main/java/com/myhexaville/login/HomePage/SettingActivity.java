package com.myhexaville.login.HomePage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myhexaville.login.R;


public class SettingActivity extends AppCompatActivity {
  private Button btn1;
  private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Init();
    }
    private void Init(){
        btn1 = (Button) findViewById(R.id.sure);
        btn2 = (Button) findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                finish();
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                finish();
            }

        });
    }


}
