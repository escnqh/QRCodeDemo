package com.ntanougat.qrcodedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by 倪启航 on 2017/7/15.
 */

public class MakeUpShareQRCode extends AppCompatActivity{
    private ImageView QRCodeForShare;
    private String ChannelId=null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodeforshare);
        QRCodeForShare= (ImageView) findViewById(R.id.image_qrcode);
        Bundle bundle=this.getIntent().getExtras();
        ChannelId=bundle.getString("ChannelId");

        createQRCode();
    }

    private void createQRCode() {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap logoBitmap = BitmapFactory.decodeResource(MakeUpShareQRCode.this.getResources(), R.mipmap.logo);
                return QRCodeEncoder.syncEncodeQRCode(ChannelId, BGAQRCodeUtil.dp2px(MakeUpShareQRCode.this, 250), Color.parseColor("#ff0000"), logoBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    QRCodeForShare.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(MakeUpShareQRCode.this, "生成分享二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
