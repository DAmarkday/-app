package com.myhexaville.login.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myhexaville.login.HomePage.SettingActivity;
import com.myhexaville.login.R;
import com.myhexaville.login.login.LoginFragment;
import com.myhexaville.login.login.MainActivity;


public class ThirdFragmentPage extends Fragment {
    private TextView user_word;
    private TextView user_name;
    private Button btn;
    private View mRoot = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.activity_third_fragment_page, container, false);
            Init();
        }
        return mRoot;
    }
public void Init(){
         user_word = mRoot.findViewById(R.id.user_word);
         user_name = mRoot.findViewById(R.id.user_name);
        Button btn = mRoot.findViewById(R.id.addx);
        user_name.setText(LoginFragment.userName);
        user_word.setText(LoginFragment.userId);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
            Intent x= new Intent(getContext(),SettingActivity.class);
            startActivity(x);
        }

    });

}

}
