package com.myhexaville.login.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.myhexaville.login.R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import es.dmoral.toasty.Toasty;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.myhexaville.login.FlexibleFrameLayout.ORDER_LOGIN_STATE;
import static com.myhexaville.login.login.MainActivity.binding;
import static com.myhexaville.login.login.MainActivity.isLogin;

public class SignUpFragment extends Fragment implements OnSignUpListener{
    private static final String TAG = "SignUpFragment";
    private HashMap<String, String> stringHashMapx;
    private EditText e1;
    private RadioGroup e2_sex;
   // private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    //private EditText ra1;
    private View inflate;
    private String sexChoose = "1";

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         inflate = inflater.inflate(R.layout.fragment_signup, container, false);
         Init();
        return inflate;
    }


    @Override
    public void signUp() {
        String stringName=e1.getText().toString();
        String stringId=e3.getText().toString();
        String stringPassword=e4.getText().toString();
        String make_sure_password=e5.getText().toString();
        //Toast.makeText(getContext(), "Sign up", Toast.LENGTH_SHORT).show();
        Toasty.warning(getContext(), "正在注册，请稍后", Toasty.LENGTH_SHORT, true).show();
        if(judgeInput(stringName,stringId,stringPassword,make_sure_password)==true){
            handOn(stringName,stringId,stringPassword);
        }


    }
    private boolean judgeInput(String name,String id,String password,String again_password){
        if(name.equals("")==true||id.equals("")==true||password.equals("")==true||again_password.equals("")==true||name==null||id==null||password==null){
            Toasty.warning(getContext(), "请将信息填写完整", Toasty.LENGTH_SHORT, true).show();
            return false;
        }else if(name.indexOf(" ")!=-1||id.indexOf(" ")!=-1||password.indexOf(" ")!=-1){
            Toasty.warning(getContext(), "输入框中不能存在空格", Toasty.LENGTH_SHORT, true).show();
            return false;
        }else if(again_password.equals(password)==false){
            Toasty.warning(getContext(), "两次输入密码不一致", Toasty.LENGTH_SHORT, true).show();
            return false;
        }else if(name.equals("")==false&&id.equals("")==false&&password.equals("")==false&&again_password.equals(password)==true){
            return true;
        }
        Toasty.warning(getContext(), "请将信息填写正确", Toasty.LENGTH_SHORT, true).show();
        return false;
    }
    private void Init(){
        stringHashMapx = new HashMap<>();
        e1 = (EditText) inflate.findViewById(R.id.e1_name);
        e2_sex = (RadioGroup) inflate.findViewById(R.id.e2_sex);
        e3 = (EditText) inflate.findViewById(R.id.e3_id);
        e4 = (EditText) inflate.findViewById(R.id.e4_password);
        e5 = (EditText) inflate.findViewById(R.id.e5_make_sure_password);
        e2_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.male:
                        sexChoose = "1";
                        break;
                    case R.id.female:
                        sexChoose = "0";
                        break;
                }

            }
        });

    }

    private boolean requestPostx(HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "http://106.14.155.164:7777/userlogin/server/LoginDateServlet";
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos >0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode((URLEncoder.encode(paramsMap.get(key))), "UTF-8")));
                pos++;
            }

            String params = tempParams.toString();
            Log.e("main","params--post-->>"+params);
            // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            //配置请求Content-Type
//            urlConn.setRequestProperty("Content-Type", "application/json");//post请求不能设置这个
            // 开始连接
            urlConn.connect();

            // 发送请求参数
            PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
            dos.write(params);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            //String result = streamToString(urlConn.getInputStream());
            Log.e("main", "Post方式请求成功，result--->xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + (urlConn.getResponseCode()));
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String resultx = streamToString(urlConn.getInputStream());
                Log.e("main", "Post方式请求成功，result--->" + resultx);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  Toast.makeText(RegisterPage.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Toasty.success(getContext(), "注册成功", Toasty.LENGTH_SHORT, true).show();


                            }

                        });

                    }
                }).start();

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(7200);
//                        }catch (Exception e){
//
//                        }
//                        Intent x = new Intent(RegisterPage.this,LoginPage.class);
//                        startActivity(x);
//                    }
//                }).start();
            } else {

                Log.e("main", "Post方式请求失败");
                return false;
            }
            // 关闭连接
            urlConn.disconnect();



        } catch (Exception e) {
            Log.e("main", e.toString());
            return false;
        }
        return true;
    }
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("main", e.toString());
            return null;
        }
    }
    private void handOn(String name,String id,String password){
        byte[] z=null;

        stringHashMapx.put("user_name",name);
        stringHashMapx.put("user_sex",sexChoose);
        stringHashMapx.put("id", id);
        //stringHashMapx.put("user_age", e5.getText().toString());
        //stringHashMapx.put("user_height", e2.getText().toString());
       // stringHashMapx.put("user_weight", ra1.getText().toString());
        stringHashMapx.put("password", password);
        //stringHashMapx.put("password", et_data_upass.getText().toString());
        new Thread(postRun).start();
    }
    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if(requestPostx(stringHashMapx)==true){

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        Thread.sleep(2000);}
                        catch (Exception e){

                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                binding.loginFragment.setVisibility(VISIBLE);
                                binding.loginFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        binding.signUpFragment.setVisibility(INVISIBLE);
                                        binding.signUpFragment.setRotation(90);
                                        binding.wrapper.setDrawOrder(ORDER_LOGIN_STATE);
                                    }});
                                isLogin = !isLogin;
                                binding.button.startAnimation();


                            }

                        });

                    }
                }).start();

            }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toasty.error(getContext(), "网络问题，请稍后再试", Toasty.LENGTH_SHORT, true).show();


                                }

                            });

                        }
                    }).start();
            };
        }};
}


