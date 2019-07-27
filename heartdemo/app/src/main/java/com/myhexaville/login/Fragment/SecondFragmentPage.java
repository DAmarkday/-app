package com.myhexaville.login.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myhexaville.login.R;
import com.myhexaville.login.RecordPage.RecordPage;

public class SecondFragmentPage extends Fragment {
private TextView receive_data;
private TextView time;
private TextView advice;
private View mRoot = null;
private  Button btnChange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.activity_second_fragment_page, container, false);
            Init();
        }
        return mRoot;
    }
    public void Init() {
        btnChange = (Button) mRoot.findViewById(R.id.change);//跳转录音界面
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
             Intent x= new Intent(getContext(),RecordPage.class);
              startActivityForResult(x, 0);
            }

    });
}

    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        receive_data=(TextView) mRoot.findViewById(R.id.receive_data_normal);
        advice = (TextView) mRoot.findViewById(R.id.advice);
        time=mRoot.findViewById(R.id.time);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String p=data.getExtras().getString("身体状况");
            if(p.indexOf("202")!=-1||p.indexOf("203")!=-1){
            receive_data.setText("正常");
            advice.setText("健康状况良好,请继续保持.");
            }else if(p.indexOf("201")!=-1){
                receive_data.setText("异常");
                advice.setText("您处于亚健康状态,请注意饮食作息安排.");
            }else {
                receive_data.setText("正常");
                advice.setText("健康状况良好,请继续保持.");
            }
          //  Log.e("main","1212121121231212312121231231231"+" "+p+" "+"2121212");
            String z=data.getExtras().getString("上传时间");
            //Log.e("main","1212121121231212312121231231231"+" "+z+" "+"2121212");
            time.setText(z);
        }
}
}
