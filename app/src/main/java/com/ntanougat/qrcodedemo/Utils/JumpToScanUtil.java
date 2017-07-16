package com.ntanougat.qrcodedemo.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ntanougat.qrcodedemo.MakeUpShareQRCode;
import com.ntanougat.qrcodedemo.ScanActivity;

/**
 * Created by 倪启航 on 2017/7/15.
 */

public class JumpToScanUtil {
    private String ChannelId;
    Context act=null;

    public JumpToScanUtil(Context act){
        this.act=act;
    }

    public void startJump(){
        Intent intent=new Intent(act, ScanActivity.class);
        act.startActivity(intent);
    }
}
