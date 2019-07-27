package com.myhexaville.login.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.myhexaville.login.HomePage.HomeActivity;
import com.myhexaville.login.R;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class LoginFragment extends Fragment implements OnLoginListener{
    private static final String TAG = "LoginFragment";
    public static String userId;
    public static String userNumber;
    public static String userName;
    private EditText et_data_uname;
    private EditText et_data_upass;
    private HashMap<String, String> stringHashMap;
    View inflate;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         inflate = inflater.inflate(R.layout.fragment_login, container, false);
        et_data_uname = (EditText) inflate.findViewById(R.id.edit1);
        et_data_upass = (EditText) inflate.findViewById(R.id.edit2);
       // inflate.findViewById(R.id.forgot_password).setOnClickListener(v ->
               // Toast.makeText(getContext(), "Forgot password clicked", Toast.LENGTH_SHORT).show()
               //  );

        stringHashMap = new HashMap<>();
        return inflate;
    }

    @Override
    public void login() {
       // Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
        loginPOST();
    }
    public void loginGET() {
        stringHashMap.put("id", et_data_uname.getText().toString());
        stringHashMap.put("password", et_data_upass.getText().toString());
        new Thread(getRun).start();


    }

    public void loginPOST() {
        stringHashMap.put("id", et_data_uname.getText().toString());
        stringHashMap.put("password", et_data_upass.getText().toString());

        new Thread(postRun).start();

    }
    /**
     * get请求线程
     */
    Runnable getRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            requestGet(stringHashMap);
        }
    };
    /**
     * post请求线程
     */
    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            requestPost(stringHashMap);

        }
    };


    /**
     * get提交数据
     *
     * @param paramsMap
     */
    private void requestGet(HashMap<String, String> paramsMap) {
        try {
            String baseUrl =
                    "http://106.14.155.164:7777/userlogin/server/LoginDateServlet";
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }

            Log.e(TAG,"params--get-->>"+tempParams.toString());
            String requestUrl = baseUrl + tempParams.toString();
            Log.e("asdasasas","get-->>"+requestUrl.toString());
            // 新建一个URL对象
            URL url = new URL(requestUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功

            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);
                if(result.indexOf("登陆成功")!=-1){


                    Intent main = new Intent(getContext(),HomeActivity.class);
                    startActivity(main);
                    getActivity().finish();}
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {

                                @Override

                                public void run() {

                                    //Toast.makeText(LoginPage.this, "密码错误", Toast.LENGTH_SHORT).show();
                                    Toasty.error(getActivity().getApplicationContext(), "密码错误", Toasty.LENGTH_SHORT, true).show();

                                }

                            });
                        }
                    }).start();
                }
            } else {
                Log.e(TAG, "Get方式请求失败");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {

                            @Override

                            public void run() {

                                //Toast.makeText(LoginPage.this, "登陆请求失败", Toast.LENGTH_SHORT).show();
                                Toasty.warning(getActivity().getApplicationContext(), "登陆请求失败", Toasty.LENGTH_SHORT, true).show();

                            }

                        });
                    }
                }).start();
            }
            // 关闭连接
            urlConn.disconnect();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * post提交数据
     *
     * @param paramsMap
     */
    private void requestPost(HashMap<String, String> paramsMap) {
        try {
            String baseUrl =
                    "http://106.14.155.164:7777/userlogin/server/LogonDateServlet";
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos >0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            Log.e(TAG,"params--post-->>"+params);
            String requestUrl = baseUrl + tempParams.toString();
            Log.e("asdasasas","get-->>"+requestUrl.toString());
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
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e("main", "Post方式请求成功，result--->" + result);

                if(result.indexOf("登陆成功")!=-1){
                    int left=result.indexOf("\"user_name\":\"");
                    int right=result.indexOf("\"}}");
                    userName="用户名: "+result.substring(left+13,right);
                    Log.e("main","454545454545"+userName);

                    int left1=result.indexOf("\"user_number\":");
                    int right1=result.indexOf(",\"id\"");
                    userNumber=result.substring(left1+14,right1);
                    Log.e("main","454545454545"+userNumber);

                    int left2=result.indexOf("\"id\":");
                    int right2=result.indexOf(",\"password\"");
                    userId="个人账号: "+result.substring(left2+5,right2);
                    Log.e("main","454545454545"+userId);

                    Intent main = new Intent(getContext(),HomeActivity.class);

                    startActivity(main);
                    getActivity().finish();}
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {

                                @Override

                                public void run() {

                                    //Toast.makeText(LoginPage.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                                    Toasty.error(getActivity().getApplicationContext(), "账号或密码错误", Toasty.LENGTH_SHORT, true).show();

                                }

                            });
                        }
                    }).start();
                }
            } else {
                Log.e(TAG, "Get方式请求失败");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {

                            @Override

                            public void run() {

                                //Toast.makeText(LoginPage.this, "登陆请求失败", Toast.LENGTH_SHORT).show();
                                Toasty.warning(getActivity().getApplicationContext(), "登陆请求失败", Toasty.LENGTH_SHORT, true).show();

                            }

                        });

                    }
                }).start();
            }

            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
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
            Log.e(TAG, e.toString());
            return null;
        }
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     */
    private void downloadFile(String fileUrl) {
        try {
            // 新建一个URL对象
            URL url = new URL(fileUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                String filePath = "";//下载文件保存在本地的地址
                File descFile = new File(filePath);
                FileOutputStream fos = new FileOutputStream(descFile);
                ;
                byte[] buffer = new byte[1024];
                int len;
                InputStream inputStream = urlConn.getInputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    // 写到本地
                    fos.write(buffer, 0, len);
                }
            } else {
                Log.e(TAG, "文件下载失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 文件上传
     *
     * @param filePath
     * @param paramsMap
     */
    private void upLoadFile(String filePath, HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "https://xxx.com/uploadFile";
            File file = new File(filePath);
            //新建url对象
            URL url = new URL(baseUrl);
            //通过HttpURLConnection对象,向网络地址发送请求
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //设置该连接允许读取
            urlConn.setDoOutput(true);
            //设置该连接允许写入
            urlConn.setDoInput(true);
            //设置不能适用缓存
            urlConn.setUseCaches(false);
            //设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);   //设置连接超时时间
            //设置读取超时时间
            urlConn.setReadTimeout(5 * 1000);   //读取超时
            //设置连接方法post
            urlConn.setRequestMethod("POST");
            //设置维持长连接
            urlConn.setRequestProperty("connection", "Keep-Alive");
            //设置文件字符集
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            //设置文件类型
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + "*****");
            String name = file.getName();
            DataOutputStream requestStream = new DataOutputStream(urlConn.getOutputStream());
            requestStream.writeBytes("--" + "*****" + "\r\n");
            //发送文件参数信息
            StringBuilder tempParams = new StringBuilder();
            tempParams.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"; ");
            int pos = 0;
            int size = paramsMap.size();
            for (String key : paramsMap.keySet()) {
                tempParams.append(String.format("%s=\"%s\"", key, paramsMap.get(key), "utf-8"));
                if (pos < size - 1) {
                    tempParams.append("; ");
                }
                pos++;
            }
            tempParams.append("\r\n");
            tempParams.append("Content-Type: application/octet-stream\r\n");
            tempParams.append("\r\n");
            String params = tempParams.toString();
            requestStream.writeBytes(params);
            //发送文件数据
            FileInputStream fileInput = new FileInputStream(file);
            int bytesRead;
            byte[] buffer = new byte[1024];
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            while ((bytesRead = in.read(buffer)) != -1) {
                requestStream.write(buffer, 0, bytesRead);
            }
            requestStream.writeBytes("\r\n");
            requestStream.flush();
            requestStream.writeBytes("--" + "*****" + "--" + "\r\n");
            requestStream.flush();
            fileInput.close();
            int statusCode = urlConn.getResponseCode();
            if (statusCode == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "上传成功，result--->" + result);
            } else {
                Log.e(TAG, "上传失败");
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }


}
