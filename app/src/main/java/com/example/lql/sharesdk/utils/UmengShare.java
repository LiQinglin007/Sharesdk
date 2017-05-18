package com.example.lql.sharesdk.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

/**
 * Created by LQL on 2016/10/15.
 */

public class UmengShare {
    private static Activity myActivity;
    private static SHARE_MEDIA logintype;

    /**
     * 分享
     * @param mActivity
     * @param title  标题
     * @param context  内容
     * @param Sharetype  分享方式
     * SHARE_MEDIA.WEIXIN_FAVORITE
     * SHARE_MEDIA.WEIXIN_CIRCLE
     * SHARE_MEDIA.WEIXIN
     * SHARE_MEDIA.SINA
     * SHARE_MEDIA.QZONE
     * SHARE_MEDIA.QQ
     * @param picurl  图片地址
     */
    public static void  SharePic(Activity mActivity,String title,String context,SHARE_MEDIA Sharetype,String picurl){
        UmengShare.myActivity=mActivity;
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mActivity,mPermissionList,123);
        }
        if(picurl!=null){
            new ShareAction(mActivity).setPlatform(Sharetype)
                    .withText(context)
                    .withTitle(title)
                    .withMedia(new UMImage(mActivity,picurl))
                    .setCallback(umShareListener)
                    .share();
        }else{
            new ShareAction(mActivity).setPlatform(Sharetype)
                    .withText(context)
                    .withTitle(title)
                    .setCallback(umShareListener)
                    .share();
        }
    }

    /**
     *
     * @param mActivity
     * @param type  登录方式
     * SHARE_MEDIA.QQ
     * SHARE_MEDIA.WEIXIN
     * SHARE_MEDIA.SINA
     */
    public static void UmengLogin(Activity mActivity,SHARE_MEDIA type){
        UmengShare.myActivity=mActivity;
        UmengShare.logintype=type;
        PublicStaticData.mShareAPI = UMShareAPI.get( mActivity );

        PublicStaticData.mShareAPI.doOauthVerify(mActivity,type, umAuthListener);
    }


    /**
     *  第三方登录监听
     */
    public static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(myActivity, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e("data11111111111",data.toString());
            if(UmengShare.logintype==SHARE_MEDIA.QQ){
                Map<String, Object> map = JSON.parseObject(data.toString(), new TypeReference<Map<String, Object>>() {
                });
                final String openid = map.get("openid").toString();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( myActivity, "授权失败", Toast.LENGTH_SHORT).show();
            Log.e("data11111111111","aaaaaaaaaaaaaaaaaaaaa");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( myActivity, "关闭授权", Toast.LENGTH_SHORT).show();
            Log.e("data11111111111","cccccccccccccc");
        }
    };

    /**
     * 分享监听
     */
    public static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            Toast.makeText(myActivity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(myActivity,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(myActivity,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
