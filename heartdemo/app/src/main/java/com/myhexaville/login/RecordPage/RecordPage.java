package com.myhexaville.login.RecordPage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.myhexaville.login.Painter.Electrocardiogram;
import com.myhexaville.login.R;
import com.myhexaville.login.RequestHttp.RequestHttp;
import com.myhexaville.login.Utils.SoundPlayer;
import com.myhexaville.login.Utils.SoundRecorder;

import java.io.File;

import es.dmoral.toasty.Toasty;


import static com.myhexaville.login.Utils.SoundRecorder.newFileName;

public class RecordPage extends AppCompatActivity implements View.OnClickListener {
    private File recordFile;
    public String HttpUrl = "http://106.14.155.164:7777/dopost/dopost";
   // public String HttpUrl = "http://192.168.253.1:7777/uploadFile";
//    public final static int RECORD_OUT_OF_TIME = 1;
    public final static int RECORD_PROGRESS = 2;
    public final static int RECORD_WAVE = 3;
    public final static int RECORD_STOP_WAVE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);
        recordFile = new File(newFileName());
        Init();
        Electrocardiogram electrocardiogram = (Electrocardiogram) this.findViewById(R.id.electrocardiogram);
        electrocardiogram.startDraw();


    }

    public Context getRecordContext() {
        Context context = RecordPage.this;
        return context;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.record:
                //  mediaRecorder.reset();
               // Toast.makeText(RecordPage.this, "开始录音!", Toast.LENGTH_SHORT).show();
                Toasty.success(RecordPage.this,  "开始录音!", Toasty.LENGTH_SHORT, true).show();
                SoundRecorder.startRecording(recordFile, this.getRecordContext());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }

                        SoundRecorder.stopRecording();
                        Looper.prepare();//子线程加载toast
                        Toasty.success(RecordPage.this,  "录音结束!", Toasty.LENGTH_SHORT, true).show();
                       // Toast.makeText(RecordPage.this, "录音结束!", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();

//                final Runnable runnable = new Runnable() {
//     @Override
//   public void run() {
//      //执行耗时操作
//           try {
//              // Log.e("bm", "runnable线程： " + Thread.currentThread().getId()+ " name:" + Thread.currentThread().getName());
//               Thread.sleep(5000);
//               SoundRecorder.stopRecording(LaunchActivity.mRecorder,RecordPage.this.getRecordContext());
////               Log.e("bm", "执行完耗时操作了~");
//               Toast.makeText(RecordPage.this, "mediaRecorder停止记录", Toast.LENGTH_LONG).show();
//
//           } catch (InterruptedException e) {
//e.printStackTrace();
//}
//}
//                };
//                new Thread() {
//  public void run() {
//   new Handler(Looper.getMainLooper()).post(runnable);//在子线程中直接去new 一个handler
//
////这种情况下，Runnable对象是运行在主线程中的，不可以进行联网操作，但是可以更新UI
//}
//                }.start();


//                TimerTask task = new TimerTask() {
//                    @Override
//                    public void run() {
//                        SoundRecorder.stopRecording(mediaRecorder,RecordPage.this.getRecordContext());
//                    }
//                };
//                Timer timer = new Timer();
//                timer.schedule(task, 5000);//3秒后执行TimeTask的run方法
                // mediaRecorder.stop();
                break;
            case R.id.play:
                //  mediaRecorder.reset();
                SoundPlayer.startPlaying(newFileName());
                break;
            case R.id.upload:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File fileX = new File(newFileName());
                        if (fileX.exists() && fileX.isFile()) {
                       // RequestHttp.uploadLogFile(getRecordContext());
                      // RequestHttp.uploadLogFile(getRecordContext(), HttpUrl, newFileName());
                        try{
                            Log.e("main", "eeeeeeeeeeeeeeeee");
                        RequestHttp.sendFile(getRecordContext(),HttpUrl,newFileName());
                    }catch (Exception e){
                            Looper.prepare();//子线程加载toast
                           //Toast.makeText(RecordPage.this,"上传失败！",Toast.LENGTH_SHORT).show();
                            Log.e("asdasdas","错误是   "+e);
                            Toasty.error(RecordPage.this, "上传失败！", Toasty.LENGTH_SHORT, true).show();
                               Log.e("main", "eeeeeeeeeeeeeeeee");
                            Looper.loop();

                        }
                        }else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toasty.warning(RecordPage.this,"音频文件不存在！", Toasty.LENGTH_SHORT, true).show();
                                          //  Toast.makeText(RecordPage.this,"音频文件不存在！",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).start();
                        }
                    }

                }).start();
                //上传之后删除掉音频文件
                break;
        }
    }

    private void Init() {
        Button btnRecord = (Button) findViewById(R.id.record);
        Button btnPlay = (Button) findViewById(R.id.play);
        Button btnUpload = (Button) findViewById(R.id.upload);
        btnRecord.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnUpload.setOnClickListener(this);

    }


}