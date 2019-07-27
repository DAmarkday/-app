package com.myhexaville.login.Utils;


public class SoundUtild {
//    public SoundUtild(){
//        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/测试.mp3");
//        FileName = file.getAbsolutePath();
//        mStopPlay.setText(FileName);
//    }
//
//    private void start() {
//        recordingTime = 0;
//        handler.post(runnable);
//        mRecorder = new MediaRecorder();
//// 设置音频录入源
//        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
////设置记录的媒体文件的输出转换格式。
//        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//// 设置音频记录的编码格式。
//        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
////设置音频记录的音频源。
//        mRecorder.setOutputFile(FileName);
//        try {
//            mRecorder.prepare();
//        } catch (IOException e) {
//// Log.e(LOG_TAG, "prepare() failed");
//        }
//        mRecorder.start();
//        isRecording = true;
//        mRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
//            @Override
//            public void onError(MediaRecorder mr, int what, int extra) {
//// 发生错误，停止录制
//                mRecorder.stop();
//                mRecorder.release();
//                mRecorder = null;
//                isRecording = false;
//                showToast("录制出错");
//            }
//        });
//    }
//    //停止录制音频
//    private void stop() {
//        if(mRecorder!=null){
//            mRecorder.stop();
//            mRecorder.release();
//            mRecorder = null;
//            isRecording = false;
//        }
//
//
//    }
//    //stop播放音频
//    private void stopPlay() {
//        isPlaying = false;
//        if (mPlayer != null) {
//            mPlayer.release();
//            mPlayer = null;
//        }
//    }
//    //播放音频
//    private void starPplay() {
//        isPlaying = true;
//        playTime = 0;
//        handler.post(runnable);
//        mPlayer = new MediaPlayer();
//// mStopPlay.setText(mPlayer.getCurrentPosition() + "");
//        try {
//            mPlayer.setDataSource(FileName);
//            mPlayer.prepare();
//            mPlayer.start();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "播放失败");
//        }
//        allPlayTime = mPlayer.getDuration() / 1000;
//    }
//    protected void onDestroy() {
//        if (isRecording) {
//            mRecorder.stop();
//            mRecorder.release();
//            mRecorder = null;
//        }
//        if (mPlayer != null) {
//            mPlayer.release();
//            mPlayer = null;
//        }
//        super.onDestroy();
//    }
}
