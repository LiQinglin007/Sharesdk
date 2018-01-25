package com.example.lql.sharesdk.application;

import android.app.Application;

import com.example.lql.sharesdk.utils.PublicStaticData;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by LQL on 2016/10/14.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        PublicStaticData.myShareSDK= new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());

        PlatformConfig.setWeixin("wx282305cc871b77bc", "bdf0b6e3ff283ec29a12abf40ff62c1a");
        PlatformConfig.setSinaWeibo("170221996", "75314930606f946864ee0d7aeecc46ed");
//        Config.REDIRECT_URL = "http://sns.whalecloud.com";//您新浪后台的回调地址
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";//您新浪后台的回调地址
//        PlatformConfig.setSinaWeibo("212218324", "a563a1ad5be5ec9d044cfd5844159b0d");
        PlatformConfig.setQQZone("1105747496", "EVHTBI0P5NDpuNIZ");

        UMShareAPI.get(this);

        super.onCreate();
    }
}
