package cn.dm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.dm.util.CustoContext;
import cn.dm.util.L;

public class DataManager {

    // volatile:禁止指令重排序优化
    private volatile static DataManager mManager = null;
    private SQLiteDatabase db = null;
    private BaseDataHelper dataHelper = null;
    private Context mContext = null;

    private DataManager(Context context) {
        mContext = context;
        try {
            if (dataHelper == null) {
                dataHelper = new BaseDataHelper(mContext, CustoContext.TableName.TABLE_NAME_DB_TEST,
                        CustoContext.version);
            }
        } catch (Exception e) {
            L.e("DataManager happen error!");
        }
    }

    public static DataManager getInstance(Context context) {
        if (mManager == null) {
            synchronized (DataManager.class) {
                if (mManager == null) {
                    mManager = new DataManager(context);
                }
            }
        }
        return mManager;
    }

    private boolean isDBReady() {
        try {
            db = dataHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (db == null) {
            L.e("db init error!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 查询
     */
    public void query() {
        try {
            if (isDBReady()) {
                // 查询哪些指定的列; 如果传入null，表示返回所有列;
                String[] columns = { "_id", "_key", "_totalTimestamp", "_value", "_count", "_label" };
                // 过滤条件；如果传入null，表示不进行过滤，返回所有行；
                String selection = null;
                // 过滤条件中？号对应的值
                String[] selectionArgs = null;

                // 分组条件；如果传入null，表示不分组；
                String groupBy = null;
                // 分组后的过滤条件；传入null，表示不过滤；
                String having = null;

                // 排序条件；传入null，进行默认排序ASC
                String orderBy = null;

                // 分页条件；传入null，不进行分页；
                String limit = null;

                // 更为简易的查询方法
                Cursor cursor = db.query(CustoContext.TableName.TABLE_NAME_DB_TEST, columns, selection, selectionArgs,
                        groupBy, having, orderBy, limit);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int columnIndex = 0;
                        // 取索引
                        columnIndex = cursor.getColumnIndex("_id");
                        // 取值
                        int id = cursor.getInt(columnIndex);

                        columnIndex = cursor.getColumnIndex("_key");
                        String key = cursor.getString(columnIndex);

                        columnIndex = cursor.getColumnIndex("_totaltimestamp");
                        int totaltimestamp = cursor.getInt(columnIndex);

                        columnIndex = cursor.getColumnIndex("_value");
                        int value = cursor.getInt(columnIndex);

                        columnIndex = cursor.getColumnIndex("_count");
                        int count = cursor.getInt(columnIndex);

                        columnIndex = cursor.getColumnIndex("_label");
                        String lable = cursor.getString(columnIndex);

                        Log.i("sanbo", "id" + id + "key:" + key + ", totaltimestamp：" + totaltimestamp + "value:"
                                + value + ", count：" + count + ", lable：" + lable);
                    }
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 根据key修改count
     *
     * @param key
     * @param count
     */
    public void update(String key, int count) {
        try {
            if (isDBReady()) {
                // 注意：null在此处是一个合法的值，会被转化为NULL更新到数据库
                ContentValues values = new ContentValues();
                values.put("_count", count);
                String whereClause = "_key=?";
                String[] whereArgs = { key };

                int affected = db.update(CustoContext.TableName.TABLE_NAME_DB_TEST, values, whereClause, whereArgs);
                L.d("update over. result:" + affected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 删除
     *
     * @param id
     *            主键
     */
    public void del(String key) {
        try {
            if (isDBReady()) {
                // 过滤条件；输入null，则删除所有行；
                String whereClause = "_key=?";
                // 过滤条件对应的参数值；也就是whereClause中？号对应的实际值。
                String[] whereArgs = { key };
                // delete语句会返回执行完删除操作后，所影响的行数
                int affected = db.delete(CustoContext.TableName.TABLE_NAME_DB_TEST, whereClause, whereArgs);
                L.d("delete over. result:" + affected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * delete all
     */
    public void del() {
        try {
            if (isDBReady()) {
                int affected = db.delete(CustoContext.TableName.TABLE_NAME_DB_TEST, null, null);
                L.d("delete over. result:" + affected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 添加数据到table
     *
     * @param key
     * @param totalTimestamp
     * @param value
     * @param count
     * @param label
     */
    public void insert(String key, int totalTimestamp, int value, int count, String label) {
        String s = null;
        if (isDBReady()) {
            try {
                // 如果写null，就无法插入一条空数据（2.3会出异常，4.0之后可以写null）
                String nullColumnHack = null;
                // 实际上是一个Map，K对应着列名，V对应着列值
                ContentValues values = new ContentValues();
                values.put("_key", key);
                values.put("_totalTimestamp", totalTimestamp);
                values.put("_value", value);
                values.put("_count", count);
                if (isLableLegitimate(label)) {
                    values.put("_label", label);
                }
                // 插入成功返回被插入行的ID；失败返回-1
                long result = db.insert(CustoContext.TableName.TABLE_NAME_DB_TEST, nullColumnHack, values);
                L.d("insert over. result:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
            // add(key, totalTimestamp, value, count, label);
        }
    }

    /**
     *
     * @return
     */
    private boolean isLableLegitimate(String label) {
        // TODO Auto-generated method stub
        if (label == null || "".equals(label) || label.length() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void add(String key, int totalTimestamp, int value, int count, String label) {
        String s = null;
        if (isDBReady()) {
            if (label == null || label.length() < 1) {
                s = "insert into data(_key,_totaltimestamp,_value,_count) values(\"" + key + "\"," + totalTimestamp
                        + "," + value + "," + count + ")";
            } else {
                s = "insert into data(_key,_totaltimestamp,_value,_count,_label) values(\"" + key + "\","
                        + totalTimestamp + "," + value + "," + count + ",\"" + label + "\")";
            }
            try {
                db.execSQL(s);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }

        }
    }
}
