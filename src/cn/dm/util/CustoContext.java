package cn.dm.util;

public class CustoContext {

    public static final int version = 1;
    public static final int DB_TYPE_BASE = 1000;

    /**
     *
     * @Copyright © 2015 sanbo Inc.. All rights reserved.
     * @Title: CustoContext.java
     * @Description: 表名字类，提供给外部访问
     * @Version: 1.0
     * @Create: 2015年6月18日 下午4:19:38
     * @Author: sanbo
     *
     */
    public static class DBName {
        public static String DB_NAME = "mydb.db";
        public static String DB_INDEX = "index.db";

    }

    /**
     *
     * @Copyright © 2015 sanbo Inc.. All rights reserved.
     * @Title: CustoContext.java
     * @Description: 表名字，多个db中可共用，不建议共用
     * @Version: 1.0
     * @Create: 2015年6月18日 下午4:19:33
     * @Author: sanbo
     *
     */
    public static class TableName {
        public static final String TABLE_NAME_DB_TEST = "data";
        public static final String TABLE_NAME_DB_INDEX = "index";
    }

    /**
     *
     * @Copyright © 2015 sanbo Inc.. All rights reserved.
     * @Title: CustoContext.java
     * @Description: 数据表的类型，可为索引表，可为数据表
     * @Version: 1.0
     * @Create: 2015年6月18日 下午4:21:54
     * @Author: sanbo
     *
     */
    public static class TableType {
        public static final int TABLE_TYPE_DEFAU = DB_TYPE_BASE;
        public static final int TABLE_TYPE_DB = DB_TYPE_BASE + 1;
        public static final int TABLE_TYPE_INDEX = DB_TYPE_BASE + 2;
    }

    /**
     *
     * @Copyright © 2015 sanbo Inc.. All rights reserved.
     * @Title: CustoContext.java
     * @Description: db的类型，可以是备份DB
     * @Version: 1.0
     * @Create: 2015年6月18日 下午4:19:29
     * @Author: sanbo
     *
     */
    public static class DBType {
        public static final int DB_TYPE_DEFAU = DB_TYPE_BASE;
        public static final int DB_TYPE_DB = DB_TYPE_BASE + 1;
        public static final int DB_TYPE_INDEX = DB_TYPE_BASE + 2;
    }

}
