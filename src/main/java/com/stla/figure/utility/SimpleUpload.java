package com.stla.figure.utility;

import java.io.File;
import java.io.FileInputStream;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 七牛 java sdk 简单上传，覆盖上传，文件上传
 */
public class SimpleUpload {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = "kPeWiw4wnNnTAeTE0U5KTFJqC-Q1wlaqo65jz7TJ"; //这两个登录七牛 账号里面可以找到
    private static String SECRET_KEY = "d2tdwwKRCNTqFsBSrf2V5ap-UzWyQux5cLCeGX7T";

    // 上传空间
    private static String bucketName = "xdxnrx";
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    static Configuration cfg = new Configuration(Zone.autoZone());   //zong1() 代表华北地区
    static UploadManager uploadManager = new UploadManager(cfg);
    /**
     * 获取凭证
     * @param key 如果需要覆盖上传则设置此参数
     */
    public static String getUpToken(String key) {
        return auth.uploadToken(bucketName);
    }
    /**
     * 上传方法1
     * @param filePath 文件路径  （也可以是字节数组、或者File对象）
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public static void upload(String filePath, String key, String bucketName) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken(key));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
    /**
     * 上传方法2
     * @param file 文件
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     */
    public static void upload(File file, String key) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken(key));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
    /**
     * 上传方法3 覆盖上传
     * @param path 上传文件路径
     * @param key 文件名
     */
    public static void overrideUpload(String path, String key) {
        try {
            String token = getUpToken(key);//获取 token
            Response response = uploadManager.put(path, key, token);//执行上传，通过token来识别 该上传是“覆盖上传”
            System.out.println(response.toString());
        } catch (QiniuException e) {
            System.out.println(e.response.statusCode);
            e.printStackTrace();
        }
    }
    // base64方式上传
    public static void put64image(String filePath, String fileName, String key) throws Exception {
        FileInputStream fis = null;
        int l = (int) (new File(filePath).length());
        byte[] src = new byte[l];
        fis = new FileInputStream(new File(filePath));
        fis.read(src);
        String file64 = Base64.encodeToString(src, 0);
        String url = "http://upload.qiniu.com/putb64/" + l + "/key/" + UrlSafeBase64.encodeToString(fileName);
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken(key)).post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }
    /**
     * 主函数：程序入口，测试功能
     */
    public static void main(String[] args) {
        // 上传文件的路径，因为在Mac下，所以路径和windows下不同
        //String filePath = "D:\\20210727.mp4";
        String filePath = "D:\\1.jpg";

        // 上传到七牛后保存的文件名
        String key = "2.jpg";
        // 这里的filepath可以直接替换成File如下注释所示
        File file=new File(filePath);
        new SimpleUpload().upload(file, key);
        //new SimpleUpload().upload(filePath, key, bucketName);
    }

}
