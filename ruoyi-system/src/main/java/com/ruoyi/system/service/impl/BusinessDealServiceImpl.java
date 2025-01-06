//package com.ruoyi.system.service.impl;
//
//import com.ruoyi.system.domain.vo.ServiceResponse;
//import com.ruoyi.system.service.IBusinessDealService;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.api.java.tuple.Tuple3;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.functions.co.CoMapFunction;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 算子业务处理 服务层实现
// *
// * @author ruoyi
// */
//@Service
//public class BusinessDealServiceImpl implements IBusinessDealService
//{
//    //启动流程
//    public static void main() throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        // 开始节点 A1
//        DataStream<ServiceResponse> a1Stream = env.fromElements("A1")
//                .map(new MapFunction<String, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map(String nodeName) throws Exception {
//                        //查询数据库获取相应的值
//                        URL url = new URL("http://192.168.2.85:85/A1?a=3&b=4&c=1527");
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder response = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse(nodeName, response.toString());
//                    }
//                });
//
//        // 开始节点 A2
//        DataStream<ServiceResponse> a2Stream = env.fromElements("A2")
//                .map(new MapFunction<String, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map(String nodeName) throws Exception {
//                        URL url = new URL("http://192.168.2.85:85/A2?a=6&b=5");
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder response = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse(nodeName, response.toString());
//                    }
//                });
//
//
//        // 执行节点 A1A2B1
//        DataStream<ServiceResponse> b1Stream = a1Stream
//                .connect(a2Stream)
//                .map(new CoMapFunction<ServiceResponse, ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map1(ServiceResponse value1) throws Exception {
//                        return null;
//                    }
//
//                    @Override
//                    public ServiceResponse map2(ServiceResponse value2) throws Exception {
//                        return null;
//                    }
//
//                    public ServiceResponse coMap(ServiceResponse value1, ServiceResponse value2) throws Exception {
//                        String a1Response = value1.getResponse();
//                        String a2Response = value2.getResponse();
//                        int y1 = extractValue(a1Response, "Y1");
//                        int x1 = extractValue(a2Response, "X1");
//
//                        URL url = new URL("http://192.168.2.85:85/执行节点A1A2B1?a=1&b=3&X1=" + x1 + "&Y1=" + y1);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder response = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("执行节点A1A2B1", response.toString());
//                    }
//                });
//
//
//        // 执行节点 A1B2
//        DataStream<ServiceResponse> b2Stream = a1Stream
//                .map(new MapFunction<ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map(ServiceResponse value) throws Exception {
//                        String a1Response = value.getResponse();
//                        int y2 = extractValue(a1Response, "Y2");
//
//                        URL url = new URL("http://192.168.2.85:85/A1B2?a=1&b=4&Y2=" + y2);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder response = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("执行节点A1B2", response.toString());
//                    }
//                });
//
//        // 执行节点 B1C1
//        DataStream<ServiceResponse> c1Stream = b1Stream
//                .map(new MapFunction<ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map(ServiceResponse value) throws Exception {
//                        String response = value.getResponse();
//                        int o1 = extractValue(response, "O1");
//
//                        URL url = new URL("http://192.168.2.85:85/A3?a=6&O1=" + o1);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder responseStr = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            responseStr.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("执行节点B1C1", responseStr.toString());
//                    }
//                });
//
//        // 执行节点 B1C2
//        DataStream<ServiceResponse> c2Stream = b1Stream
//                .map(new MapFunction<ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map(ServiceResponse value) throws Exception {
//                        String response = value.getResponse();
//                        int o1 = extractValue(response, "O1");
//                        int o2 = extractValue(response, "O2");
//
//                        URL url = new URL("http://192.168.2.85:85/A3?a=1&b=4&c=5&d=8&e=9&O1=" + o1 + "&O2=" + o2);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder responseStr = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            responseStr.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("执行节点B1C2", responseStr.toString());
//                    }
//                });
//
//        // 执行节点 A1B1C3
//        DataStream<ServiceResponse> c3Stream = b1Stream
//                .connect(b2Stream)
//                .map(new CoMapFunction<ServiceResponse, ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map1(ServiceResponse value1) throws Exception {
//                        return null;
//                    }
//
//                    @Override
//                    public ServiceResponse map2(ServiceResponse value2) throws Exception {
//                        return null;
//                    }
//
//                    public ServiceResponse map(Tuple2<ServiceResponse, ServiceResponse> value) throws Exception {
//                        String b1Response = value.f0.getResponse();
//                        String b2Response = value.f1.getResponse();
//                        int z1 = extractValue(b2Response, "Z1");
//                        int y2 = extractValue(b1Response, "Y2");
//
//                        URL url = new URL("http://192.168.2.85:85/A3?a=1&b=4&c=5&d=6&Z1=" + z1 + "&Y2=" + y2);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder responseStr = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            responseStr.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("执行节点A1B1C3", responseStr.toString());
//                    }
//                });
//
//        // 结束节点 B1C2D1
//        DataStream<ServiceResponse> d1Stream = b1Stream
//                .connect(c2Stream)
//                .map(new CoMapFunction<ServiceResponse, ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map1(ServiceResponse value1) throws Exception {
//                        return null;
//                    }
//
//                    @Override
//                    public ServiceResponse map2(ServiceResponse value2) throws Exception {
//                        return null;
//                    }
//
//
//                    public ServiceResponse map(Tuple2<ServiceResponse, ServiceResponse> value) throws Exception {
//                        String b1Response = value.f0.getResponse();
//                        String c2Response = value.f1.getResponse();
//                        int o1 = extractValue(b1Response, "O1");
//                        int p1 = extractValue(c2Response, "P1");
//
//                        URL url = new URL("http://192.168.2.85:85/A3?a=7&O1=" + o1 + "&P1=" + p1);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder responseStr = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            responseStr.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("结束节点B1C2D1", responseStr.toString());
//                    }
//                });
//
//        // 结束节点 B1B2B3C3D2
//
//        // 先连接 b1Stream 和 b2Stream
//        DataStream<Tuple2<ServiceResponse, ServiceResponse>> b1b2Stream = b1Stream.connect(b2Stream).map(new CoMapFunction<ServiceResponse, ServiceResponse, Tuple2<ServiceResponse, ServiceResponse>>() {
//            @Override
//            public Tuple2<ServiceResponse, ServiceResponse> map1(ServiceResponse value1) throws Exception {
//                return new Tuple2<>(value1, null);
//            }
//
//            @Override
//            public Tuple2<ServiceResponse, ServiceResponse> map2(ServiceResponse value2) throws Exception {
//                return new Tuple2<>(null, value2);
//            }
//
//            public Tuple2<ServiceResponse, ServiceResponse> coMap(ServiceResponse value1, ServiceResponse value2) throws Exception {
//                return new Tuple2<>(value1, value2);
//            }
//        });
//
//        // 再连接 b1b2Stream 和 c3Stream
//        DataStream<ServiceResponse> d2Stream = b1b2Stream.connect(c3Stream).map(new CoMapFunction<Tuple2<ServiceResponse, ServiceResponse>, ServiceResponse, ServiceResponse>() {
//            @Override
//            public ServiceResponse map1(Tuple2<ServiceResponse, ServiceResponse> value1) throws Exception {
//                return null;
//            }
//
//            @Override
//            public ServiceResponse map2(ServiceResponse value2) throws Exception {
//                return null;
//            }
//
//            public ServiceResponse coMap(Tuple2<ServiceResponse, ServiceResponse> value12, ServiceResponse value3) throws Exception {
//                ServiceResponse b1Response = value12.f0;
//                ServiceResponse b2Response = value12.f1;
//                ServiceResponse c3Response = value3;
//                int o3 = extractValue(b1Response.getResponse(), "O3");
//                int z1 = extractValue(b2Response.getResponse(), "Z1");
//                int r1 = extractValue(c3Response.getResponse(), "R1");
//
//                URL url = new URL("http://192.168.2.85:85/A3?a=5&O3=" + o3 + "&Z1=" + z1 + "&R1=" + r1);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder responseStr = new StringBuilder();
//                while ((inputLine = in.readLine())!= null) {
//                    responseStr.append(inputLine);
//                }
//                in.close();
//
//                return new ServiceResponse("结束节点B1B2B3C3D2", responseStr.toString());
//            }
//        });
//        // 结束节点 A1C3D3
//        DataStream<ServiceResponse> d3Stream = a1Stream
//                .connect(c3Stream)
//                .map(new CoMapFunction<ServiceResponse, ServiceResponse, ServiceResponse>() {
//                    @Override
//                    public ServiceResponse map1(ServiceResponse value1) throws Exception {
//                        return null;
//                    }
//
//                    @Override
//                    public ServiceResponse map2(ServiceResponse value2) throws Exception {
//                        return null;
//                    }
//
//                    public ServiceResponse map(Tuple2<ServiceResponse, ServiceResponse> value) throws Exception {
//                        String a1Response = value.f0.getResponse();
//                        String c3Response = value.f1.getResponse();
//                        int y1 = extractValue(a1Response, "Y1");
//                        int r2 = extractValue(c3Response, "R2");
//                        int r3 = extractValue(c3Response, "R3");
//
//                        URL url = new URL("http://192.168.2.85:85/A3?a=8&Y1=" + y1 + "&R2=" + r2 + "&R3=" + r3);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuilder responseStr = new StringBuilder();
//                        while ((inputLine = in.readLine())!= null) {
//                            responseStr.append(inputLine);
//                        }
//                        in.close();
//
//                        return new ServiceResponse("结束节点A1C3D3", responseStr.toString());
//                    }
//                });
//
//        // 收集所有节点的响应并打印
//        DataStream<ServiceResponse> allResponses = d1Stream.union(d2Stream, d3Stream);
//        allResponses.print();
//
//        env.execute();
//
//    }
//
//    private static int extractValue(String response, String key) {
//        int startIndex = response.indexOf(key + ":");
//        if (startIndex == -1) {
//            return 0;
//        }
//        startIndex += key.length() + 1;
//        int endIndex = response.indexOf(",", startIndex);
//        if (endIndex == -1) {
//            endIndex = response.length();
//        }
//        return Integer.parseInt(response.substring(startIndex, endIndex));
//    }
//
//}
//
//
//
