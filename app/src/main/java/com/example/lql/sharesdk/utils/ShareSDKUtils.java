package com.example.lql.sharesdk.utils;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by LQL on 2016/10/14.
 */

public class ShareSDKUtils {
private static String type="";

    /**
     * 分享到微博
     * @param text  文本内容
     * @param picUrl  网络图片 （通过审核后才能添加）
     */
    public static  void shareSina(String text,String picUrl,Context context){
        type="share";
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText(text);
        if(picUrl!=null){
            sp.setImageUrl(picUrl);
        }
        Platform weibo = PublicStaticData.myShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
//                 执行图文分享
        weibo.share(sp);
    }

    /**
     * 分享到QQ空间
     * @param title 标题
     * @param content 内容
     * @param PicUrl 图片
     * @param titleUrl  title链接
     */
    public static void shareQzone(String title,String content,String PicUrl,String titleUrl){
        type="share";
        QZone.ShareParams sp = new  QZone.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if(titleUrl!=null){
            sp.setTitleUrl(titleUrl); // 标题的超链接
        }
        if(PicUrl!=null){
            sp.setImageUrl(PicUrl);
        }
        Platform qzone = PublicStaticData.myShareSDK.getPlatform (QZone.NAME);
        qzone. setPlatformActionListener (mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }

    /**
     * QQ
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareQQ(String title,String content,String PicUrl,String titleUrl){
        type="share";
        QQ.ShareParams  sp=new   QQ.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if(titleUrl!=null){
            sp.setTitleUrl(titleUrl); // 标题的超链接
        }
        if(PicUrl!=null){
            sp.setImageUrl(PicUrl);
        }
        Platform qq = PublicStaticData.myShareSDK.getPlatform (QQ.NAME);
        qq. setPlatformActionListener (mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qq.share(sp);
    }

    /**
     * 收藏
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareWXF(String title,String content,String PicUrl,String titleUrl){
        type="share";
        WechatFavorite.ShareParams sp=new WechatFavorite.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if(titleUrl!=null){
            sp.setTitleUrl(titleUrl); // 标题的超链接
        }
        if(PicUrl!=null){
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://www.163.com/");
        Platform qq = PublicStaticData.myShareSDK.getPlatform (WechatFavorite.NAME);
        qq. setPlatformActionListener (mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qq.share(sp);
    }



    /**
     * 朋友圈
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareWXM(String title,String content,String PicUrl,String titleUrl){
        type="share";
        WechatMoments.ShareParams sp=new WechatMoments.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if(titleUrl!=null){
            sp.setTitleUrl(titleUrl); // 标题的超链接
        }
        if(PicUrl!=null){
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://www.sina.com.cn");
        Platform qq = PublicStaticData.myShareSDK.getPlatform (WechatMoments.NAME);
        qq. setPlatformActionListener (mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qq.share(sp);
    }



    /**
     * 微信
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     * Platform.SHARE_TEXT（分享文本），
    Platform.SHARE_IMAGE（分享图片），
    Platform.SHARE_WEBPAGE（分享网页，既图文分享），
    Platform.SHARE_MUSIC（分享音频），
    Platform.SHARE_VIDEO（分享视频），
    Platform.SHARE_APPS（分享应用，仅微信支持），
    Platform.SHARE_FILE（分享文件，仅微信支持）
    Platform.SHARE_EMOJI（分享表情，仅微信支持）
     */
    public static void shareWX(String title,String content,String PicUrl,String titleUrl){
        type="share";
        Wechat.ShareParams sp=new Wechat.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if(titleUrl!=null){
            sp.setTitleUrl(titleUrl); // 标题的超链接
        }
        if(PicUrl!=null){
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://qq.com");
        Platform wx = PublicStaticData.myShareSDK.getPlatform (Wechat.NAME);
        wx. setPlatformActionListener (mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        wx.share(sp);
    }
    /**
     * 登录
     */
    public static void  Login(String name){
        type="login";
        Platform mPlatform = ShareSDK.getPlatform(name);
        mPlatform.setPlatformActionListener(mPlatformActionListener);
        mPlatform.authorize();//单独授权,OnComplete返回的hashmap是空的
        mPlatform.showUser(null);//授权并获取用户信息
    }

    public static PlatformActionListener mPlatformActionListener= new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            if(type.equals("login")){
                Log.e("onComplete","登录成功");
                Log.e("openid",platform.getDb().getUserId());//拿到登录后的openid
                Log.e("username",platform.getDb().getUserName());//拿到登录用户的昵称
            }else{
                Log.e("onComplete","分享成功");
            }
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e("onError",throwable.toString()+"");
            if(type.equals("login")){
                Log.e("onError","登录失败"+throwable.toString());
            }else{
                Log.e("onError","分享失败"+throwable.toString());
            }
        }

        @Override
        public void onCancel(Platform platform, int i) {
            if(type.equals("login")){
                Log.e("onCancel","登录取消");
            }else{
                Log.e("onCancel","分享取消");
            }
        }
    };
}
