package com.ua.yuriihrechka.androiddrinkshop;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_continue = (Button)findViewById(R.id.btn_continue);

        //printKeyHash();
    }

    private void printKeyHash() {

        try {

            if(Build.VERSION.SDK_INT >= 28) {
                @SuppressLint("WrongConstant") final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNING_CERTIFICATES);
                final Signature[] signatures = packageInfo.signingInfo.getApkContentsSigners();
                final MessageDigest md = MessageDigest.getInstance("SHA");
                for (Signature signature : signatures) {
                    md.update(signature.toByteArray());
                    final String signatureBase64 = new String(Base64.encode(md.digest(), Base64.DEFAULT));
                    Log.d("KEYHASH", signatureBase64);
                }


//            PackageInfo info = getPackageManager().getPackageInfo(
//                        "com.ua.yuriihrechka.androiddrinkshop"
//                        , PackageManager.GET_SIGNATURES
//                );
//
//                for (Signature signature : info.activities) {
//
//                    MessageDigest md = MessageDigest.getInstance("SHA");
//                    md.update(signature.toByteArray());
//                    Log.d("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));

                }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
