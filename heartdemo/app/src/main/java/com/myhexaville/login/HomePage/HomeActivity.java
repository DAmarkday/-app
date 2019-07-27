package com.myhexaville.login.HomePage;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;


import com.lzy.alpha.AlphaIndicator;
import com.lzy.alpha.AlphaView;
import com.myhexaville.login.Fragment.FirstFragmentPage;
import com.myhexaville.login.Fragment.SecondFragmentPage;
import com.myhexaville.login.Fragment.ThirdFragmentPage;
import com.myhexaville.login.R;

import java.util.ArrayList;
import java.util.List;

import cn.taurusxi.guidebackgroundcoloranimation.library.ColorAnimationView;

public class HomeActivity extends FragmentActivity {


    private ViewPager viewPager;
    private List<Fragment> list;
    private AlphaView alpha1;
    private AlphaView alpha2;
    private AlphaView alpha3;
    private TabFragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitView();





//把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new FirstFragmentPage());
        list.add(new SecondFragmentPage());
        list.add(new ThirdFragmentPage());
       // adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);

        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(),list));
        AlphaIndicator alphaIndicator =  findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);



        viewPager.setCurrentItem(1);
        ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
// 设置菜单栏的点击事件
        colorAnimationView.setmViewPager(viewPager, 3,Color.parseColor("#B0E2FF"),Color.parseColor("#3ebee4"),Color.parseColor("#4EEE94"));
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // Log.e("TAG","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
               //Log.e("TAG","onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.e("TAG","onPageScrollStateChanged");
            }
        });

    }


    private void InitView() {

        alpha1 = (AlphaView) findViewById(R.id.alpha1);
        alpha2 = (AlphaView) findViewById(R.id.alpha2);
//        alpha3 = (AlphaView) findViewById(R.id.alpha3);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

//        alpha1.setIconAlpha(R.mipmap.history_record);
//        alpha2.setIconAlpha(R.mipmap.microphone_press);
//        alpha3.setIconAlpha(R.mipmap.user_settings);
//        alpha1.setOnClickListener(this);
//        alpha2.setOnClickListener(this);
//        alpha3.setOnClickListener(this);
//        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());
       // historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record_press));

    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fg_item_one:
////                myViewPager.setCurrentItem(0);
////                historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record_press));
////                microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone));
////                userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings));
//                break;
//            case R.id.fg_item_two:
////                myViewPager.setCurrentItem(1);
////                historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record));
////                microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone_press));
////                userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings));
//                break;
//            case R.id.fg_item_three:
////                myViewPager.setCurrentItem(2);
////                historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record));
////                microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone));
////                userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings_press));
//                break;
//        }
//    }

//
//    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//        }

//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//        }
//
//        @Override
//        public void onPageSelected(int arg0) {
//            switch (arg0) {
//                case 0:
////                    historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record_press));
////                    microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone));
////                    userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings));
//                    break;
//                case 1:
//                  /*  historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record));
//                    microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone_press));
//                    userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings));*/
//                    break;
//                case 2:
////                    historyRecord.setImageDrawable(getResources().getDrawable(R.mipmap.history_record));
////                    microPhone.setImageDrawable(getResources().getDrawable(R.mipmap.microphone));
////                    userSettings.setImageDrawable(getResources().getDrawable(R.mipmap.user_settings_press));
//                    break;
//            }
//        }
//    }

}


