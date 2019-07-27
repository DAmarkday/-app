package com.myhexaville.login.RequestHttp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import com.myhexaville.login.HomePage.HomeActivity;
import com.myhexaville.login.login.LoginFragment;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

import static com.myhexaville.login.Utils.SoundRecorder.newFileName;

public class RequestHttp {
    private static String strTime;
    private static String showTime;
    private static String resultx;
    /**
     * 上传文件到服务器
     * @param context
     //* @param uploadUrl     上传服务器地址
    // * @param oldFilePath       本地文件路径
     */
//    public static void uploadLogFile(Context context, String uploadUrl, String oldFilePath){
//        final String path=uploadUrl;
//
//        try {
//            URL url = new URL(path);
//            // Log.e(TAG,"wrong");
//
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//
//            // 允许Input、Output，不使用Cache
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.setUseCaches(false);
//
//            con.setConnectTimeout(50000);
//            con.setReadTimeout(50000);
//            // 设置传送的method=POST
//            con.setRequestMethod("POST");
//            //在一次TCP连接中可以持续发送多份数据而不会断开连接
//            con.setRequestProperty("Connection", "Keep-Alive");
//            //设置编码
//            con.setRequestProperty("Charset", "UTF-8");
//            //text/plain能上传纯文本文件的编码格式
//            con.setRequestProperty("Content-Type", "multipart/form-data");
//
//            // 设置DataOutputStream
//            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
//            System.out.println("11111111111111111111111");
//            // 取得文件的FileInputStream
//            FileInputStream fStream = new FileInputStream(oldFilePath);
//            // 设置每次写入1024bytes
//            int bufferSize = 1024;
//            byte[] buffer = new byte[bufferSize];
//
//            int length = -1;
//            // 从文件读取数据至缓冲区
//            while ((length = fStream.read(buffer)) != -1) {
//                // 将资料写入DataOutputStream中
//                ds.write(buffer, 0, length);
//            }
//            ds.flush();
//            fStream.close();
//            ds.close();
//            if(con.getResponseCode() == 200){//返回值为200，则代表上传成功
//                Looper.prepare();//子线程加载toast
//                Toast.makeText(context,"上传成功!",Toast.LENGTH_SHORT).show();
//                Looper.loop();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("22222222222222");
//            Looper.prepare();//子线程加载toast
//            System.out.println("3333333333333333333");
//            Toast.makeText(context,"上传失败!",Toast.LENGTH_SHORT).show();
//            Log.e(TAG,"1231");
//            Looper.loop();
//        }
//    }
    public static void sendFile(final Context context, String urlPath, String filePath) throws Exception {
        Log.e("66666", "666666");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd hh-mm-ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        strTime=simpleDateFormat2.format(date);
        showTime=simpleDateFormat.format(date);

        String newName = LoginFragment.userNumber+" "+strTime+".3gp";
     //   Log.e("main", "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+strTime);
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
     //   Log.e("main", "bbbbbbbbbbbbbbbbbbbbbbbb");
        URL url = new URL(urlPath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
      //  Log.e("main", "aaaaaaaaaaaaaaaaaaa");
        /* 允许Input、Output，不使用Cache */
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        /* 设置传送的method=POST */
      //  Log.e("main", "ddddddddddddddddddddddd");
        con.setRequestMethod("POST");
        /* setRequestProperty */

        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        con.setRequestProperty("Content-Type", "multipart/form-data;boundary="
                + boundary);
        /* 设置DataOutputStream */
        DataOutputStream ds = new DataOutputStream(con.getOutputStream());
        ds.writeBytes(twoHyphens + boundary + end);
        ds.writeBytes("Content-Disposition: form-data; "
                + "name=\"file1\";filename=\"" + newName + "\"" + end);
        ds.writeBytes(end);
     //   Log.e("main", "eeeeeeeeeeeeeeeee");
        /* 取得文件的FileInputStream */
        FileInputStream fStream = new FileInputStream(filePath);
        /* 设置每次写入1024bytes */
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int length = -1;
        /* 从文件读取数据至缓冲区 */
      //  Log.e("main", "rrrrrrrrrrrrrrrrrr");
        while ((length = fStream.read(buffer)) != -1) {
            /* 将资料写入DataOutputStream中 */
            ds.write(buffer, 0, length);
        }
       // Log.e("main", "ttttttttttttttttttt");
        ds.writeBytes(end);
        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
     //   Log.e("main", "zzzzzzzzzzzzzzzz");

        /* close streams */
        fStream.close();
        ds.flush();
       // Log.e("main", "qqqqqqqqqqqqqqqqqqqqqqq+"+con.getResponseCode());
        /* 取得Response内容 */
        InputStream is;
        try{
            is = con.getInputStream();
             resultx = streamToString(is);
            Log.e("main", "ooooooooooooooooooooooooooooooooooooooooooo"+resultx);
             }
        catch (Exception e) {
       // Log.e("mainxxxxxxxxxxxxxxx",e.toString());
            Log.e("request ","错误是   "+e);
        }


       // int ch;
        // b = new StringBuffer();
//        while ((ch = is.read()) != -1) {
//            b.append((char) ch);
//        }


       // Log.e("main", "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        if(con.getResponseCode() == 200){
            Looper.prepare();//子线程加载toast
           // Toast.makeText(context,"上传成功！",Toast.LENGTH_SHORT).show();
            Toasty.success(context,"上传成功！", Toasty.LENGTH_SHORT, true).show();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
////获取当前时间
//            Date date = new Date(System.currentTimeMillis());
          //  time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
//            Log.e(TAG,"1231");

          //  Log.e("main", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3600);
                     //  Log.e("main", "11111111111111111111111111");
                        String str = resultx;
                        Intent x=((Activity)context).getIntent();
                    //    Log.e("main", "222222222222222222222222222"+x);
                        x.putExtra("身体状况",str);
                        x.putExtra("上传时间",showTime);
                        File file = new File(newFileName());
                        if (file.exists() && file.isFile()) {
                            if (file.delete()) {
                                Log.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + newFileName() + "成功！");
                            }
                        }
                        ((Activity)context).setResult(HomeActivity.RESULT_OK,x);
                    //    Log.e("main", "3333333333333333333");
                        ((Activity)context).finish();
                    }catch (Exception e){

                       //Log.e("main",e.toString());
                    }

                }
            }).start();
            Looper.loop();//写在Looper.loop()之后的代码不会被执行，这个函数内部应该是一个循环，当调用mHandler.getLooper().quit()后。loop才会中止，其后的代码才干得以执行。
        }else{
            //请求返回码为200，则代表上传成功

            Toasty.success(context,"返回码错误！", Toasty.LENGTH_SHORT, true).show();
        }
        /* 关闭DataOutputStream */
        ds.close();

    }
    public static String streamToString(InputStream is) {
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
}
