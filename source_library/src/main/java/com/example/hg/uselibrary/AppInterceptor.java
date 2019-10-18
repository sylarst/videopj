package com.example.hg.uselibrary;

import android.os.Build;
import android.text.TextUtils;

import com.age.mac.baselibrary.utils.LLog;
import com.example.hg.uselibrary.user.UserTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class AppInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }


    public static JSONObject getHeaderInfo() throws JSONException {
        // 获取当前手机的品牌和型号：
        String brand = Build.BRAND;
        brand = brand.replaceAll(" ", "_");

        String model = Build.MODEL;
        model = model.replaceAll(" ", "_");

//        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        JSONObject headerInfo = new JSONObject();

        headerInfo.put("plat", "android");
        headerInfo.put("appv", "1.2.0");
        headerInfo.put("apiv", "1");
        headerInfo.put("appid", "com.shenghetiyu.selltool");
        headerInfo.put("mac", "02:00:00:00:00:00");
        headerInfo.put("w", "1080");
        headerInfo.put("h", "1920");
        headerInfo.put("os", Build.VERSION.RELEASE);
        headerInfo.put("mid", model);
        headerInfo.put("brand", brand);
        headerInfo.put("appCodeName", "11");
        headerInfo.put("net", "3G");
        headerInfo.put("channel", "c10021");
        headerInfo.put("version", "1.2.0");
        headerInfo.put("androidid", "d2b40272287c4080");
        headerInfo.put("lon", "0");
        headerInfo.put("lat", "0");

        return headerInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header("Content-Type", "application/json; charset=utf-8");
        builder.header("Accept-Encoding", "gzip, deflate");
        builder.header("Connection", "keep-alive");
        if(UserTools.getUserTools().getUserInfo()!=null) {
            String token = UserTools.getUserTools().getToken();
            if (!TextUtils.isEmpty(token)) {
                builder.header("token", "token");
            }
            String warehouseID = UserTools.getUserTools().getWarehouseId();
            if (!TextUtils.isEmpty(warehouseID)) {
                builder.header("warehouseId", "warehouseId");
            }
        }
        builder.header("Accept", "*/*");

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

//        String requestStartMessage = "--> " + request.method() + ' ' + request.url();
////        Timber.d(requestStartMessage);


//        JSONObject headerInfo = null;
//        try {
//            headerInfo = getHeaderInfo();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        JSONObject params = new JSONObject();
        try {
            if(UserTools.getUserTools().getUserInfo()!=null) {
                String token = UserTools.getUserTools().getToken();
                if (!TextUtils.isEmpty(token)) {
                    params.put("token", token);
                }
                String warehouseID = UserTools.getUserTools().getWarehouseId();
                if (!TextUtils.isEmpty(warehouseID)) {
                    params.put("warehouseId", warehouseID);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (hasRequestBody) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            if (isPlaintext(buffer)) {
                String strBody = buffer.readString(UTF8);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(strBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    params.put("data",jsonObject);
                    LLog.d("info " + jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

//        Timber.d(params.toJSONString());
//        if (requestBody.contentType() != null) {
//            Timber.d("Content-Type: " + requestBody.contentType());
//        }
//        if (requestBody.contentLength() != -1) {
//            Timber.d("Content-Length: " + requestBody.contentLength());
//        }

        request = builder.post(RequestBody.create(requestBody.contentType(),params.toString())).build();

//        request = builder.post(requestBody).build();

        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";


//        String jsonString = responseBody.string();
//        responseBody.close();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
////            try {
////                // 如果这里能取出数据，而且没有问题，那就代表这是 code data msg 数据格式的
////                String code = jsonObject.getString("code");
////                if (TextUtils.equals(code,"999")) {
//////                    throw new NetErrorException(jsonObject.getString("message"), code);
////                    String aa="";
////                }
////            } catch (JSONException ignored) {
////
////            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
////            throw new NetErrorException("数据解析异常", NetErrorException.PARSE_ERROR);
//        } finally {
////            responseBody.close();
//        }
        return response;
    }
}
