package com.ntanougat.qrcodedemo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ntanougat.qrcodedemo.Utils.JumpToScanUtil;
import com.ntanougat.qrcodedemo.Utils.JumpToShareUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SourceActivity extends AppCompatActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private Button button_share=null;
    private Button button_scan=null;
    private String channelid="1234567";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        button_scan= (Button) findViewById(R.id.button_scan);
        button_share= (Button) findViewById(R.id.button_share);
        button_share.setOnClickListener(this);
        button_scan.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_scan:
                JumpToScanUtil jumpToScanUtil=new JumpToScanUtil(this);
                jumpToScanUtil.startJump();
                break;
            case R.id.button_share:

                JumpToShareUtil jumpaction=new JumpToShareUtil(channelid,this);
                jumpaction.startJump();

                break;
            default:
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
