package com.bw.xiangmu.api;

/**
 * @Auther: len
 * @Date: 2019/3/20 19:05:26
 * @Description:
 */
public interface api {
    //登录
    public static final String url_login = "http://mobile.bwstudent.com/small/user/v1/login";
    //注册
    public static final String url_register = "http://mobile.bwstudent.com/small/user/v1/register";
    //首页轮播
    public static final String url_ban = "http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
    //首页商品
    public static final String url_home = "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    //首页搜索
    public static final String url_key = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
    //商品详情
    public static final String url_details = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityDetailsById";
}
