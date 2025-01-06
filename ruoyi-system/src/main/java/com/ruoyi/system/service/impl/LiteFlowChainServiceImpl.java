package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.LiteFlowChain;
import com.ruoyi.system.domain.LiteFlowScript;
import com.ruoyi.system.domain.dto.ProcessLineDto;
import com.ruoyi.system.domain.dto.ProcessPointDto;
import com.ruoyi.system.domain.dto.ProcessPointItemDto;
import com.ruoyi.system.mapper.LiteFlowChainMapper;
import com.ruoyi.system.mapper.LiteFlowNodeRelaMapper;
import com.ruoyi.system.mapper.LiteFlowScriptMapper;
import com.ruoyi.system.service.ILiteFlowChainService;
import com.ruoyi.system.service.ILiteFlowNodeRelaService;
import com.ruoyi.system.service.IServiceNodeService;
import com.yomahub.liteflow.core.FlowExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LiteFlowChainServiceImpl extends ServiceImpl<LiteFlowChainMapper, LiteFlowChain> implements ILiteFlowChainService {

    @Resource
    private LiteFlowChainMapper liteFlowChainMapper;

    @Resource
    private LiteFlowScriptMapper liteFlowScriptMapper;

    @Resource
    private IServiceNodeService serviceNodeService;

    @Resource
    private ILiteFlowNodeRelaService linkNodeRelaService;

    @Resource
    private LiteFlowNodeRelaMapper linkNodeRelaMapper;

    @Resource
    private FlowExecutor flowExecutor;



    @Override
    public void updateProcess(String json) {
        liteFlowChainMapper.deleteAll();
        liteFlowScriptMapper.deleteAll();

       String goodJsonStr ="[\n" +
               "    {\n" +
               "        \"shape\": \"edge\",\n" +
               "        \"attrs\": {\n" +
               "            \"line\": {\n" +
               "                \"stroke\": \"#faad14\"\n" +
               "            }\n" +
               "        },\n" +
               "        \"id\": \"8cc455b6-56b9-48d2-a0ed-2f4f74454d89\",\n" +
               "        \"router\": {\n" +
               "            \"name\": \"manhattan\"\n" +
               "        },\n" +
               "        \"connector\": {\n" +
               "            \"name\": \"smooth\"\n" +
               "        },\n" +
               "        \"zIndex\": 0,\n" +
               "        \"source\": {\n" +
               "            \"cell\": \"323c8dcf-1bec-46e8-bb99-00fd2fc3a984\",\n" +
               "            \"port\": \"X1\"\n" +
               "        },\n" +
               "        \"target\": {\n" +
               "            \"cell\": \"0918f24f-93a0-4888-afab-4575a43fe9c1\",\n" +
               "            \"port\": \"Y2\"\n" +
               "        },\n" +
               "        \"tools\": {\n" +
               "            \"items\": [],\n" +
               "            \"name\": null\n" +
               "        }\n" +
               "    },\n" +
               "    {\n" +
               "        \"position\": {\n" +
               "            \"x\": 170,\n" +
               "            \"y\": 110\n" +
               "        },\n" +
               "        \"size\": {\n" +
               "            \"width\": 150,\n" +
               "            \"height\": 200\n" +
               "        },\n" +
               "        \"view\": \"vue-shape-view\",\n" +
               "        \"attrs\": {\n" +
               "            \"body\": {\n" +
               "                \"rx\": 6,\n" +
               "                \"ry\": 6\n" +
               "            }\n" +
               "        },\n" +
               "        \"shape\": \"试验战局规划\",\n" +
               "        \"ports\": {\n" +
               "            \"groups\": {\n" +
               "                \"left\": {\n" +
               "                    \"position\": \"left\",\n" +
               "                    \"attrs\": {\n" +
               "                        \"circle\": {\n" +
               "                            \"r\": 6,\n" +
               "                            \"magnet\": true,\n" +
               "                            \"stroke\": \"#7F6000\",\n" +
               "                            \"strokeWidth\": 1,\n" +
               "                            \"fill\": \"#7F6000\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                },\n" +
               "                \"right\": {\n" +
               "                    \"position\": \"right\",\n" +
               "                    \"label\": {\n" +
               "                        \"position\": {\n" +
               "                            \"name\": \"outside\"\n" +
               "                        }\n" +
               "                    },\n" +
               "                    \"attrs\": {\n" +
               "                        \"circle\": {\n" +
               "                            \"r\": 6,\n" +
               "                            \"magnet\": true,\n" +
               "                            \"stroke\": \"#7F6000\",\n" +
               "                            \"strokeWidth\": 1,\n" +
               "                            \"fill\": \"#7F6000\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                }\n" +
               "            },\n" +
               "            \"items\": [\n" +
               "                {\n" +
               "                    \"id\": \"X1\",\n" +
               "                    \"group\": \"right\",\n" +
               "                    \"type\": \"Integer\",\n" +
               "                    \"attrs\": {\n" +
               "                        \"text\": {\n" +
               "                            \"fontSize\": 10,\n" +
               "                            \"fill\": \"#fff\",\n" +
               "                            \"text\": \"导控任务\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                }\n" +
               "            ]\n" +
               "        },\n" +
               "        \"id\": \"323c8dcf-1bec-46e8-bb99-00fd2fc3a984\",\n" +
               "        \"label\": \"试验战局规划\",\n" +
               "        \"data\": {\n" +
               "            \"id\": 2,\n" +
               "            \"operatorName\": \"试验战局规划\",\n" +
               "            \"operatorType\": \"0\",\n" +
               "            \"url\": \"http://127.0.0.1:11909/system/businessService/callA2\",\n" +
               "            \"input\": \"{}\",\n" +
               "            \"output\": \"[{\\r\\n\\t\\\"name\\\": \\\"导控任务\\\",\\r\\n\\t\\\"id\\\": \\\"X1\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}]\",\n" +
               "            \"attribute\": \"[{\\r\\n\\t\\\"name\\\": \\\"指挥控制任务集\\\",\\r\\n\\t\\\"id\\\": \\\"a\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}, {\\r\\n\\t\\\"name\\\": \\\"导调控制任务集\\\",\\r\\n\\t\\\"id\\\": \\\"b\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}]\",\n" +
               "            \"code\": \"A2\",\n" +
               "            \"operatorAttribute\": \"{\\r\\n\\t \\\"input\\\":{\\r\\n\\t },\\r\\n\\t \\\"output\\\":{\\r\\n\\t   \\\"X1\\\":\\\"Integer\\\"\\r\\n\\t },\\r\\n\\t \\\"attribute\\\":{\\r\\n\\t    \\\"a\\\":\\\"Integer\\\",\\r\\n\\t\\t\\\"b\\\":\\\"Integer\\\"\\r\\n\\r\\n\\t }\\r\\n}\\r\\n\",\n" +
               "            \"calculationRules\": \"{\\\"X1\\\":{\\\"rules\\\":\\\"ADD\\\",\\\"variable\\\":{\\\"a\\\":\\\"Integer\\\",\\\"b\\\":\\\"Integer\\\"}}}\",\n" +
               "            \"delFlag\": \"0\",\n" +
               "            \"createUserId\": null,\n" +
               "            \"updateUserId\": null,\n" +
               "             \"createTime\": null,\"attributeParamJson\":{\"a\":\"1\",\"b\":\"2\",\"c\":\"5\",\"d\":\"5\",\"e\":\"6\",\"f\":\"7\"},\n" +
               "            \"updateTime\": null\n" +
               "        },\n" +
               "        \"zIndex\": 2\n" +
               "    },\n" +
               "    {\n" +
               "        \"position\": {\n" +
               "            \"x\": 530,\n" +
               "            \"y\": 110\n" +
               "        },\n" +
               "        \"size\": {\n" +
               "            \"width\": 150,\n" +
               "            \"height\": 200\n" +
               "        },\n" +
               "        \"view\": \"vue-shape-view\",\n" +
               "        \"attrs\": {\n" +
               "            \"body\": {\n" +
               "                \"rx\": 6,\n" +
               "                \"ry\": 6\n" +
               "            }\n" +
               "        },\n" +
               "        \"shape\": \"评估指标体系选择\",\n" +
               "        \"ports\": {\n" +
               "            \"groups\": {\n" +
               "                \"left\": {\n" +
               "                    \"position\": \"left\",\n" +
               "                    \"attrs\": {\n" +
               "                        \"circle\": {\n" +
               "                            \"r\": 6,\n" +
               "                            \"magnet\": true,\n" +
               "                            \"stroke\": \"#7F6000\",\n" +
               "                            \"strokeWidth\": 1,\n" +
               "                            \"fill\": \"#7F6000\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                },\n" +
               "                \"right\": {\n" +
               "                    \"position\": \"right\",\n" +
               "                    \"label\": {\n" +
               "                        \"position\": {\n" +
               "                            \"name\": \"outside\"\n" +
               "                        }\n" +
               "                    },\n" +
               "                    \"attrs\": {\n" +
               "                        \"circle\": {\n" +
               "                            \"r\": 6,\n" +
               "                            \"magnet\": true,\n" +
               "                            \"stroke\": \"#7F6000\",\n" +
               "                            \"strokeWidth\": 1,\n" +
               "                            \"fill\": \"#7F6000\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                }\n" +
               "            },\n" +
               "            \"items\": [\n" +
               "                {\n" +
               "                    \"id\": \"Y2\",\n" +
               "                    \"group\": \"left\",\n" +
               "                    \"type\": \"Integer\",\n" +
               "                    \"attrs\": {\n" +
               "                        \"text\": {\n" +
               "                            \"fontSize\": 10,\n" +
               "                            \"fill\": \"#fff\",\n" +
               "                            \"text\": \"评估对象类型\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                },\n" +
               "                {\n" +
               "                    \"id\": \"Z1\",\n" +
               "                    \"group\": \"right\",\n" +
               "                    \"type\": \"Integer\",\n" +
               "                    \"attrs\": {\n" +
               "                        \"text\": {\n" +
               "                            \"fontSize\": 10,\n" +
               "                            \"fill\": \"#fff\",\n" +
               "                            \"text\": \"评估指标体系\"\n" +
               "                        }\n" +
               "                    }\n" +
               "                }\n" +
               "            ]\n" +
               "        },\n" +
               "        \"id\": \"0918f24f-93a0-4888-afab-4575a43fe9c1\",\n" +
               "        \"label\": \"评估指标体系选择\",\n" +
               "        \"data\": {\n" +
               "            \"id\": 4,\n" +
               "            \"operatorName\": \"评估指标体系选择\",\n" +
               "            \"operatorType\": \"1\",\n" +
               "            \"url\": \"http://127.0.0.1:11909/system/businessService/callA1B2\",\n" +
               "            \"input\": \"[{\\r\\n\\t\\\"name\\\": \\\"评估对象类型\\\",\\r\\n\\t\\\"id\\\": \\\"Y2\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}]\",\n" +
               "            \"output\": \"[{\\r\\n\\t\\\"name\\\": \\\"评估指标体系\\\",\\r\\n\\t\\\"id\\\": \\\"Z1\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}]\",\n" +
               "            \"attribute\": \"[{\\r\\n\\t\\\"name\\\": \\\"评估指标体系\\\",\\r\\n\\t\\\"id\\\": \\\"a\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}, {\\r\\n\\t\\\"name\\\": \\\"评估能力\\\",\\r\\n\\t\\\"id\\\": \\\"b\\\",\\r\\n\\t\\\"type\\\": \\\"Integer\\\"\\r\\n}]\",\n" +
               "            \"code\": \"A1B2\",\n" +
               "            \"operatorAttribute\": \"{\\r\\n\\t \\\"input\\\":{\\r\\n\\t   \\\"Y2\\\":\\\"Integer\\\"\\r\\n\\t },\\r\\n\\t \\\"output\\\":{\\r\\n\\t   \\\"Z1\\\":\\\"Integer\\\"\\r\\n\\r\\n\\t },\\r\\n\\t \\\"attribute\\\":{\\r\\n\\t    \\\"a\\\":\\\"Integer\\\",\\r\\n\\t\\t\\\"b\\\":\\\"Integer\\\"\\r\\n\\t }\\r\\n}\",\n" +
               "            \"calculationRules\": \"{\\r\\n\\t \\\"Z1\\\":{\\r\\n\\t     \\\"rules\\\":\\\"ADD\\\",\\r\\n\\t\\t\\t \\\"variable\\\":{\\\"a\\\",\\\"b\\\",\\\"Y2\\\"}\\r\\n\\t }\\r\\n}\",\n" +
               "            \"delFlag\": \"0\",\n" +
               "            \"createUserId\": null,\n" +
               "            \"updateUserId\": null,\n" +
               "             \"createTime\": null,\"attributeParamJson\":{\"a\":\"1\",\"b\":\"2\",\"c\":\"5\",\"d\":\"5\",\"e\":\"6\",\"f\":\"7\"},\n" +
               "            \"updateTime\": null\n" +
               "        },\n" +
               "        \"zIndex\": 3\n" +
               "    }\n" +
               "]";


        try {
            Object parsedObject = JSON.parse(goodJsonStr);
            String result = JSON.toJSONString(parsedObject);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.parseArray(goodJsonStr);

        Map<String, List<String>> flowMap = new HashMap<>();
        List<ProcessLineDto> processLineList = new ArrayList<>();
        List<ProcessPointDto> processPointDtoList = new ArrayList<>();

        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            if ("edge".equals(jsonObject.getString("shape"))) {
                JSONObject source = jsonObject.getJSONObject("source");//连线起始点
                JSONObject target = jsonObject.getJSONObject("target");//连线结束点
                String sourceId = source.getString("cell");//连线起始点节点Id
                String sourcePort = source.getString("port");//连线起始点参数名称
                String targetId = target.getString("cell");//连线结束点节点Id
                String targetPort = target.getString("port");//连线结束点参数名称
                String id = jsonObject.getString("id");//连线ID

                ProcessLineDto processLine = new ProcessLineDto();
                processLine.setId(id);
                processLine.setSourceId(sourceId);
                processLine.setSourcePort(sourcePort);
                processLine.setTargetPort(targetPort);
                processLine.setTargetId(targetId);
                processLineList.add(processLine);

                //实现将 sourceId和sourcePort和targetId,targetPort 存储到List的对象中
                if (!flowMap.containsKey(sourceId)) {//map代表执行节点
                    flowMap.put(sourceId, new ArrayList<>());
                }
                flowMap.get(sourceId).add(targetId);
            } else {
                JSONObject data = jsonObject.getJSONObject("data");
                String id = jsonObject.getString("id");
                String operatorName = data.getString("operatorName");
                String dbId = data.getString("id");
                String code = data.getString("code");
                String url = data.getString("url");

                JSONObject attributeParamJson = data.getJSONObject("attributeParamJson");
                System.out.println(attributeParamJson);
                Map<String, String> attributeParamMap = new HashMap<>();
                for (String key : attributeParamJson.keySet()) {
                    attributeParamMap.put(key, attributeParamJson.getString(key));
                }

                ProcessPointDto processPointDto = new ProcessPointDto();
                processPointDto.setId(id);
                processPointDto.setAttributeParamJson(attributeParamMap);
                processPointDto.setDbId(dbId);
                processPointDto.setName(operatorName);
                processPointDto.setIsDiscover(false);
                processPointDto.setUrl(url);
                processPointDto.setCode(code);

                JSONObject ports = jsonObject.getJSONObject("ports");
                //ports.getJSONArray("items");

                List<JSONObject> items = ports.getJSONArray("items").toJavaList(JSONObject.class);
                List<ProcessPointItemDto> inputParamList = new ArrayList<>();
                List<ProcessPointItemDto> outputParamList = new ArrayList<>();
                for (JSONObject item : items) {
                    ProcessPointItemDto processPointItemDto = new ProcessPointItemDto();
                    processPointItemDto.setName(item.getString("id"));
                    processPointItemDto.setGroup(item.getString("group"));
                    processPointItemDto.setType(item.getString("type"));
                    if ("left".equals(item.getString("group"))) {
                        inputParamList.add(processPointItemDto);
                    } else if ("right".equals(item.getString("group"))) {
                        outputParamList.add(processPointItemDto);
                    }
                }

                processPointDto.setInputParam(inputParamList);
                processPointDto.setOutputParam(outputParamList);
                processPointDtoList.add(processPointDto);
            }
        }

        // 构建输入参数和输出参数到名称的映射
        Map<String, ProcessPointItemDto> inputParamMap = new HashMap<>();
        Map<String, ProcessPointItemDto> outputParamMap = new HashMap<>();
        for (ProcessPointDto processPointDto : processPointDtoList) {
            for (ProcessPointItemDto inputParam : processPointDto.getInputParam()) {
                inputParamMap.put(inputParam.getName(), inputParam);
            }
            for (ProcessPointItemDto outParam : processPointDto.getOutputParam()) {
                outputParamMap.put(outParam.getName(), outParam);
            }
        }

        // 设置目标端口类型和源端口类型
        for (ProcessLineDto processLineDto : processLineList) {
            ProcessPointItemDto inputParam = inputParamMap.get(processLineDto.getTargetPort());
            if (inputParam != null) {
                processLineDto.setTargetPortType(inputParam.getType());
            }
            ProcessPointItemDto outParam = outputParamMap.get(processLineDto.getSourcePort());
            if (outParam != null) {
                processLineDto.setSourcePortType(outParam.getType());
            }
        }


        // 校验找到节点 processPointDtoList 的对象的 InputParam 不为空且 processLineList 没有这个对象或者这个对象的 targetId 为空的情况
        for (ProcessPointDto dto : processPointDtoList) {
            if (!dto.getInputParam().isEmpty()) {
                boolean hasConnection = false;
                for (ProcessLineDto lineDto : processLineList) {
                    if (lineDto.getTargetId().equals(dto.getId())) {
                        hasConnection = true;
                        break;
                    }
                }
                if (!hasConnection) {
                    throw new RuntimeException("有入参的对象必须要有连线提示：" + dto.getId());
                }
            }
        }


        // 去重并只保留 targetId 和 sourceId
        Map<String, ProcessLineDto> distinctMap = new HashMap<>();
        for (ProcessLineDto line : processLineList) {
            distinctMap.put(line.getTargetId(), line);
        }
        List<ProcessLineDto> distinctList = new ArrayList<>(distinctMap.values());

        // 组装 Map<String, List<ProcessPointDto>>
        Map<String, List<ProcessPointDto>> resultMap = new HashMap<>();
        for (ProcessLineDto line : distinctList) {
            List<ProcessPointDto> pointList = new ArrayList<>();
            for (ProcessPointDto point : processPointDtoList) {
                if (line.getSourceId().equals(point.getId())) {
                    pointList.add(point);
                }
            }
            resultMap.put(line.getTargetId(), pointList);
        }

        // 打印结果
        for (Map.Entry<String, List<ProcessPointDto>> entry : resultMap.entrySet()) {
            System.out.println("TargetId: " + entry.getKey() + ", PointList: " + entry.getValue());
        }

        while (!processPointDtoList.stream().allMatch(obj -> obj.getIsDiscover())) {
            for (ProcessPointDto processPointDto : processPointDtoList) {
                if (processPointDto.getInputParam().isEmpty() && !processPointDto.getIsDiscover()) {
                    processPointDto.setMarkNumber(0);
                    processPointDto.setIsDiscover(true);
                } else if(!processPointDto.getIsDiscover()) {
                    List<Integer> markNumberList = new ArrayList<>();
                    List<ProcessPointDto> pointDtoList = resultMap.get(processPointDto.getId());
                    int count = 0;

                    for (ProcessPointDto pointDto:pointDtoList ) {
                        if(!pointDto.getIsDiscover()){
                          break;
                        }
                        count++;
                        if (count == pointDtoList.size()) {
                            markNumberList.add(pointDto.getMarkNumber() + 1);
                            Integer maxValue = markNumberList.stream()
                                    .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);
                            processPointDto.setMarkNumber(maxValue);
                            processPointDto.setIsDiscover(true);
                        }
                    }
                }
            }
        }










        while (!processPointDtoList.stream().allMatch(obj -> obj.getIsDiscover())) {
            for (ProcessPointDto processPointDto : processPointDtoList) {
                if (processPointDto.getInputParam().isEmpty() && !processPointDto.getIsDiscover()) {
                    processPointDto.setMarkNumber(0);
                    processPointDto.setIsDiscover(true);
                } else if(!processPointDto.getIsDiscover()) {
                    List<Integer> markNumberList = new ArrayList<>();
                    for (ProcessPointItemDto inputParam : processPointDto.getInputParam()) {//两条线还是一条线
                        //有多少输入点 就循环多少次
                            for (ProcessLineDto markedPoint : processLineList) {//有多少以当前的节点作为线就会循环多少次
                                if (inputParam.getName().equals(markedPoint.getTargetPort()) && markedPoint.getTargetId().equals(processPointDto.getId())) {
                                    //每次执行就是 每次的线的描述

                                }
                            }
                    }
                }
            }
        }








        Map<String, Integer> markMap = new HashMap<>();
        for (ProcessPointDto processPointDto : processPointDtoList) {
            markMap.put(processPointDto.getName(), processPointDto.getMarkNumber());

            System.out.println("id :" + processPointDto.getId() + " name :" + processPointDto.getName()
                    + " dbId :" + processPointDto.getDbId() + " markNumber :" + processPointDto.getMarkNumber());

        }
        Collections.sort(processPointDtoList, Comparator.comparingInt(ProcessPointDto::getMarkNumber));

        // 根据 markNumber 分组
        Map<Integer, List<ProcessPointDto>> groupedMap = new HashMap<>();
        for (ProcessPointDto processPointDto : processPointDtoList) {
            if (!groupedMap.containsKey(processPointDto.getMarkNumber())) {
                groupedMap.put(processPointDto.getMarkNumber(), new ArrayList<>());
            }
            groupedMap.get(processPointDto.getMarkNumber()).add(processPointDto);
        }

        // 打印特殊格式的结果
        StringBuffer result = new StringBuffer("THEN(");
        boolean first = true;
        for (Map.Entry<Integer, List<ProcessPointDto>> entry : groupedMap.entrySet()) {
            if (!first) {
                result.append(",");
            }
            result.append("WHEN(");
            boolean innerFirst = true;
            for (ProcessPointDto dto : entry.getValue()) {
                if (!innerFirst) {
                    result.append(",");
                }
                result.append(dto.getCode());
                innerFirst = false;

                //生成脚本
                createJob(dto, processLineList, processPointDtoList);
            }
            result.append(")");
            first = false;
        }
        result.append(")");

        System.out.println(result.toString());//输出规则
        LiteFlowChain liteFlowChain = new LiteFlowChain();
        liteFlowChain.setElData(result.toString());
        liteFlowChain.setId(Long.valueOf(1));
        liteFlowChain.setApplicationName("prod");
        liteFlowChain.setChainName("mainChain");
        liteFlowChain.setChainDesc("业务流程");
        liteFlowChain.setCreateTime(new Date());
        liteFlowChain.setProcessJson(json);
        liteFlowChainMapper.insert(liteFlowChain);
    }

    /**
     * 生成脚本
     * @param processPointDto
     * @param processLineList
     * @param processPointDtoList
     * @return
     */
    private  void createJob(ProcessPointDto processPointDto, List<ProcessLineDto> processLineList, List<ProcessPointDto> processPointDtoList) {
        String id = processPointDto.getDbId();


        List<ProcessPointItemDto> pointDtoInputParamList = processPointDto.getInputParam();
        StringBuffer paramResult = new StringBuffer();
        StringBuffer ctxResult = new StringBuffer();
        boolean second = true;

        Map<String, String> map = processPointDto.getAttributeParamJson();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!second) {
                paramResult.append("&");
            } else {
                second = false;
            }
            paramResult.append(entry.getKey() + "=" + entry.getValue());
        }

        boolean firstIteration = true;

        for (ProcessPointItemDto processPointItemDto : pointDtoInputParamList) {
            String name = processPointItemDto.getName();
            String sourcePortType = processLineList.stream()
                    .filter(processLineDto -> name.equals(processLineDto.getTargetPort()) && processLineDto.getTargetId().equals(processPointDto.getId()))
                    .map(ProcessLineDto::getSourcePortType)
                    .findFirst()
                    .orElse("");

            String sourceName = processLineList.stream()
                    .filter(processLineDto -> name.equals(processLineDto.getTargetPort()) && processLineDto.getTargetId().equals(processPointDto.getId()))
                    .map(ProcessLineDto::getSourcePort)
                    .findFirst()
                    .orElse("");


            ctxResult.append(sourcePortType + "   " + name + " = defaultContext.getData(\"" + sourceName + "\");\n");

            if (firstIteration) {
                paramResult.append("&");
                firstIteration = false;
            } else {
                paramResult.append("\"&");
            }
            paramResult.append(name + "=\"+" + name + "+");
        }

        // 移除末尾的 "&"
        if (paramResult.length() > 0 && CollectionUtil.isNotEmpty(pointDtoInputParamList) && paramResult.charAt(paramResult.length() - 1) == '&') {
            paramResult.setLength(paramResult.length() - 1);
        }
        if (paramResult.length() > 0 && CollectionUtil.isNotEmpty(pointDtoInputParamList)) {
            paramResult = new StringBuffer(paramResult.substring(0, paramResult.length() - 1));
        }

        if (CollectionUtil.isEmpty(pointDtoInputParamList)) {
            paramResult.append("\"");
        }



        /**
         * 输出
         */
        StringBuffer outPutBuffer = new StringBuffer();

        List<ProcessPointItemDto> pointDtoOutputParamList = processPointDto.getOutputParam();
        for (ProcessPointItemDto processPointItemDto : pointDtoOutputParamList) {

            outPutBuffer.append(processPointItemDto.getType() + " " + processPointItemDto.getName() + " =  (("+processPointItemDto.getType()+") dataMap.get(\"" + processPointItemDto.getName() + "\")).intValue(); \n");

            outPutBuffer.append("defaultContext.setData('" + processPointItemDto.getName() + "'," + processPointItemDto.getName() + "); \n");
        }
        //int O1 = ((Number) dataMap.get("O1")).intValue();



        String ad=
                "import com.ruoyi.common.utils.http.HttpUtils\n" +
                "import com.yomahub.liteflow.slot.DefaultContext\n" +
                "import com.fasterxml.jackson.databind.ObjectMapper\n" +
                "import com.fasterxml.jackson.databind.JsonNode;\n" +
                "\n" + ctxResult.toString() +
                "def ip = \"" + processPointDto.getUrl() + "\";\n" +
                "def param=\"" + paramResult.toString() + ";\n" +
                "def rs = HttpUtils.sendGet(ip, param)\n" +
                "\n" +
                "ObjectMapper objectMapper = new ObjectMapper();\n" +
                "// 将 JSON 转换为 Map\n" +
                "Map<String, Object> map = objectMapper.readValue(rs, Map.class);\n" +
                "Map<String, Object> dataMap = (Map<String, Object>) map.get(\"data\");\n" +
                        "    "+ outPutBuffer.toString() +"\n" ;

        System.out.println("=======================================================================");
        LiteFlowScript liteFlowScript = new LiteFlowScript();
        liteFlowScript.setScriptId(processPointDto.getCode());
        liteFlowScript.setApplicationName("prod");
        liteFlowScript.setScriptName(processPointDto.getName());
        liteFlowScript.setScriptData(ad);
        liteFlowScript.setScriptType("script");
        liteFlowScript.setScriptLanguage("groovy");
        liteFlowScript.setCreateTime(new Date());
        liteFlowScriptMapper.insert(liteFlowScript);
        System.out.println(ad);
        System.out.println("=======================================================================");
    }
}

