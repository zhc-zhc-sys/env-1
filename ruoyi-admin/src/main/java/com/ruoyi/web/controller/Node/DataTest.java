package com.ruoyi.web.controller.Node;

public class DataTest {

    /**
     * 模拟数据集列表
     */
    public static String datasetList = "[{\"id\":\"180\",\"mc\":\"计算峰值超压数据集\",\"cqid\":null,\"xgr\":\"1\",\"cjr\":\"1\",\"bbid\":null,\"mxid\":\"2\",\"mxmc\":null,\"mxidjh\":\"计算峰值超压\",\"ly\":\"计算峰值超压\",\"ms\":\"计算峰值超压第一版数据\",\"sc\":\"false\",\"rate\":0,\"sjjdx\":2,\"sjjlx\":1,\"stm\":\"SJJ_183\",\"sjjh\":\"416,454\",\"bqidjh\":\"23\",\"count\":null,\"sjjbb\":3,\"fid\":\"178\"},{\"id\":\"181\",\"mc\":\"计算比冲量数据集\",\"cqid\":null,\"xgr\":\"1\",\"cjr\":\"1\",\"bbid\":null,\"mxid\":\"2\",\"mxmc\":null,\"mxidjh\":\"计算比冲量\",\"ly\":\"计算比冲量\",\"ms\":\"计算比冲量第一版数据\",\"sc\":\"false\",\"rate\":0,\"sjjdx\":2,\"sjjlx\":1,\"stm\":\"SJJ_183\",\"sjjh\":\"416,454\",\"bqidjh\":\"23\",\"count\":null,\"sjjbb\":3,\"fid\":\"178\"},{\"id\":\"182\",\"mc\":\"计算破片初速度数据集\",\"cqid\":null,\"xgr\":\"1\",\"cjr\":\"1\",\"bbid\":null,\"mxid\":\"2\",\"mxmc\":null,\"mxidjh\":\"计算破片初速度\",\"ly\":\"计算破片初速度\",\"ms\":\"计算破片初速度第一版数据\",\"sc\":\"false\",\"rate\":0,\"sjjdx\":2,\"sjjlx\":1,\"stm\":\"SJJ_183\",\"sjjh\":\"416,454\",\"bqidjh\":\"23\",\"count\":null,\"sjjbb\":3,\"fid\":\"178\"},{\"id\":\"183\",\"mc\":\"计算破片剩余速度数据集\",\"cqid\":null,\"xgr\":\"1\",\"cjr\":\"1\",\"bbid\":null,\"mxid\":\"2\",\"mxmc\":null,\"mxidjh\":\"计算破片剩余速度\",\"ly\":\"计算破片剩余速度\",\"ms\":\"计算破片剩余速度第一版数据\",\"sc\":\"false\",\"rate\":0,\"sjjdx\":2,\"sjjlx\":1,\"stm\":\"SJJ_183\",\"sjjh\":\"416,454\",\"bqidjh\":\"23\",\"count\":null,\"sjjbb\":3,\"fid\":\"178\"}]\n";
    /**
     * 模拟 数据集表格 - 表格数据
     */
    public static String dataTable = "[{\"id\":180,\"data\":{\"title\":[{\"zwm\":\"time(timestamp)\",\"jc\":\"CC\"},{\"zwm\":\"炸药量\",\"jc\":\"ZY\"},{\"zwm\":\"传播距离\",\"jc\":\"CB\"},{\"zwm\":\"超压值\",\"jc\":\"CY\"}],\"body\":[{\"CC\":\"2020-10-10 12:10:10\",\"ZY\":5.1234,\"CB\":150,\"CY\":1.35},{\"CC\":\"2020-10-10 12:10:11\",\"ZY\":5.4567,\"CB\":152.3,\"CY\":1.37},{\"CC\":\"2020-10-10 12:10:12\",\"ZY\":5.789,\"CB\":154.6,\"CY\":1.4},{\"CC\":\"2020-10-10 12:10:13\",\"ZY\":5.2234,\"CB\":153.2,\"CY\":1.42},{\"CC\":\"2020-10-10 12:10:14\",\"ZY\":5.6578,\"CB\":155.1,\"CY\":1.38},{\"CC\":\"2020-10-10 12:10:15\",\"ZY\":5.789,\"CB\":156.8,\"CY\":1.41},{\"CC\":\"2020-10-10 12:10:16\",\"ZY\":5.3456,\"CB\":158,\"CY\":1.36},{\"CC\":\"2020-10-10 12:10:17\",\"ZY\":5.789,\"CB\":160.2,\"CY\":1.39},{\"CC\":\"2020-10-10 12:10:18\",\"ZY\":5.4567,\"CB\":159.4,\"CY\":1.37},{\"CC\":\"2020-10-10 12:10:19\",\"ZY\":5.6789,\"CB\":161.5,\"CY\":1.43}]}},{\"id\":181,\"data\":{\"title\":[{\"zwm\":\"time(timestamp)\",\"jc\":\"CC\"},{\"zwm\":\"炸药量\",\"jc\":\"ZY\"},{\"zwm\":\"传播距离\",\"jc\":\"CB\"},{\"zwm\":\"比冲量\",\"jc\":\"BC\"}],\"body\":[{\"CC\":\"2020-10-10 12:10:10\",\"ZY\":4.9876,\"CB\":142.3,\"BC\":350},{\"CC\":\"2020-10-10 12:10:11\",\"ZY\":5.2345,\"CB\":145.2,\"BC\":352.5},{\"CC\":\"2020-10-10 12:10:12\",\"ZY\":5.5678,\"CB\":148.1,\"BC\":355},{\"CC\":\"2020-10-10 12:10:13\",\"ZY\":5.1234,\"CB\":146.3,\"BC\":357.5},{\"CC\":\"2020-10-10 12:10:14\",\"ZY\":5.8765,\"CB\":149,\"BC\":359},{\"CC\":\"2020-10-10 12:10:15\",\"ZY\":5.2345,\"CB\":151.2,\"BC\":360.5},{\"CC\":\"2020-10-10 12:10:16\",\"ZY\":5.3456,\"CB\":153.5,\"BC\":362},{\"CC\":\"2020-10-10 12:10:17\",\"ZY\":5.789,\"CB\":155,\"BC\":364},{\"CC\":\"2020-10-10 12:10:18\",\"ZY\":5.1234,\"CB\":157.1,\"BC\":366},{\"CC\":\"2020-10-10 12:10:19\",\"ZY\":5.6789,\"CB\":158.9,\"BC\":368.5}]}},{\"id\":182,\"data\":{\"title\":[{\"zwm\":\"time(timestamp)\",\"jc\":\"CC\"},{\"zwm\":\"古尼常数\",\"jc\":\"GN\"},{\"zwm\":\"装药质量\",\"jc\":\"ZZ\"},{\"zwm\":\"壳体质量\",\"jc\":\"KT\"}],\"body\":[{\"CC\":\"2020-10-10 12:10:10\",\"GN\":0.85,\"ZZ\":1.25,\"KT\":3.45},{\"CC\":\"2020-10-10 12:10:11\",\"GN\":0.87,\"ZZ\":1.3,\"KT\":3.5},{\"CC\":\"2020-10-10 12:10:12\",\"GN\":0.89,\"ZZ\":1.35,\"KT\":3.55},{\"CC\":\"2020-10-10 12:10:13\",\"GN\":0.88,\"ZZ\":1.28,\"KT\":3.6},{\"CC\":\"2020-10-10 12:10:14\",\"GN\":0.86,\"ZZ\":1.4,\"KT\":3.62},{\"CC\":\"2020-10-10 12:10:15\",\"GN\":0.83,\"ZZ\":1.45,\"KT\":3.68},{\"CC\":\"2020-10-10 12:10:16\",\"GN\":0.84,\"ZZ\":1.5,\"KT\":3.7},{\"CC\":\"2020-10-10 12:10:17\",\"GN\":0.87,\"ZZ\":1.55,\"KT\":3.75},{\"CC\":\"2020-10-10 12:10:18\",\"GN\":0.9,\"ZZ\":1.6,\"KT\":3.8},{\"CC\":\"2020-10-10 12:10:19\",\"GN\":0.92,\"ZZ\":1.65,\"KT\":3.85}]}},{\"id\":183,\"data\":{\"title\":[{\"zwm\":\"time(timestamp)\",\"jc\":\"CC\"},{\"zwm\":\"破片初速\",\"jc\":\"PC\"},{\"zwm\":\"飞行距离\",\"jc\":\"FX\"},{\"zwm\":\"破片质量\",\"jc\":\"PM\"},{\"zwm\":\"符合系数\",\"jc\":\"FH\"}],\"body\":[{\"CC\":\"2020-10-10 12:10:10\",\"PC\":600.5,\"FX\":800,\"PM\":25,\"FH\":0.98},{\"CC\":\"2020-10-10 12:10:11\",\"PC\":602.1,\"FX\":805,\"PM\":24.8,\"FH\":0.97},{\"CC\":\"2020-10-10 12:10:12\",\"PC\":603.3,\"FX\":810.5,\"PM\":25.5,\"FH\":0.96},{\"CC\":\"2020-10-10 12:10:13\",\"PC\":605,\"FX\":815,\"PM\":26,\"FH\":0.95},{\"CC\":\"2020-10-10 12:10:14\",\"PC\":607.2,\"FX\":820,\"PM\":26.5,\"FH\":0.94},{\"CC\":\"2020-10-10 12:10:15\",\"PC\":608.5,\"FX\":825,\"PM\":27,\"FH\":0.93},{\"CC\":\"2020-10-10 12:10:16\",\"PC\":610,\"FX\":830,\"PM\":27.5,\"FH\":0.92},{\"CC\":\"2020-10-10 12:10:17\",\"PC\":611.3,\"FX\":835,\"PM\":28,\"FH\":0.91},{\"CC\":\"2020-10-10 12:10:18\",\"PC\":613,\"FX\":840,\"PM\":28.5,\"FH\":0.9},{\"CC\":\"2020-10-10 12:10:19\",\"PC\":614.2,\"FX\":845,\"PM\":29,\"FH\":0.89}]}}]";

    /**
     * 设计好的流程 - 数据
     */
    public static String workflowData = "[{\"shape\":\"dag-edge\",\"id\":\"id-edge-2y6aj2tj\",\"source\":{\"port\":\"id-node-p0ivghgy.outputs-dataset\",\"cell\":\"id-node-p0ivghgy\"},\"target\":{\"port\":\"id-node-kd79yy0a.field-label-list\",\"cell\":\"id-node-kd79yy0a\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-ynup0fel\",\"source\":{\"port\":\"id-node-p0ivghgy.outputs-dataset\",\"cell\":\"id-node-p0ivghgy\"},\"target\":{\"port\":\"id-node-a4u4coda.field-label-list\",\"cell\":\"id-node-a4u4coda\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-66svp2is\",\"source\":{\"port\":\"id-node-p0ivghgy.outputs-dataset\",\"cell\":\"id-node-p0ivghgy\"},\"target\":{\"port\":\"id-node-31rr464s.field-label-list\",\"cell\":\"id-node-31rr464s\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-9fez5nx4\",\"source\":{\"port\":\"id-node-a4u4coda.field-label\",\"cell\":\"id-node-a4u4coda\"},\"target\":{\"port\":\"id-node-epk225sk.field-label\",\"cell\":\"id-node-epk225sk\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-4l5wsicb\",\"source\":{\"port\":\"id-node-31rr464s.field-label\",\"cell\":\"id-node-31rr464s\"},\"target\":{\"port\":\"id-node-epk225sk.field-label\",\"cell\":\"id-node-epk225sk\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-hk7r7qv4\",\"source\":{\"port\":\"id-node-kd79yy0a.field-label\",\"cell\":\"id-node-kd79yy0a\"},\"target\":{\"port\":\"id-node-8k6d2wlp.field-label\",\"cell\":\"id-node-8k6d2wlp\"}},{\"shape\":\"dag-edge\",\"id\":\"id-edge-mrfgoh2p\",\"source\":{\"port\":\"id-node-epk225sk.outputs-model\",\"cell\":\"id-node-epk225sk\"},\"target\":{\"port\":\"id-node-8k6d2wlp.field-label\",\"cell\":\"id-node-8k6d2wlp\"}},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"name\":\"outputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集id\",\"description\":\"选择数据集的id\",\"value\":\"183\"}],\"artifacts\":[]},\"opoper\":\"default\",\"name\":\"数据集\",\"menuId\":3,\"description\":\"数据集\",\"id\":237,\"type\":\"node1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":-10,\"y\":-40,\"id\":\"id-node-p0ivghgy\",\"ports\":[{\"id\":\"id-node-p0ivghgy.outputs-dataset\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-p0ivghgy.data-api\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-p0ivghgy.data-id\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"data\":[{\"label\":\"毁伤值\",\"value\":\"HS\"},{\"label\":\"标准值\",\"value\":\"BZ\"},{\"label\":\"随机值\",\"value\":\"SJ\"}],\"name\":\"outputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[\"HS\",\"BZ\",\"SJ\"]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":-220,\"y\":140,\"id\":\"id-node-kd79yy0a\",\"ports\":[{\"id\":\"id-node-kd79yy0a.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-kd79yy0a.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":160,\"y\":130,\"id\":\"id-node-a4u4coda\",\"ports\":[{\"id\":\"id-node-a4u4coda.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-a4u4coda.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":515,\"y\":130,\"id\":\"id-node-31rr464s\",\"ports\":[{\"id\":\"id-node-31rr464s.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-31rr464s.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.joblib\",\"name\":\"outputs-model\",\"alias\":\"模型\",\"from\":\"\",\"type\":\"model\",\"value\":[]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"WavePressureCalc\",\"menuId\":6,\"description\":\"冲击波毁伤算子\",\"id\":241,\"type\":\"node5\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":340,\"y\":400,\"id\":\"id-node-epk225sk\",\"ports\":[{\"id\":\"id-node-epk225sk.outputs-model\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-epk225sk.field-label\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"shape\":\"g-dag-node\",\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.joblib\",\"name\":\"outputs-model\",\"alias\":\"模型\",\"from\":\"\",\"type\":\"model\",\"value\":[]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"MistakePressureRateCalc\",\"menuId\":6,\"description\":\"冲击波毁伤误差率算子\",\"id\":242,\"type\":\"node6\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"x\":-70,\"y\":550,\"id\":\"id-node-8k6d2wlp\",\"ports\":[{\"id\":\"id-node-8k6d2wlp.outputs-model\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-8k6d2wlp.field-label\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]";
//    public static String workflowData = "[{\"id\":\"id-edge-u82itsw2\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-j0ve5dch\",\"port\":\"id-node-j0ve5dch.outputs-dataset\"},\"target\":{\"cell\":\"id-node-33zvv9q9\",\"port\":\"id-node-33zvv9q9.field-label-list\"}},{\"id\":\"id-edge-kcp9gr7b\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-j0ve5dch\",\"port\":\"id-node-j0ve5dch.outputs-dataset\"},\"target\":{\"cell\":\"id-node-4101o426\",\"port\":\"id-node-4101o426.field-label-list\"}},{\"id\":\"id-node-j0ve5dch\",\"shape\":\"g-dag-node\",\"x\":250,\"y\":-70,\"data\":{\"outputs\":{\"artifacts\":[{\"path\":\"/workspace/outputs/data.csv\",\"name\":\"outputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"hidden\",\"datatype\":\"str\",\"name\":\"data-api\",\"options\":{},\"alias\":\"数据集服务api\",\"description\":\"数据集服务的api接口\",\"value\":\"http://223.0.15.68:30061\"},{\"inputoper\":\"selecttable\",\"datatype\":\"str\",\"name\":\"data-id\",\"options\":{},\"alias\":\"数据集id\",\"description\":\"选择数据集的id\",\"value\":\"183\"}],\"artifacts\":[]},\"opoper\":\"default\",\"description\":\"数据集\",\"type\":\"node1\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"name\":\"数据集\",\"menuId\":3,\"id\":237,\"config\":[{\"name\":\"loop\",\"value\":\"123456789\"},{\"name\":\"if\",\"value\":{}}],\"status\":\"\"},\"ports\":[{\"id\":\"id-node-j0ve5dch.outputs-dataset\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-j0ve5dch.data-api\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-j0ve5dch.data-id\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-4101o426\",\"shape\":\"g-dag-node\",\"x\":84,\"y\":120,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"data\":[{\"label\":\"毁伤值\",\"value\":\"HS\"},{\"label\":\"标准值\",\"value\":\"BZ\"}],\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":[\"HS\",\"BZ\"]}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-4101o426.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-4101o426.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]},{\"id\":\"id-node-33zvv9q9\",\"shape\":\"g-dag-node\",\"x\":460,\"y\":120,\"data\":{\"args\":[\"python3 download.py {{inputs.parameters.data-id}}\"],\"outputs\":{\"artifacts\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"选择标签列\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}]},\"image\":\"dockerhub.kubekey.local/op/upload-file-one:v1\",\"inputs\":{\"parameters\":[{\"inputoper\":\"\",\"datatype\":\"column\",\"name\":\"field-label-list\",\"options\":{\"data\":{\"targets\":[\"inputs-dataset\"]},\"type\":\"single\"},\"alias\":\"标签列展示\",\"description\":\"预测目标列,要求不是数组类型\",\"value\":\"\"}],\"artifacts\":[{\"path\":\"/workspace/inputs/data.csv\",\"name\":\"inputs-dataset\",\"alias\":\"数据集\",\"from\":\"\",\"type\":\"data\",\"value\":[]}]},\"opoper\":\"default\",\"name\":\"列选择\",\"menuId\":4,\"description\":\"列选择\",\"id\":238,\"type\":\"node2\",\"command\":\"[\\\"sh\\\",\\\"-c\\\"]\",\"status\":\"\"},\"ports\":[{\"id\":\"id-node-33zvv9q9.field-label\",\"group\":\"bottom\",\"attrs\":{\"circle\":{\"r\":6}}},{\"id\":\"id-node-33zvv9q9.field-label-list\",\"group\":\"top\",\"attrs\":{\"circle\":{\"r\":6}}}]}]";
//    public static String workflowData = "[{\"id\":\"id-edge-qiqxgyji\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-5zmv1794\",\"port\":\"id-node-5zmv1794.outputs-dataset\"},\"target\":{\"cell\":\"id-node-713r1p8t\",\"port\":\"id-node-713r1p8t.field-label-list\"}},{\"id\":\"id-edge-b3d0wn7s\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-5w65v7yh\",\"port\":\"id-node-5w65v7yh.field-label\"},\"target\":{\"cell\":\"id-node-4kmwbxks\",\"port\":\"id-node-4kmwbxks.field-label\"}},{\"id\":\"id-edge-ysb7mqfa\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-713r1p8t\",\"port\":\"id-node-713r1p8t.field-label\"},\"target\":{\"cell\":\"id-node-4kmwbxks\",\"port\":\"id-node-4kmwbxks.field-label\"}},{\"id\":\"id-edge-1c4ypygg\",\"shape\":\"dag-edge\",\"source\":{\"cell\":\"id-node-5zmv1794\",\"port\":\"id-node-5zmv1794.outputs-dataset\"},\"target\":{\"cell\":\"id-node-5w65v7yh\",\"port\":\"id-node-5w65v7yh.field-label-list\"}}]";
}
