package cn.dm.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.dm.util.L;

public class BaseDataHelper extends SQLiteOpenHelper {

    private String sqlCreateData = null;

    /**
     * @param context
     *            上下文
     * @param dbName
     *            数据库名字
     * @throws Exception
     */
    public BaseDataHelper(Context context, String dbName, int version) throws Exception {
        this(context, dbName, null, version, null);
    }

    /**
     *
     * @param context
     * @param dbName
     * @param factory
     * @param version
     * @param errorHandler
     * @throws Exception
     */
    public BaseDataHelper(Context context, String dbName, CursorFactory factory, int version,
            DatabaseErrorHandler errorHandler) throws Exception {

        super(context, dbName, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqlCreateData = "create table data(_id integer  primary key autoincrement,_key text(1000) not null,"
                + "_totaltimestamp integer,_value integer,_count integer,_label text(1000))";
        try {
            if (sqlCreateData != null) {
                db.execSQL(sqlCreateData);
                L.d("create table sql exec  over");
            }
        } catch (Throwable e) {
            L.e("sql exec happen error!");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
