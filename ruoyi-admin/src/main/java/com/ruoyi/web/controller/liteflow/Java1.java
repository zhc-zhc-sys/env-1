//package com.ruoyi.web.controller.liteflow;
//
//import com.example.Message;
//import com.yomahub.liteflow.core.FlowExecutor;
//import org.springframework.stereotype.Component;
//
//@Component("java1")
//public class Java1 {
//        public static void main(String[] args) throws Exception {
//            // 构造请求参数
//            Message.ServiceData requestData = Message.ServiceData.newBuilder()
//                    .setId("service1")
//                    .setParam("inputData")
//                    .setValue(123)
//                    .build();
//
//            // 将数据序列化为字节数组
//            byte[] serializedData = requestData.toByteArray();
//
//            // 调用 Liteflow 流程，传递序列化数据
//            FlowExecutor flowExecutor = new FlowExecutor();
//            flowExecutor.execute("serviceFlow", serializedData);
//        }
//}