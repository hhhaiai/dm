package cn.dm.db;

import android.content.Context;
import cn.dm.model.Model;
import cn.dm.util.CustoContext;
import cn.dm.util.L;
import cn.dm.util.Utils;

public class DBModelUtil {

    private BaseDataHelper dataHelper = null;

    /**
     * 如果不设置，默认名字为 CustomContext中的DBName，版本也是1
     *
     * @param con
     */
    private DBModelUtil(Context con) {
        this(con, CustoContext.DBName.DB_NAME, CustoContext.version);
    }

    /**
     *
     * @param context
     * @param dbName
     *            数据库名字
     * @param version
     *            数据库版本，大于等于1，如果小于1则取1
     */
    public DBModelUtil(Context context, String dbName, int version) {
        if (Utils.isEmpty(dbName)) {
            dbName = CustoContext.DBName.DB_NAME;
        }
        if (version <= 1) {
            version = CustoContext.version;
        }

        try {
            dataHelper = new BaseDataHelper(context, dbName, version);
        } catch (Throwable e) {
            L.e(e);
        }
    }

    public Model fromDB(String dbName) {

        return null;
    }

    public boolean modelToDB(Model m, Class c) {

        return false;
    }

}
