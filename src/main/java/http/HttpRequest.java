package http;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * java发送http的get和post请求
 */
public class HttpRequest {
    /**
     * 向指定URL发送GET方式的请求
     * @param url  发送请求的URL
     * @param param 请求参数
     * @return URL 代表远程资源的响应
     */
    public static String sendGet(String url, String param){
        String result = "";
        String urlName = url + "?" + param;
        try{
            URL realUrl = new URL(urlName);
            //打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际的连接
            conn.connect();
            //获取所有的响应头字段
            Map<String,List<String>> map = conn.getHeaderFields();
            //遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "-->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常" + e);
            e.printStackTrace();
        }
        return result;


    }

    /**
     * 向指定URL发送POST方式的请求
     * @param urlPath  发送请求的URL
     * @param json 请求参数
     * @return URL 代表远程资源的响应
     */
    public static String sendPost(String urlPath, Object json){
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (json != null) {
                String jsonStr = JSONObject.toJSONString(json);
                byte[] writebytes = jsonStr.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(writebytes);
                outwritestream.flush();
                outwritestream.close();
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    //测试发送GET和POST请求
    public static void main(String[] args) throws Exception{
        //发送GET请求
        String s = HttpRequest.sendGet("https://www.baidu.com",null);
        System.out.println(s);
        //发送POST请求
        String s1 = HttpRequest.sendPost("http://localhost:8080/addComment", "questionId=1&content=美丽人生");
        System.out.println(s1);
    }
}
