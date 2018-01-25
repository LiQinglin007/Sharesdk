package com.example.lql.sharesdk.utils;

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
 * 作者：dell or Xiaomi Li
 * 时间： 2018/1/25
 * 内容：把回调放在activity里边，就能直接拿到结果过处理了
 * 最后修改：
 */
public class ShareSDKUtils {
    public static int SHARETYPE = 0X1;
    public static int LOGINTYPE = 0X2;

    /**
     * 分享到微博
     *
     * @param text   文本内容
     * @param picUrl 网络图片 （通过审核后才能添加）
     */
    public static void shareSina(String text, String picUrl, PlatformActionListener mPlatformActionListener) {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText(text);
        if (picUrl != null) {
            sp.setImageUrl(picUrl);
        }
        Platform weibo = PublicStaticData.myShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
//                 执行图文分享
        weibo.share(sp);
    }

    /**
     * 分享到QQ空间
     *
     * @param title    标题
     * @param content  内容
     * @param PicUrl   图片
     * @param titleUrl title链接
     */
    public static void shareQzone(String title, String content, String PicUrl, String titleUrl, PlatformActionListener mPlatformActionListener) {
        QZone.ShareParams sp = new QZone.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        sp.setTitleUrl(titleUrl); // 标题的超链接
        if (PicUrl != null) {
            sp.setImageUrl(PicUrl);
        }
        Platform qzone = PublicStaticData.myShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }

    /**
     * QQ
     *
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareQQ(String title, String content, String PicUrl, String titleUrl, PlatformActionListener mPlatformActionListener) {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        sp.setTitleUrl(titleUrl); // 标题的超链接
        if (PicUrl != null) {
            sp.setImageUrl(PicUrl);
        }
        Platform qq = PublicStaticData.myShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        qq.share(sp);
    }

    /**
     * 收藏
     *
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareWXF(String title, String content, String PicUrl, String titleUrl, PlatformActionListener mPlatformActionListener) {
        WechatFavorite.ShareParams sp = new WechatFavorite.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        sp.setTitleUrl(titleUrl); // 标题的超链接
        if (PicUrl != null) {
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://www.163.com/");
        Platform wxf = PublicStaticData.myShareSDK.getPlatform(WechatFavorite.NAME);
        wxf.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        wxf.share(sp);
    }


    /**
     * 朋友圈
     *
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl
     */
    public static void shareWXM(String title, String content, String PicUrl, String titleUrl, PlatformActionListener mPlatformActionListener) {
        WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        sp.setTitleUrl(titleUrl); // 标题的超链接
        if (PicUrl != null) {
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://www.sina.com.cn");
        Platform wxm = PublicStaticData.myShareSDK.getPlatform(WechatMoments.NAME);
        wxm.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        wxm.share(sp);
    }


    /**
     * 微信
     *
     * @param title
     * @param content
     * @param PicUrl
     * @param titleUrl Platform.SHARE_TEXT（分享文本），
     *                 Platform.SHARE_IMAGE（分享图片），
     *                 Platform.SHARE_WEBPAGE（分享网页，既图文分享），
     *                 Platform.SHARE_MUSIC（分享音频），
     *                 Platform.SHARE_VIDEO（分享视频），
     *                 Platform.SHARE_APPS（分享应用，仅微信支持），
     *                 Platform.SHARE_FILE（分享文件，仅微信支持）
     *                 Platform.SHARE_EMOJI（分享表情，仅微信支持）
     */
    public static void shareWX(String title, String content, String PicUrl, String titleUrl, PlatformActionListener mPlatformActionListener) {
        Wechat.ShareParams sp = new Wechat.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        sp.setTitleUrl(titleUrl); // 标题的超链接
        if (PicUrl != null) {
            sp.setImageUrl(PicUrl);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        sp.setUrl("http://qq.com");
        Platform wx = PublicStaticData.myShareSDK.getPlatform(Wechat.NAME);
        wx.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        // 执行图文分享
        wx.share(sp);
    }

    /**
     * 登录
     */
    public static void Login(String name, PlatformActionListener mPlatformActionListener) {
        Platform mPlatform = ShareSDK.getPlatform(name);
        mPlatform.setPlatformActionListener(mPlatformActionListener);
        mPlatform.authorize();//单独授权,OnComplete返回的hashmap是空的
        mPlatform.showUser(null);//授权并获取用户信息
    }
}
