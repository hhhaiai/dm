# dm

android平台数据库db和Model转换的工具.类似功能有gson/JSONModel等

#实现步骤
0. 首先实现数据库的增删改查
1. 对象和固定sql转换
2. 通过泛型来实现活动sql字段组成，此部分包括容错
3. 通过反射来实现不同类型名称的映射
