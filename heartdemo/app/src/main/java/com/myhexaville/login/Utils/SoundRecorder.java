package com.myhexaville.login.Utils;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class SoundRecorder{
    static MediaRecorder mRecorder;
    private boolean isRun = true;

        public static void startRecording(File f,Context x){
//            Toast.makeText(x,"开始录音!",Toast.LENGTH_SHORT).show();
            if(judgeFileExits()==true){
                        f.delete();//删除已存在的音频
            }
            if(mRecorder!=null){
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }

            //耗时操作，硬件住准备，消耗性能

             mRecorder = new MediaRecorder();
         mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(newFileName());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        String LOG_TG="SoundRecorder";
         try{
        mRecorder.prepare();
             mRecorder.start();
        } catch(IOException e) {
         Log.e(LOG_TG,"prepare() failed");
        }



        }

        public static void stopRecording(){
//        if (recorder != null) {
//            recorder.stop();
//            recorder.release();
//            recorder = null;
//        }
//    }

                if (mRecorder != null) {
                    try {
                        mRecorder.stop();
                    } catch (IllegalStateException e) {
                        // TODO 如果当前java状态和jni里面的状态不一致，
                        //e.printStackTrace();
                        mRecorder = null;
                        mRecorder = new MediaRecorder();
                    }
                    mRecorder.release();
                    mRecorder = null;
                }


//            Toast.makeText(x, "mediaRecorder停止记录", Toast.LENGTH_LONG).show();

        }

      public static String newFileName(){
      String mFileName=Environment.getExternalStorageDirectory()
        .getAbsolutePath();

        //String s=new SimpleDateFormat("yyyy-MM-dd hhmmss").format(new Date());
        return mFileName+= "/SoundRecorder"+".3gp";
        }

        public static boolean judgeFileExits(){//判断文件是否存在,true表示存在，false表示不存在
            try
            {
              File f=new File(newFileName());
                if(!f.exists())
                {
                    return false;
                }

            }
            catch (Exception e)
            {
                return false;
            }

            return true;
        }

//    public static float handle() {
////        Timer timer = new Timer();
////        timer.schedule(new TimerTask() {
////
////            @Override
////            public void run() {
////                // TODO Auto-generated method stub
//           return   (float) this.getVolumn();//返回f音频分析的值
////            } }, 500, 1000);//0.5秒之后，每隔1秒做一次run()操作
//
    }
//    public void getVolumn(){
//        new Thread(new Runnable() {
//            int i = 0;
//
//            public void run() {
//                while(isRun){
//                    i = 8 * mRecorder.getMaxAmplitude() / 32768; //������������8����
//                    waveMsg = new Message();
//                    waveMsg.what = RecordPage.RECORD_WAVE;
//                    waveMsg.arg1 = (int) (i);
//                    handler.sendMessage(waveMsg);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//                int BASE = 600;
//                if(mRecorder !=null)
//
//                {
//                    double ratio = (double) mRecorder.getMaxAmplitude() / 32768;//BASE;
//                    int db = 0;// 分贝
//                    if (ratio > 1)
//                        db = (int) (20 * Math.log10(ratio)) - 50;
//                    if (db < 0)
//                        db = 0;
//                    //x.add(-db);//保存音频分贝大小，根据X里的值绘制一段完整的波浪线
////        if (x.size() > sfv.getWidth() / divider) {
////            x.remove(0);//如果长达超出了屏幕宽度则删除第一个数据
//////        }
//
//                }
//            }).start();




        //}
//}
//}


