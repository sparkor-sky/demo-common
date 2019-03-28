package http;

/*
*
accessThread94:838：response :{"code":5000,"result":"内部错误","message":"Internal server error","timestamp":1550728703009}
accessThread71:849：data: {"app":"wuxi","channel":"1","code":"accessThread71517576777","event":1,"id":"questionnaire-wuxi-35","timestamp":1550728703002}
* */

import com.alibaba.fastjson.JSONObject;

public class Main {
    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            QThread my = new AccessThread();
            my.setName("accessThread" + i);
            Thread thread = new Thread(my);
            thread.start();

            QThread run = new QuotaThread();
            run.setName("quotaThread" + i);
            Thread thread2 = new Thread(run);
            thread2.start();
        }
    }

    private static class AccessThread extends QThread{

        @Override
        public void setEvent() {
            super.event = 1;
        }
    }

    private static class QuotaThread extends QThread{

        @Override
        public void setEvent() {
            super.event = 3;
        }
    }

    private static abstract class QThread implements Runnable{

        private String name;

        private int event;

        public void setName(String name){
            this.name = name;
        }

        public abstract void setEvent();

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            setEvent();
            String url = "http://open.xianzhi.netease.com/api/questionnaire/da";
            for (int i = 0; i < 1000; i++) {
                Da da = new Da();
                da.setApp("wuxi");
                da.setChannel("1");
                da.setCode(name + "517576777");
                da.setEvent(event);
                da.setId("questionnaire-wuxi-35");
                da.setTimestamp(System.currentTimeMillis());
                String response = HttpRequest.sendPost(url,da);
                if(!response.startsWith("{\"code\":200")){
                    String data = JSONObject.toJSONString(da);
                    System.out.println(name + ":" + i + "：data: " + data);
                    System.out.println(name + ":" + i + "：response :" + response);
                }
            }
            System.out.println(name + "用时：" + (System.currentTimeMillis() - startTime)/1000 + " s");
        }
    }

}
