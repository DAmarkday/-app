package com.myhexaville.login.Painter;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Electrocardiogram extends View {
    private static final String TAG = Electrocardiogram.class.getSimpleName();
    private Handler mHandler = new Handler() {
    };
    private boolean mIsDrawGird = true;
    private Paint paint;
    private Paint electrocarPaint;
    private Path electrocarPath;
    private int width;
    private int height;
    private int baseLine;
    int bigGirdNum = 6;
    int verticalBigGirdNum = 8;
    private int widthOfSmallGird;
    private List<Float> datas = new ArrayList();
    private List<Float> electrocardDatas = new ArrayList();
    private int maxLevel;
    private int index = 0;
    Runnable runnable;

    public Electrocardiogram(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public Electrocardiogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setStyle(Style.STROKE);
        this.electrocarPaint = new Paint();
        this.electrocarPaint.setColor(-256);
        this.electrocarPaint.setStyle(Style.STROKE);
        this.electrocarPaint.setStrokeWidth(5.0F);
        this.electrocarPath = new Path();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       // Log.e(TAG, "onMeasure");
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       // Log.e(TAG, "onSizeChanged");
        this.width = w;
        this.height = h;
        this.widthOfSmallGird = this.width / (this.verticalBigGirdNum * 5);
        this.baseLine = this.height / 2;
        this.maxLevel = this.height / 3;
        this.setData();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // Log.e(TAG, "onDraw");
        if (this.mIsDrawGird) {
            this.drawGird(canvas);
        }

        this.drawElectrocardiogram(canvas);
    }

    private void drawElectrocardiogram(Canvas canvas) {
        canvas.drawPath(this.electrocarPath, this.electrocarPaint);
        this.electrocarPath.moveTo(0.0F, (float)this.baseLine - (Float)this.datas.get(0));

        for(int i = 1; i < this.electrocardDatas.size(); ++i) {
            float y = (float)this.baseLine - (Float)this.electrocardDatas.get(i);
            this.electrocarPath.lineTo((float)(i * this.widthOfSmallGird), y);
        }

        canvas.drawPath(this.electrocarPath, this.electrocarPaint);
    }

    public void addData() {
        if (this.datas.size() > 0) {
            this.electrocardDatas.add((Float)this.datas.get(this.index));
            ++this.index;
            if (this.index >= this.datas.size() - 1) {
                this.index = 0;
                this.electrocardDatas.clear();
                this.electrocarPath.reset();
                this.datas.clear();
                this.generateElectrocar();
            }

            this.invalidate();
        }

    }

    public void startDraw() {
        this.runnable = new Runnable() {
            public void run() {
                Electrocardiogram.this.addData();
                Electrocardiogram.this.mHandler.postDelayed(Electrocardiogram.this.runnable, 100L);
            }
        };
        this.mHandler.post(this.runnable);
    }

    public void setData() {
        this.generateElectrocar();
      //  Log.e(TAG, "" + this.datas);
    }

    public void generateElectrocar() {
        int i;
        for(i = 0; i < 2; ++i) {
            this.datas.add(0.0F);
        }
//
        double random;
        float value;
        for(i = 0; i < 6; ++i) {
            if (i % 2 == 0) {
                random = Math.random();
            } else {
                random = -Math.random();
            }

            value = (float)((double)this.maxLevel * random);
            this.datas.add(value);
        }

        for(i = 0; i < 12; ++i) {
            this.datas.add(0.0F);
        }

        for(i = 0; i < 6; ++i) {
            if (i % 2 == 0) {
                random = Math.random();
            } else {
               random = -Math.random();
         }

          value = (float)((double)this.maxLevel * random);
       // value =SoundRecorder.handle();
            this.datas.add(value);//添加返回的音频的值
        }

        for(i = 0; i < 6; ++i) {
            this.datas.add(0.0F);
        }

    }

    private void drawGird(Canvas canvas) {
        this.paint.setColor(-16711936);

        int i;
        for(i = 0; i <= this.verticalBigGirdNum * 5; ++i) {
            if (i % 5 == 0) {
                this.paint.setStrokeWidth(3.0F);
            } else {
                this.paint.setStrokeWidth(1.0F);
            }

            canvas.drawLine((float)(i * this.widthOfSmallGird), 0.0F, (float)(i * this.widthOfSmallGird), (float)this.height, this.paint);
        }

        for(i = 0; i <= this.bigGirdNum * 5; ++i) {
            if (i % 5 == 0) {
                this.paint.setStrokeWidth(3.0F);
            } else {
                this.paint.setStrokeWidth(1.0F);
            }

            canvas.drawLine(0.0F, (float)(i * this.widthOfSmallGird), (float)this.width, (float)(i * this.widthOfSmallGird), this.paint);
        }

    }
}
