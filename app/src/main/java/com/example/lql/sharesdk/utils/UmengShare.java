package com.example.lql.sharesdk.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/1/25
 * 内容：把回调放在activity里边，就能直接拿到结果过处理了
 * 最后修改：
 */
public class UmengShare {
    /**
     * 分享
     *
     * @param mActivity
     * @param title     标题
     * @param context   内容
     * @param Sharetype 分享方式
     *                  SHARE_MEDIA.WEIXIN_FAVORITE
     *                  SHARE_MEDIA.WEIXIN_CIRCLE
     *                  SHARE_MEDIA.WEIXIN
     *                  SHARE_MEDIA.SINA
     *                  SHARE_MEDIA.QZONE
     *                  SHARE_MEDIA.QQ
     * @param picurl    图片地址
     */
    public static void SharePic(Activity mActivity, String title, String context, SHARE_MEDIA Sharetype, String picurl, UMShareListener umShareListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mActivity, mPermissionList, 123);
        }
        if (picurl != null) {
            new ShareAction(mActivity).setPlatform(Sharetype)
                    .withText(context)
                    .withTitle(title)
                    .withMedia(new UMImage(mActivity, picurl))
                    .setCallback(umShareListener)
                    .share();
        } else {
            new ShareAction(mActivity).setPlatform(Sharetype)
                    .withText(context)
                    .withTitle(title)
                    .setCallback(umShareListener)
                    .share();
        }
    }

    /**
     * @param mActivity
     * @param type      登录方式
     *                  SHARE_MEDIA.QQ
     *                  SHARE_MEDIA.WEIXIN
     *                  SHARE_MEDIA.SINA
     */
    public static void UmengLogin(Activity mActivity, SHARE_MEDIA type, UMAuthListener umAuthListener) {
        PublicStaticData.mShareAPI = UMShareAPI.get(mActivity);
        PublicStaticData.mShareAPI.doOauthVerify(mActivity, type, umAuthListener);
    }

}
