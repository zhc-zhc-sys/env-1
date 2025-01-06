//package com.ruoyi.web.controller.liteflow;
//
//import com.example.Message;
//import org.springframework.stereotype.Component;
//
//@Component("java3")
//public class Java3 {
//    public static void main(String[] args) throws Exception {
//        // 假设接收到的数据是从 Java2 发来的
//        byte[] serializedData = getDataFromJava2();
//
//        // 反序列化 ProtoBuf 数据
//        Message.ServiceData serviceData = Message.ServiceData.parseFrom(serializedData);
//
//        // 执行业务逻辑
//        System.out.println("Java3 received processed data: " + serviceData.getParam());
//
//        // 输出最终结果
//        String result = "Final result: " + serviceData.getParam();
//        System.out.println(result);
//    }
//
//    private static byte[] getDataFromJava2() {
//        // 模拟接收从 Java2 传递过来的数据
//        Message.ServiceData data = Message.ServiceData.newBuilder()
//                .setId("service2")
//                .setParam("inputData_processed_by_Java2")
//                .setValue(456)
//                .build();
//        return data.toByteArray();
//    }
//}