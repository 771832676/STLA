package com.stla.figure.utility;

import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;
//七牛下载工具类
public class DownLoad {


    static String accessKey = "kPeWiw4wnNnTAeTE0U5KTFJqC-Q1wlaqo65jz7TJ";          //AccessKey值
    static String secretKey = "d2tdwwKRCNTqFsBSrf2V5ap-UzWyQux5cLCeGX7T";          //SecretKey值

    //密钥配置
    static Auth auth = Auth.create(accessKey,secretKey);
    //Auth auth = Auth.create(KeyMatter.getAccessKey(),KeyMatter.getSecretKey());
    /**
     * 获取下载文件路径，即：donwloadUrl
     */
    public static String getDownloadUrl(String targetUrl) {
        String downloadUrl = auth.privateDownloadUrl(targetUrl);
        return downloadUrl;
    }
    /**
     * 下载
     */
    public static void download(String targetUrl) {
        //获取downloadUrl
        String downloadUrl = getDownloadUrl(targetUrl);
        //本地保存路径
        String filePath = "E:/atemp/";
        download(downloadUrl, filePath);
    }

    public static String downloadUrl(String targetUrl) {
        //获取downloadUrl
        return getDownloadUrl(targetUrl);
    }
    /**
     * 通过发送http get 请求获取文件资源
     */
    private static void download(String url, String filepath) {
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).build();
        Response resp = null;
        try {
            resp = client.newCall(req).execute();
            System.out.println(resp.isSuccessful());
            if(resp.isSuccessful()) {
                ResponseBody body = resp.body();
                InputStream is = body.byteStream();
                byte[] data = readInputStream(is);
                File imgFile = new File(filepath + "2.mp3");   //下载到本地的图片命名
                FileOutputStream fops = new FileOutputStream(imgFile);
                fops.write(data);
                fops.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unexpected code " + resp);
        }
    }
    /**
     * 读取字节输入流内容
     */
    private static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 2];
        int len = 0;
        try {
            while((len = is.read(buff)) != -1) {
                writer.write(buff, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toByteArray();
    }
    /**
     * 主函数：测试
     */
    public static void main(String[] args) {
        //构造私有空间的需要生成的下载的链接；
        //格式： http://私有空间绑定的域名/空间下的文件名.文件后缀
        //String targetUrl = "http://qxxkkn5df.hb-bkt.clouddn.com/3.mp4";   //外链域名下的图片路径

        String targetUrl = "http://xdoss.poteit.com/555.mp4";
        //new DownLoad().download(targetUrl);
        //new DownLoad().downloadUrl(targetUrl);
        System.out.println(new DownLoad().downloadUrl(targetUrl));
    }

}
