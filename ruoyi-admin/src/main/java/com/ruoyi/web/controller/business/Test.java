package com.ruoyi.web.controller.business;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.system.service.ILiteFlowScriptService;
import com.ruoyi.web.controller.Node.DataTest;
import com.ruoyi.web.controller.Node.GroovyScriptExecutor;
import com.ruoyi.web.controller.Node.ProcessDTO;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.property.LiteflowConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Test implements ApplicationRunner {

    @Resource
    private FlowExecutor flowExecutor;

    @Resource
    private BusinessController businessController;

    @Resource
    private ILiteFlowScriptService liteFlowScriptService;

    @Resource
    private GroovyScriptExecutor groovyScriptExecutor;

    // 用于解析 JSON 字符串的 ObjectMapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testFlowExecution();
//        a();
//        Object aaa = aaa(180L);
//        System.out.println(aaa);
    }

    public Object aaa(Long id){
        JSONArray jsonArray = JSON.parseArray(DataTest.dataTable);
        List<Object> list = jsonArray.stream().filter(item -> JSON.parseObject(JSON.toJSONString(item)).getLong("id").equals(id)).collect(Collectors.toList());
        if (list.size() > 0) {
            String o = list.get(0).toString();
            JSONObject jsonObject = JSON.parseObject(o);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray title = data.getJSONArray("title");
            return title;
        }
        return null;
    }

    public void testFlowExecution() {
//        Object o = groovyScriptExecutor.executeGroovyScript();
//        System.out.println(o);
        businessController.startProcess();
//        flowExecutor.execute2Resp("demoFlow");
//        flowExecutor.execute2Resp("demoFlow2");
    }
    public void a() throws JsonProcessingException {
        String json = getJson7();  //
//        String json = getJson6();  //
//        String json = getJson5();  //
//        String json = getJson4();  //
//        String json = getJson3();  //12 13 45
//        String json = getJson2();   //3-2 3-1
//        String json = getJson();  //123


        // 调用方法解析 JSON 并生成表达式
//        String expression = parseJsonToEl(json);  //then
//        String expression = parseJsonToEl2(json);
//        String expression = parseJsonToEl3(json);  //when
//        String expression = parseJsonToEl4(json);
        String expression = parseJsonToEl5(json);  //自己写
//        String expression = generateThenExpression(json);

        // 输出结果
        System.out.println("表达式为"+expression);
    }


    public static void main(String[] args) {
//        String json = getJson7();  //
        String json = getJson6();  //
        String expression = parseJsonToEl5(json);  //自己写

        System.out.println("表达式为"+expression);
    }



    public static String parseJsonToEl5(String jsonString) {
        StringBuilder expression = new StringBuilder("THEN(");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            List<JsonNode> nodes = new ArrayList<>();
            List<JsonNode> edges = new ArrayList<>();

            // 解析节点和边
            for (JsonNode item : rootNode) {
                String shape = item.get("shape").asText();
                if ("g-dag-node".equals(shape) || "g-dag-judge-node".equals(shape)) {
                    nodes.add(item);
                } else if ("dag-edge".equals(shape)) {
                    edges.add(item);
                }
            }

//            Map <String, String> sourceMap = new HashMap<>();

            //key存的id value存的type
            Map <String, String> nodeMap = new HashMap<>();
            for (JsonNode node : nodes) {
                nodeMap.put(node.get("id").asText(), node.get("data").get("type").asText());
            }

            //source和target的集合
            List<String> sourcesList = new ArrayList<>();
            List<String> targetList = new ArrayList<>();
            Set<String> allNodes = new HashSet<>();

            //循环提取第一个node的id
            for (JsonNode edge : edges) {
//                sourceMap.put(edge.get("source").get("cell").asText(), edge.get("target").get("cell").asText());
//                targetMap.put(edge.get("target").get("cell").asText(), edge.get("source").get("cell").asText());

                String source = edge.get("source").get("cell").asText();
                String target = edge.get("target").get("cell").asText();
                sourcesList.add(source);
                targetList.add(target);
            }
            allNodes.addAll(sourcesList);
            allNodes.addAll(targetList);

            //初始值
            String startNodeCell = "";
            Integer count = 0;
            List<String> ids = new ArrayList<>();
            //如果target里面没有source的值 那么就是入口
            for (String s : sourcesList) {
                if(!targetList.contains(s)){
                    startNodeCell = s;
                    count++;
                    ids.add(s);
                }
            }
//            //获取到起始id
//            String startId = getTypeById(nodes, startNode);

//            expression = generateExpression(startNodeCell,edges,expression,nodeMap,true,ids);
            //根据起始点开始构建表达式
            expression = generateExpression(startNodeCell,edges,startNodeCell,expression,nodeMap,true);
//            for (String node : allNodes) {
//                if (!expression.toString().contains(node)) {
//                    ProcessDTO processDTO = new ProcessDTO();
//                    processDTO.setNodeId(node);
//                    String currentType = nodeMap.get(node);
//                    expression.append(",node(\"" + currentType + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')");
//                }
//            }
            expression.append(")");
            System.out.println("expression+++++++++++++++"+expression);
            boolean validate = LiteFlowChainELBuilder.validate(expression.toString());
            System.out.println(validate);
            if (!validate){
                throw new RuntimeException("el表达式语法错误");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return expression.toString();
    }

    /**
     *
     * @param startNodeCell  开始节点 --> 用来判断是否是开始节点
     * @param edges   节点集合 --> 用来确定是否是串行还是并行
     * @param currentNodeCell  当前节点
     * @param expression  el表达式
     * @param nodeMap  key存的id value存的type --> 用来根据id获取对应的type
     * @return
     */
    private static StringBuilder generateExpression(String startNodeCell,List<JsonNode> edges, String currentNodeCell, StringBuilder expression,Map <String, String> nodeMap, Boolean bool) {

        //获取起始值为该cell的点 并查找有几个  1个则串行 2个以上则并行
        List<String> list = new ArrayList<>();
        String next = "";

        //传参
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setNodeId(currentNodeCell);

        //根据id获取对应的type  type即node1 node2.... 这些节点注册的名称
        String currentType = nodeMap.get(currentNodeCell);
        for (JsonNode edge : edges) {
            //根据当前节点去查询该节点下的target
            if(currentNodeCell.equals(edge.get("source").get("cell").asText())){
                list.add(edge.get("target").get("cell").asText());
            }
        }

        //并行执行后 最后一个直接返回
        if(bool==false){
            if(!list.isEmpty()){
                next = list.get(0);
                return generateExpression(startNodeCell,edges,next,expression,nodeMap,true);
            }else{
                return expression;
            }
        }

        //串行
        if(list.size()<=1){
            //如果当前节点是第一个 那么就不用加THEN
            if(currentNodeCell.equals(startNodeCell)) {
                expression.append("node(\"" + currentType + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')");
            }else {
                expression.append(",THEN(");
                expression.append("node(\"" + currentType + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')"+")");  //THEN(node("A")
            }
            //如果不是最后一个节点 那么就继续获取下一个
            if (!list.isEmpty()) {
                //获取当前节点的下一个节点
                next = list.get(0);
                return generateExpression(startNodeCell,edges,next,expression,nodeMap,true);
            }
            return expression;
            //并行  肯定有两个以上
        }else if(list.size()>1){
            //如果前面有两个括号 则加逗号
            if((expression.length() > 1 && expression.charAt(expression.length() - 1) == ')')){
                expression.append(",");
            }
            expression.append("node(\"" + currentType + "\")"  + ".data('" + JSON.toJSONString(processDTO) + "')");  //THEN(WHEN(node("A")
            expression.append(",WHEN(");
            for (int i = 0; i < list.size(); i++) {
                processDTO.setNodeId(list.get(i));
                //如果是中间的 则加逗号
                if (i != list.size() - 1 && i != 0) {
                    expression.append(",node(\"" + nodeMap.get(list.get(i)) + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')"); //THEN(WHEN(node("A"), node("B")

                //第一个不加逗号，
                }else if(i == 0){
                    expression.append("node(\"" + nodeMap.get(list.get(i)) + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')"); //THEN(WHEN(node("A"), node("B")

                //如果是最后一个 则封闭WHEN的后括号) + THEN的后括号
                }else {
                    expression.append(",node(\"" + nodeMap.get(list.get(i)) + "\")" + ".data('" + JSON.toJSONString(processDTO) + "')"+")");//THEN(WHEN(node("A"), node("B"),...node("end") )
                    next = list.get(i);
                    return generateExpression(startNodeCell,edges,next,expression,nodeMap,false);
                }
            }
        }
        return expression;
    }

    public static String  getJson7(){
        String json = "[{\"id\":\"id-edge-w6s9sv9y\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-7b20203a\",\"port\":\"id-node-7b20203a.data-id-out\"},\"target\":{\"cell\":\"id-node-izmlsbdm\",\"port\":\"id-node-izmlsbdm.field-label-list\"}},{\"id\":\"id-edge-bgw1o31q\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-izmlsbdm\",\"port\":\"id-node-izmlsbdm.list-processing-multi-output\"},\"target\":{\"cell\":\"id-node-9cdmm1lm\",\"port\":\"id-node-9cdmm1lm.single-column-selection\"}},{\"id\":\"id-edge-h0s9ie9n\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-9rzxjx8m\",\"port\":\"id-node-9rzxjx8m.data-id-out\"},\"target\":{\"cell\":\"id-node-9cdmm1lm\",\"port\":\"id-node-9cdmm1lm.single-column-selection\"}},{\"id\":\"id-node-7b20203a\",\"shape\":\"g-dag-node\",\"x\":170,\"y\":80,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id-out\",\"options\":{\"data\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"GN\",\"alias\":\"古尼常数\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZZ\",\"alias\":\"装药质量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"KT\",\"alias\":\"壳体质量\",\"flag\":false,\"inputname\":\"\"}]},\"alias\":\"数据集\",\"description\":\"选择数据集的id\",\"value\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"GN\",\"alias\":\"古尼常数\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZZ\",\"alias\":\"装药质量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"KT\",\"alias\":\"壳体质量\",\"flag\":false,\"inputname\":\"\"}],\"data\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"GN\",\"alias\":\"古尼常数\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZZ\",\"alias\":\"装药质量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"KT\",\"alias\":\"壳体质量\",\"flag\":false,\"inputname\":\"\"}]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集\",\"description\":\"选择数据集的id\",\"value\":\"182\"}],\"artifacts\":[]},\"opoper\":\"dataset\",\"name\":\"数据集\",\"menuId\":3,\"description\":\"数据集\",\"id\":237,\"type\":\"node1\",\"command\":\"[\\\\\"sh\\\\\",\\\\\"-c\\\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-7b20203a.data-id-out\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-9rzxjx8m\",\"shape\":\"g-dag-node\",\"x\":610,\"y\":100,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id-out\",\"options\":{\"data\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZY\",\"alias\":\"炸药量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CB\",\"alias\":\"传播距离\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CY\",\"alias\":\"超压值\",\"flag\":false,\"inputname\":\"\"}]},\"alias\":\"数据集\",\"description\":\"选择数据集的id\",\"value\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZY\",\"alias\":\"炸药量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CB\",\"alias\":\"传播距离\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CY\",\"alias\":\"超压值\",\"flag\":false,\"inputname\":\"\"}],\"data\":[{\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"ZY\",\"alias\":\"炸药量\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CB\",\"alias\":\"传播距离\",\"flag\":false,\"inputname\":\"\"},{\"name\":\"CY\",\"alias\":\"超压值\",\"flag\":false,\"inputname\":\"\"}]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集\",\"description\":\"选择数据集的id\",\"value\":\"180\"}],\"artifacts\":[]},\"opoper\":\"dataset\",\"name\":\"数据集\",\"menuId\":3,\"description\":\"数据集\",\"id\":237,\"type\":\"node1\",\"command\":\"[\\\\\"sh\\\\\",\\\\\"-c\\\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-9rzxjx8m.data-id-out\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-izmlsbdm\",\"shape\":\"g-dag-node\",\"x\":220,\"y\":230,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"SJ\",\"alias\":\"随机值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"checked\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"],\"columnsOptions\":[]},\"type\":\"single\"},\"alias\":\"列选择\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":[\"GN\"],\"data\":[{\"name\":\"GN\",\"alias\":\"古尼常数\",\"flag\":false,\"inputname\":\"\",\"disabled\":true,\"jc\":\"GN\",\"selectVal\":\"\"}]}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"select\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\\\"sh\\\\\",\\\\\"-c\\\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-izmlsbdm.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-izmlsbdm.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-9cdmm1lm\",\"shape\":\"g-dag-node\",\"x\":530,\"y\":370,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"single-column-selection-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据单选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"radio\",\"datatype\":\"column\",\"name\":\"single-column-selection\",\"options\":{},\"alias\":\"单列选择\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"select\",\"name\":\"SingleColumnSelection\",\"menuId\":4,\"description\":\"单列选择\",\"id\":247,\"type\":\"node8\",\"command\":\"[\\\\\"sh\\\\\",\\\\\"-c\\\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-9cdmm1lm.single-column-selection-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-9cdmm1lm.single-column-selection\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]\n";
        return json;
    }

    public static String  getJson6(){
        String json = "[{\"id\":\"id-edge-8c3fo48y\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-4mlrgtsd\",\"port\":\"id-node-4mlrgtsd.list-processing-multi-output\"},\"target\":{\"cell\":\"id-node-ug2xv7uf\",\"port\":\"id-node-ug2xv7uf.list-processing-multi\"}},{\"id\":\"id-edge-xv3hxs0h\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-4mlrgtsd\",\"port\":\"id-node-4mlrgtsd.list-processing-multi-output\"},\"target\":{\"cell\":\"id-node-46gq9w5k\",\"port\":\"id-node-46gq9w5k.hs-op-dvalue\"}},{\"id\":\"id-edge-az46jqip\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-npqr3m3n\",\"port\":\"id-node-npqr3m3n.outputs-dataset\"},\"target\":{\"cell\":\"id-node-4mlrgtsd\",\"port\":\"id-node-4mlrgtsd.field-label-list\"}},{\"id\":\"id-node-npqr3m3n\",\"shape\":\"g-dag-node\",\"x\":-420,\"y\":-220,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"data\":[{\"flag\":false,\"radioVal\":false,\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"HS\",\"alias\":\"毁伤值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"BZ\",\"alias\":\"标准值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"SJ\",\"alias\":\"随机值\",\"inputname\":\"\"}],\"name\":\"outputs-dataset\",\"options\":{\"data\":[{\"flag\":false,\"radioVal\":false,\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"HS\",\"alias\":\"毁伤值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"BZ\",\"alias\":\"标准值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"SJ\",\"alias\":\"随机值\",\"inputname\":\"\"}]},\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[{\"flag\":false,\"radioVal\":false,\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"HS\",\"alias\":\"毁伤值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"BZ\",\"alias\":\"标准值\",\"inputname\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"SJ\",\"alias\":\"随机值\",\"inputname\":\"\"}]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集id\",\"description\":\"选择数据集的id\",\"value\":\"183\"}],\"artifacts\":[]},\"opoper\":\"dataset\",\"name\":\"数据集\",\"menuId\":3,\"description\":\"数据集\",\"id\":237,\"type\":\"node1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-npqr3m3n.outputs-dataset\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-4mlrgtsd\",\"shape\":\"g-dag-node\",\"x\":-420,\"y\":-50,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"SJ\",\"alias\":\"随机值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"checked\",\"data\":[{\"flag\":false,\"radioVal\":false,\"name\":\"HS\",\"alias\":\"毁伤值\",\"jc\":\"HS\",\"disabled\":true,\"inputname\":\"\",\"selectVal\":\"\"},{\"flag\":false,\"radioVal\":false,\"name\":\"SJ\",\"alias\":\"随机值\",\"jc\":\"SJ\",\"disabled\":true,\"inputname\":\"\",\"selectVal\":\"\"}],\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"],\"columnsOptions\":[]},\"type\":\"single\"},\"alias\":\"列选择\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":[\"HS\",\"SJ\"]}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-4mlrgtsd.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-4mlrgtsd.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-ug2xv7uf\",\"shape\":\"g-dag-node\",\"x\":-540,\"y\":110,\"data\":{\"id\":242,\"menuId\":6,\"type\":\"node6\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"MistakePressureRateCalc\",\"inputs\":{\"parameters\":[{\"inputoper\":\"processing\",\"datatype\":\"column\",\"name\":\"list-processing-multi\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"冲击波毁伤误差率算子\",\"description\":\"冲击波毁伤误差率算子\",\"value\":\"\",\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\",\"flag\":true,\"disabled\":false,\"inputname\":\"HS\",\"radioVal\":false}]}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"WCL\",\"alias\":\"误差率\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"description\":\"冲击波毁伤误差率算子\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-ug2xv7uf.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-ug2xv7uf.list-processing-multi\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-46gq9w5k\",\"shape\":\"g-dag-node\",\"x\":-160,\"y\":110,\"data\":{\"id\":241,\"menuId\":6,\"type\":\"node5\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"WavePressureCalc\",\"inputs\":{\"parameters\":[{\"inputoper\":\"radio\",\"datatype\":\"column\",\"name\":\"hs-op-dvalue\",\"options\":{},\"alias\":\"冲击波毁伤误差计算\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"HS\",\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\",\"flag\":false,\"disabled\":true,\"inputname\":\"\",\"radioVal\":false}]}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"CZ\",\"alias\":\"差值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"description\":\"冲击波毁伤算子\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-46gq9w5k.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-46gq9w5k.hs-op-dvalue\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]";
        return json;
    }

    public String  getJson5(){
        String json = "[{\"id\":\"id-edge-03sizi8w\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-b1yc1y5l\",\"port\":\"id-node-b1yc1y5l.list-processing-multi-output\"},\"target\":{\"cell\":\"id-node-h6d7p7hb\",\"port\":\"id-node-h6d7p7hb.control-if\"}},{\"id\":\"id-edge-jahjl14t\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-h6d7p7hb\",\"port\":\"id-node-h6d7p7hb.control-if-out-1\"},\"target\":{\"cell\":\"id-node-st0d0oa4\",\"port\":\"id-node-st0d0oa4.field-label\"}},{\"id\":\"id-edge-on01d32a\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-h6d7p7hb\",\"port\":\"id-node-h6d7p7hb.control-if-out-2\"},\"target\":{\"cell\":\"id-node-0753qfko\",\"port\":\"id-node-0753qfko.list-processing-multi\"}},{\"id\":\"id-edge-70ks316d\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-lp85vjze\",\"port\":\"id-node-lp85vjze.outputs-dataset\"},\"target\":{\"cell\":\"id-node-b1yc1y5l\",\"port\":\"id-node-b1yc1y5l.field-label-list\"}},{\"id\":\"id-node-lp85vjze\",\"shape\":\"g-dag-node\",\"x\":-890,\"y\":-480,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"name\":\"outputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集id\",\"description\":\"选择数据集的id\",\"value\":\"\"}],\"artifacts\":[]},\"opoper\":\"dataset\",\"name\":\"数据集\",\"menuId\":3,\"description\":\"数据集\",\"id\":237,\"type\":\"node1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-lp85vjze.outputs-dataset\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-b1yc1y5l\",\"shape\":\"g-dag-node\",\"x\":-890,\"y\":-310,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"CC\",\"alias\":\"time(timestamp)\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"SJ\",\"alias\":\"随机值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-b1yc1y5l.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-b1yc1y5l.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-st0d0oa4\",\"shape\":\"g-dag-node\",\"x\":-920,\"y\":0,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"CZ\",\"alias\":\"差值\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"WavePressureCalc\",\"menuId\":6,\"description\":\"冲击波毁伤算子\",\"id\":241,\"type\":\"node5\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-st0d0oa4.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-st0d0oa4.field-label\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-0753qfko\",\"shape\":\"g-dag-node\",\"x\":-550,\"y\":30,\"data\":{\"id\":242,\"menuId\":6,\"type\":\"node6\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"MistakePressureRateCalc\",\"inputs\":{\"parameters\":[{\"inputoper\":\"processing\",\"datatype\":\"column\",\"name\":\"list-processing-multi\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"list-processing-multi-output\",\"options\":{\"data\":[{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"HS\",\"alias\":\"毁伤值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"BZ\",\"alias\":\"标准值\",\"value\":\"\"},{\"inputoper\":\"none\",\"datatype\":\"String\",\"name\":\"WCL\",\"alias\":\"误差率\",\"value\":\"\"}],\"type\":\"single\"},\"alias\":\"列表数据多选\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"description\":\"冲击波毁伤误差率算子\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-0753qfko.list-processing-multi-output\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-0753qfko.list-processing-multi\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-h6d7p7hb\",\"shape\":\"g-dag-node\",\"x\":-750,\"y\":-180,\"data\":{\"id\":240,\"menuId\":5,\"type\":\"node3\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"判断控制\",\"inputs\":{\"parameters\":[{\"inputoper\":\"if\",\"datatype\":\"array\",\"name\":\"control-if\",\"options\":{},\"alias\":\"判断控制\",\"description\":\"判断控制\",\"value\":\"\"}],\"artifacts\":[]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"if\",\"datatype\":\"array\",\"name\":\"control-if-out-1\",\"options\":{},\"alias\":\"判断控制\",\"description\":\"判断控制\",\"value\":\"\"},{\"inputoper\":\"if\",\"datatype\":\"array\",\"name\":\"control-if-out-2\",\"options\":{},\"alias\":\"判断控制\",\"description\":\"判断控制\",\"value\":\"\"}]},\"description\":\"控制组件\",\"opoper\":\"if\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-h6d7p7hb.control-if-out-1\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-h6d7p7hb.control-if-out-2\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-h6d7p7hb.control-if\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]";
        return json;
    }


    private String getJson4(){
        String jsonString = "[{\"id\":\"id-edge-uyv6c2u1\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-u1wxx7gf\",\"port\":\"id-node-u1wxx7gf.outputs-dataset\"},\"target\":{\"cell\":\"id-node-bond9slg\",\"port\":\"id-node-bond9slg.field-label-list\"}},{\"id\":\"id-edge-zzvaycq3\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-u1wxx7gf\",\"port\":\"id-node-u1wxx7gf.outputs-dataset\"},\"target\":{\"cell\":\"id-node-0vxw5cj5\",\"port\":\"id-node-0vxw5cj5.field-label-list\"}},{\"id\":\"id-edge-ov4sgm7e\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-bond9slg\",\"port\":\"id-node-bond9slg.field-label\"},\"target\":{\"cell\":\"id-node-9vzack0e\",\"port\":\"id-node-9vzack0e.data-id\"}},{\"id\":\"id-edge-qhup9ibj\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-0vxw5cj5\",\"port\":\"id-node-0vxw5cj5.field-label\"},\"target\":{\"cell\":\"id-node-9vzack0e\",\"port\":\"id-node-9vzack0e.data-id\"}},{\"id\":\"id-node-u1wxx7gf\",\"shape\":\"g-dag-node\",\"x\":270,\"y\":60,\"data\":{\"id\":237,\"menuId\":3,\"type\":\"node1\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"数据集\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集id\",\"description\":\"选择数据集的id\",\"value\":\"\"}],\"artifacts\":[]},\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"name\":\"outputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"description\":\"数据集\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-u1wxx7gf.outputs-dataset\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-u1wxx7gf.data-api\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-u1wxx7gf.data-id\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-bond9slg\",\"shape\":\"g-dag-node\",\"x\":80,\"y\":240,\"data\":{\"id\":238,\"menuId\":4,\"type\":\"node2\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"列选择\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"description\":\"列选择\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-bond9slg.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-bond9slg.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-0vxw5cj5\",\"shape\":\"g-dag-node\",\"x\":510,\"y\":230,\"data\":{\"id\":238,\"menuId\":4,\"type\":\"node2\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"列选择\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"description\":\"列选择\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-0vxw5cj5.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-0vxw5cj5.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-9vzack0e\",\"shape\":\"g-dag-node\",\"x\":320,\"y\":430,\"data\":{\"id\":239,\"menuId\":4,\"type\":\"node3\",\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"数据类型转换\",\"inputs\":{\"parameters\":[{\"inputoper\":\"fileupload\",\"datatype\":\"file\",\"name\":\"data-id\",\"options\":{},\"alias\":\"选择上传的文件\",\"description\":\"选择上传的文件\",\"value\":\"\"}],\"artifacts\":[]},\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/output_file.json\",\"name\":\"output_data\",\"from\":\"str\"}]},\"description\":\"数据类型转换\",\"opoper\":\"default\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-9vzack0e.output_data\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-9vzack0e.data-id\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]";
        return jsonString;
    }



    private String getJson3(){
        String jsonString = "["
                + "{\"shape\":\"g-dag-node\", \"id\":\"node1\", \"data\": {\"type\":\"task1\"}},"
                + "{\"shape\":\"g-dag-node\", \"id\":\"node2\", \"data\": {\"type\":\"task2\"}},"
                + "{\"shape\":\"g-dag-node\", \"id\":\"node3\", \"data\": {\"type\":\"task3\"}},"
                + "{\"shape\":\"g-dag-node\", \"id\":\"node4\", \"data\": {\"type\":\"task4\"}},"
                + "{\"shape\":\"g-dag-node\", \"id\":\"node5\", \"data\": {\"type\":\"task5\"}},"
                + "{\"shape\":\"dag-edge\", \"source\": {\"cell\":\"node1\"}, \"target\": {\"cell\":\"node2\"}},"
                + "{\"shape\":\"dag-edge\", \"source\": {\"cell\":\"node1\"}, \"target\": {\"cell\":\"node3\"}},"
                + "{\"shape\":\"dag-edge\", \"source\": {\"cell\":\"node2\"}, \"target\": {\"cell\":\"node4\"}},"
                + "{\"shape\":\"dag-edge\", \"source\": {\"cell\":\"node3\"}, \"target\": {\"cell\":\"node4\"}},"
                + "{\"shape\":\"dag-edge\", \"source\": {\"cell\":\"node4\"}, \"target\": {\"cell\":\"node5\"}}"
                + "]";
        return jsonString;
    }



    private String getJson2(){
        String json = "[\n" +
                "  {\n" +
                "    \"id\": \"id-edge-2aopvg8j\",\n" +
                "    \"shape\": \"dag-edge\",\n" +
                "    \"source\": {\n" +
                "      \"cell\": \"id-node-zpogbqba\",\n" +
                "      \"port\": \"id-node-zpogbqba.output_data\"\n" +
                "    },\n" +
                "    \"target\": {\n" +
                "      \"cell\": \"id-node-mdblcdfa\",\n" +
                "      \"port\": \"id-node-mdblcdfa.output_data\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"id-edge-6uoko9zl\",\n" +
                "    \"shape\": \"dag-edge\",\n" +
                "    \"source\": {\n" +
                "      \"cell\": \"id-node-zpogbqba\",\n" +
                "      \"port\": \"id-node-zpogbqba.output_data\"\n" +
                "    },\n" +
                "    \"target\": {\n" +
                "      \"cell\": \"id-node-910kydkv\",\n" +
                "      \"port\": \"id-node-910kydkv.output_data\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"id-node-zpogbqba\",\n" +
                "    \"shape\": \"g-dag-node\",\n" +
                "    \"x\": -3196,\n" +
                "    \"y\": -2360,\n" +
                "    \"data\": {\n" +
                "      \"args\": [\n" +
                "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
                "      ],\n" +
                "      \"outputs\": {\n" +
                "        \"artifacts\": [\n" +
                "          {\n" +
                "            \"path\": \"/workspace/output_file.json\",\n" +
                "            \"name\": \"output_data\",\n" +
                "            \"from\": \"str\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
                "      \"inputs\": {\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"inputoper\": \"fileupload\",\n" +
                "            \"datatype\": \"file\",\n" +
                "            \"name\": \"data-id\",\n" +
                "            \"options\": {},\n" +
                "            \"alias\": \"选择上传的文件\",\n" +
                "            \"description\": \"选择上传的文件\",\n" +
                "            \"value\": \"\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"artifacts\": []\n" +
                "      },\n" +
                "      \"name\": \"operator_three\",\n" +
                "      \"menuId\": 2,\n" +
                "      \"description\": \"上传文件\",\n" +
                "      \"id\": 239,\n" +
                "      \"type\": \"node3\",\n" +
                "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
                "      \"status\": \"\"\n" +
                "    },\n" +
                "    \"ports\": [\n" +
                "      {\n" +
                "        \"id\": \"id-node-zpogbqba.output_data\",\n" +
                "        \"group\": \"bottom\",\n" +
                "        \"attrs\": {\n" +
                "          \"circle\": {\n" +
                "            \"r\": 6\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"id-node-910kydkv\",\n" +
                "    \"shape\": \"g-dag-node\",\n" +
                "    \"x\": -3060,\n" +
                "    \"y\": -2150,\n" +
                "    \"data\": {\n" +
                "      \"args\": [\n" +
                "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
                "      ],\n" +
                "      \"outputs\": {\n" +
                "        \"artifacts\": [\n" +
                "          {\n" +
                "            \"path\": \"/workspace/output_file.json\",\n" +
                "            \"name\": \"output_data\",\n" +
                "            \"from\": \"str\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
                "      \"inputs\": {\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"inputoper\": \"fileupload\",\n" +
                "            \"datatype\": \"file\",\n" +
                "            \"name\": \"data-id\",\n" +
                "            \"options\": {},\n" +
                "            \"alias\": \"选择上传的文件\",\n" +
                "            \"description\": \"选择上传的文件\",\n" +
                "            \"value\": \"\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"artifacts\": []\n" +
                "      },\n" +
                "      \"name\": \"operator_two\",\n" +
                "      \"menuId\": 2,\n" +
                "      \"description\": \"上传文件\",\n" +
                "      \"id\": 238,\n" +
                "      \"type\": \"node2\",\n" +
                "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
                "      \"status\": \"\"\n" +
                "    },\n" +
                "    \"ports\": [\n" +
                "      {\n" +
                "        \"id\": \"id-node-910kydkv.output_data\",\n" +
                "        \"group\": \"bottom\",\n" +
                "        \"attrs\": {\n" +
                "          \"circle\": {\n" +
                "            \"r\": 6\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"id-node-mdblcdfa\",\n" +
                "    \"shape\": \"g-dag-node\",\n" +
                "    \"x\": -2680,\n" +
                "    \"y\": -1960,\n" +
                "    \"data\": {\n" +
                "      \"id\": 237,\n" +
                "      \"menuId\": 2,\n" +
                "      \"type\": \"node1\",\n" +
                "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
                "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
                "      \"args\": [\n" +
                "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
                "      ],\n" +
                "      \"name\": \"operator_one\",\n" +
                "      \"inputs\": {\n" +
                "        \"parameters\": [\n" +
                "          {\n" +
                "            \"inputoper\": \"fileupload\",\n" +
                "            \"datatype\": \"file\",\n" +
                "            \"name\": \"data-id\",\n" +
                "            \"options\": {},\n" +
                "            \"alias\": \"选择上传的文件\",\n" +
                "            \"description\": \"选择上传的文件\",\n" +
                "            \"value\": \"\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"artifacts\": []\n" +
                "      },\n" +
                "      \"outputs\": {\n" +
                "        \"artifacts\": [\n" +
                "          {\n" +
                "            \"path\": \"/workspace/output_file.json\",\n" +
                "            \"name\": \"output_data\",\n" +
                "            \"from\": \"str\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"description\": \"上传文件\",\n" +
                "      \"status\": \"\"\n" +
                "    },\n" +
                "    \"ports\": [\n" +
                "      {\n" +
                "        \"id\": \"id-node-mdblcdfa.output_data\",\n" +
                "        \"group\": \"bottom\",\n" +
                "        \"attrs\": {\n" +
                "          \"circle\": {\n" +
                "            \"r\": 6\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
        return json;
    }


    private String getJson(){
            return  "[\n" +
            "  {\n" +
            "    \"id\": \"id-edge-qxznpo27\",\n" +
            "    \"shape\": \"dag-edge\",\n" +
            "    \"source\": {\n" +
            "      \"cell\": \"id-node-wclzkhej\",\n" +
            "      \"port\": \"id-node-wclzkhej.output_data\"\n" +
            "    },\n" +
            "    \"target\": {\n" +
            "      \"cell\": \"id-node-m7dbvw1t\",\n" +
            "      \"port\": \"id-node-m7dbvw1t.output_data\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"id-edge-ow7ljln9\",\n" +
            "    \"shape\": \"dag-edge\",\n" +
            "    \"source\": {\n" +
            "      \"cell\": \"id-node-m7dbvw1t\",\n" +
            "      \"port\": \"id-node-m7dbvw1t.output_data\"\n" +
            "    },\n" +
            "    \"target\": {\n" +
            "      \"cell\": \"id-node-l6tmx1za\",\n" +
            "      \"port\": \"id-node-l6tmx1za.output_data\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"id-node-wclzkhej\",\n" +
            "    \"shape\": \"g-dag-node\",\n" +
            "    \"x\": -2590,\n" +
            "    \"y\": -1910,\n" +
            "    \"data\": {\n" +
            "      \"id\": 237,\n" +
            "      \"menuId\": 2,\n" +
            "      \"type\": \"node1\",\n" +
            "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
            "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
            "      \"args\": [\n" +
            "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
            "      ],\n" +
            "      \"name\": \"operator_one\",\n" +
            "      \"inputs\": {\n" +
            "        \"parameters\": [\n" +
            "          {\n" +
            "            \"inputoper\": \"fileupload\",\n" +
            "            \"datatype\": \"file\",\n" +
            "            \"name\": \"data-id\",\n" +
            "            \"options\": {},\n" +
            "            \"alias\": \"选择上传的文件\",\n" +
            "            \"description\": \"选择上传的文件\",\n" +
            "            \"value\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"artifacts\": []\n" +
            "      },\n" +
            "      \"outputs\": {\n" +
            "        \"artifacts\": [\n" +
            "          {\n" +
            "            \"path\": \"/workspace/output_file.json\",\n" +
            "            \"name\": \"output_data\",\n" +
            "            \"from\": \"str\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"description\": \"上传文件\",\n" +
            "      \"status\": \"\"\n" +
            "    },\n" +
            "    \"ports\": [\n" +
            "      {\n" +
            "        \"id\": \"id-node-wclzkhej.output_data\",\n" +
            "        \"group\": \"bottom\",\n" +
            "        \"attrs\": {\n" +
            "          \"circle\": {\n" +
            "            \"r\": 6\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"id-node-m7dbvw1t\",\n" +
            "    \"shape\": \"g-dag-node\",\n" +
            "    \"x\": -2440,\n" +
            "    \"y\": -1770,\n" +
            "    \"data\": {\n" +
            "      \"id\": 238,\n" +
            "      \"menuId\": 2,\n" +
            "      \"type\": \"node2\",\n" +
            "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
            "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
            "      \"args\": [\n" +
            "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
            "      ],\n" +
            "      \"name\": \"operator_two\",\n" +
            "      \"inputs\": {\n" +
            "        \"parameters\": [\n" +
            "          {\n" +
            "            \"inputoper\": \"fileupload\",\n" +
            "            \"datatype\": \"file\",\n" +
            "            \"name\": \"data-id\",\n" +
            "            \"options\": {},\n" +
            "            \"alias\": \"选择上传的文件\",\n" +
            "            \"description\": \"选择上传的文件\",\n" +
            "            \"value\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"artifacts\": []\n" +
            "      },\n" +
            "      \"outputs\": {\n" +
            "        \"artifacts\": [\n" +
            "          {\n" +
            "            \"path\": \"/workspace/output_file.json\",\n" +
            "            \"name\": \"output_data\",\n" +
            "            \"from\": \"str\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"description\": \"上传文件\",\n" +
            "      \"status\": \"\"\n" +
            "    },\n" +
            "    \"ports\": [\n" +
            "      {\n" +
            "        \"id\": \"id-node-m7dbvw1t.output_data\",\n" +
            "        \"group\": \"bottom\",\n" +
            "        \"attrs\": {\n" +
            "          \"circle\": {\n" +
            "            \"r\": 6\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"id-node-l6tmx1za\",\n" +
            "    \"shape\": \"g-dag-node\",\n" +
            "    \"x\": -2210,\n" +
            "    \"y\": -1660,\n" +
            "    \"data\": {\n" +
            "      \"id\": 239,\n" +
            "      \"menuId\": 2,\n" +
            "      \"type\": \"node3\",\n" +
            "      \"image\": \"dockerhub.kubekey.local/op/upload-file-one:v1\",\n" +
            "      \"command\": \"[\\\"sh\\\",\\\"-c\\\"]\",\n" +
            "      \"args\": [\n" +
            "        \"python3 download.py {{inputs.parameters.data-id}}\"\n" +
            "      ],\n" +
            "      \"name\": \"operator_three\",\n" +
            "      \"inputs\": {\n" +
            "        \"parameters\": [\n" +
            "          {\n" +
            "            \"inputoper\": \"fileupload\",\n" +
            "            \"datatype\": \"file\",\n" +
            "            \"name\": \"data-id\",\n" +
            "            \"options\": {},\n" +
            "            \"alias\": \"选择上传的文件\",\n" +
            "            \"description\": \"选择上传的文件\",\n" +
            "            \"value\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"artifacts\": []\n" +
            "      },\n" +
            "      \"outputs\": {\n" +
            "        \"artifacts\": [\n" +
            "          {\n" +
            "            \"path\": \"/workspace/output_file.json\",\n" +
            "            \"name\": \"output_data\",\n" +
            "            \"from\": \"str\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"description\": \"上传文件\",\n" +
            "      \"status\": \"\"\n" +
            "    },\n" +
            "    \"ports\": [\n" +
            "      {\n" +
            "        \"id\": \"id-node-l6tmx1za.output_data\",\n" +
            "        \"group\": \"bottom\",\n" +
            "        \"attrs\": {\n" +
            "          \"circle\": {\n" +
            "            \"r\": 6\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    }


}