package com.myhexaville.login.LaunchActivity;

import android.content.Intent;
import android.os.Handler;
import android.provider.SyncStateContract;

import com.daimajia.androidanimations.library.Techniques;
import com.myhexaville.login.R;
import com.myhexaville.login.login.MainActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

//extends AwesomeSplash!
public class LaunchActivity extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!


    @Override
    public void initSplash(ConfigSplash configSplash) {

        /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.yellow); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.timge); //or any other drawable
        configSplash.setAnimLogoSplashDuration(3600); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.RollIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
       // configSplash.setPathSplash(SyncStateContract.Constants.DROID_LOGO"myfont.ttf");
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.black); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.black); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("智能家用听诊app");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        Integer time = 2000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.finish();
            }
        }, time);
        //transit to another activity here
        //or do whatever you want
    }
}
//public class LaunchActivity extends AppCompatActivity implements View.OnClickListener{
//  private Button btn_launch;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launch);
//        Integer time = 2000;
//        Init();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
//                LaunchActivity.this.finish();
//            }
//        }, time);
//    }
//    public void onClick(View v){
//        switch(v.getId()){
//            case R.id.btn_launch:
//                startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
//                LaunchActivity.this.finish();
//        }
//
//    }
//
//    private void Init(){
//        btn_launch = (Button) findViewById(R.id.btn_launch);
//        btn_launch.setOnClickListener(this);
//    }
//}
