package com.wzf.mvpdemo.http.model.request;

import android.text.TextUtils;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.http.model.UserInfo;
import com.wzf.mvpdemo.utils.AppDeviceInfo;


/**
 * Created by zhenfei.wang on 2016/8/11.
 */
public class PostParam {
    private BaseParam baseParam = BaseParam.getInstance();

    public static class BaseParam {
        private static BaseParam mInstance;
        /**
         * guid = 20
         * phone = 18521709590
         * token = XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
         * platform = Android
         * appVersion = 1.5.2
         * osVersion = 5.0.2
         * deviceId = {"mac":"84:73:03:a0:bf:79","device_id":"869841026120177"}
         * deviceModel = Letv X500
         * screenW = 1080
         * screenH = 1920
         */

        private String uid;
        private String mobile;
        private String token;
        private String platform;
        private String appVersion;
        private String osVersion;
        private String deviceId;
        private String deviceModel;
        private String shopId;

        private BaseParam() {
            platform = "Android";
            appVersion = AppDeviceInfo.getAppVersionName(MyApplication.getAppInstance());
            osVersion = AppDeviceInfo.getSystemVersion();
            deviceId = AppDeviceInfo.getDeviceid();
            deviceModel = AppDeviceInfo.getPhoneType();
            shopId = UserInfo.getInstance().getShopId();
        }

        public static BaseParam getInstance() {
            if (mInstance == null) {
                synchronized (BaseParam.class) {
                    if (mInstance == null) {
                        mInstance = new BaseParam();
                    }
                }
            }
            // 随时修改
            mInstance.uid = UserInfo.getInstance().getUid();
            mInstance.mobile = UserInfo.getInstance().getMobile();
            mInstance.token = UserInfo.getInstance().getToken();
            if (TextUtils.isEmpty(mInstance.deviceId)) { // 防止有时候deviceid拿不到
                mInstance.deviceId = AppDeviceInfo.getDeviceid();
            }
            return mInstance;
        }

        @Override
        public String toString() {
            return "BaseParam{" +
                    "uid='" + uid + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", token='" + token + '\'' +
                    ", platform='" + platform + '\'' +
                    ", appVersion='" + appVersion + '\'' +
                    ", osVersion='" + osVersion + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    ", deviceModel='" + deviceModel + '\'' +
                    ", shopId=" + shopId +
                    '}';
        }
    }
}
