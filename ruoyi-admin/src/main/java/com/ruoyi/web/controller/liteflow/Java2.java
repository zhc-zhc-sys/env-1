//package com.ruoyi.web.controller.liteflow;
//
//import com.example.Message;
//import org.springframework.stereotype.Component;
//
//@Component("java2")
//public class Java2 {
//    public static void main(String[] args) throws Exception {
//        // 假设接收到的数据是从 Java1 发来的
//        byte[] serializedData = getDataFromJava1();
//
//        // 反序列化 ProtoBuf 数据
//        Message.ServiceData serviceData = Message.ServiceData.parseFrom(serializedData);
//
//        // 处理数据
//        System.out.println("Java2 received data: " + serviceData.getParam());
//
//        // 假设我们做了一些处理
//        String processedData = serviceData.getParam() + "_processed_by_Java2";
//
//        // 将处理结果传递给 Java3
//        byte[] processedSerializedData = serviceData.toBuilder()
//                .setParam(processedData)
//                .build()
//                .toByteArray();
//
//        // 模拟将数据传递给 Java3
//        sendToJava3(processedSerializedData);
//    }
//
//    private static byte[] getDataFromJava1() {
//        // 模拟接收从 Java1 传递过来的数据
//        Message.ServiceData data = Message.ServiceData.newBuilder()
//                .setId("service1")
//                .setParam("inputData")
//                .setValue(123)
//                .build();
//        return data.toByteArray();
//    }
//
//    private static void sendToJava3(byte[] data) {
//        // 在此模拟将数据传递给 Java3
//        System.out.println("Sending processed data to Java3...");
//    }
//}