package com.wzf.mvpdemo.ui.activity.login;

import android.app.Activity;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wzf.mvpdemo.http.OkHttpUtils;
import com.wzf.mvpdemo.http.OnOkHttpDataCallBack;
import com.wzf.mvpdemo.http.OrderUrlService;
import com.wzf.mvpdemo.http.model.request.BaseParam;
import com.wzf.mvpdemo.http.model.request.BaseParamDto;
import com.wzf.mvpdemo.http.model.request.UserVideoRequestDto;
import com.wzf.mvpdemo.http.requestbody.ProgressRequestListener;
import com.wzf.mvpdemo.utils.JsonUtils;
import com.wzf.mvpdemo.utils.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 16:10
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final  LoginContract.View mLoginView;
    private Activity mActivity;

    public LoginPresenter(@NonNull LoginContract.View loginView, @NonNull Activity activity){
        this.mLoginView = checkNotNull(loginView, "tasksView cannot be null!");
        this.mActivity = checkNotNull(activity);
        mLoginView.setPresenter(this);
    }

    @Override
    public void login(String account, final String psw) {
        if (StringUtils.isEmpty(account)) {
            mLoginView.showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(psw)) {
            mLoginView.showToast("请输入密码");
            return;
        }
        Log.i("------------->>>>>" , "mainThread:" + Thread.currentThread().getId());
        OrderUrlService service = OkHttpUtils.getInstance().createReqeustService(OrderUrlService.class, new ProgressRequestListener() {
            @Override
            public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                Log.i("------------->>>>>" , bytesWritten * 100.0f / contentLength + "%");
            }
        });

        File fileCover = new File(Environment.getExternalStorageDirectory(), "small.jpg");
        File fileVideo = new File(Environment.getExternalStorageDirectory(), "123.wmv");
                RequestBody photo = RequestBody.create(MediaType.parse("image/png"), fileCover);
                RequestBody video = RequestBody.create(MediaType.parse("image/png"), fileVideo);
        Map<String, RequestBody> photos = new HashMap<>();
        photos.put("myfiles\"; filename=\"icon.png", photo);
        photos.put("myfiles\"; filename=\"123.mp4", video);


        service.upload(photos,RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJson(new BaseParam()))).enqueue(new OnOkHttpDataCallBack(mActivity, String.class, false) {
            @Override
            public void onSuccess(String json) throws Exception {
                super.onSuccess(json);
                mLoginView.showToast(json);

            }

            @Override
            public void onError(String error) throws Exception {
                super.onError(error);
                mLoginView.showToast(error);
            }
        });
//        service.login(Constants.OS, AppDeviceInfo.getPhoneType(), AppDeviceInfo.getDeviceid(), "",
//                account, psw)
//                .subscribeOn(Schedulers.io())
////                .map(new Func1<BaseResponse<LogInDto>, BaseResponse<LogInDto>>() {
////                    @Override
////                    public BaseResponse<LogInDto> call(BaseResponse<LogInDto> logInDtoBaseResponse) {
////                        return logInDtoBaseResponse;
////                    }
////                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ResponseSubscriber<LogInDto>(mActivity, true) {
//                    @Override
//                    public void onSuccess(LogInDto logInDto) {
//                        super.onSuccess(logInDto);
//                        Log.i("------------->>>>>", "onSuccess:" + Thread.currentThread().getId());
//                        Log.i("------------->>>>>", "error:" + logInDto.toString());
//                    }
//
//                    @Override
//                    public void onFailure(int code, String message) {
//                        super.onFailure(code, message);
//                        Log.i("------------->>>>>", "onFailure:" + Thread.currentThread().getId());
//                        Log.i("------------->>>>>", "code:" + code + "  errorMessage" + message);
//                    }
//                });

//        new Observer<LogInDto>() {
//            @Override
//            public void onCompleted() {
//                Log.i("------------->>>>>" , "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("------------->>>>>" , "error:" + e.toString());
//            }
//
//            @Override
//            public void onNext(LogInDto logInDto) {
//                if(logInDto != null){
//                    Log.i("------------->>>>>" , logInDto.toString());
//                }
//            }
//        }




//        service.login(Constants.OS, AppDeviceInfo.getPhoneType(), AppDeviceInfo.getDeviceid(), "",
//                account, psw).enqueue(new OnOkHttpDataCallBack(mActivity, String.class, true) {
//            @Override
//            public void onSuccess(String json) throws Exception {
//                super.onSuccess(json);
//                mLoginView.showToast(json);
//                LogInDto logInDto = JsonUtils.fromJSON(LogInDto.class, json);
//                logInDto.setPsw(psw);
//                UserInfo.getInstance().setLoginUserInfo(logInDto);
//                Intent intent = new Intent();
//                intent.setClass(mActivity, BossPageActivity.class);
//                switch (logInDto.getRole()) {
////                    case UserInfo.ROLE_SPECIALIST:
////                        intent.setClass(LogInActivity.this, ConfirmOrderActivity.class);
////                        break;
////                    case UserInfo.ROLE_CASHIER:
////                        intent.setClass(LogInActivity.this, ConfirmCheckActivity.class);
////                        break;
////                    case UserInfo.ROLE_BOSS:
////
////                        break;
//                }
//                mActivity.startActivity(intent);
//                mActivity.finish();
//            }
//
//            @Override
//            public void onError(String error) throws Exception {
//                super.onError(error);
//                mLoginView.showToast(error);
//            }
//        });
    }
}
