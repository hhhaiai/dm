package cn.dm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import cn.dm.db.DBModelUtil;
import cn.dm.db.DataManager;
import cn.dm.model.Model;
import cn.dm.util.CustoContext;

public class MainActivity extends Activity implements OnClickListener {

    private DataManager mDataManager;

    private boolean isBase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        this.findViewById(R.id.btnInsert).setOnClickListener(this);
        this.findViewById(R.id.btnDel).setOnClickListener(this);
        this.findViewById(R.id.btnUpdate).setOnClickListener(this);
        this.findViewById(R.id.btnQuery).setOnClickListener(this);
        if (isBase) {
            mDataManager = DataManager.getInstance(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnInsert:
            insert();
            break;
        case R.id.btnDel:
            del();
            break;
        case R.id.btnUpdate:
            update();
            break;
        case R.id.btnQuery:
            query();
            break;

        default:
            break;
        }
    }

    /************************************************* base operating. setup 0 ************************************************/
    private void query() {
        if (isBase) {
            baseQuery();
        } else {
            modelQuery();
        }
    }

    private void update() {
        if (isBase) {
            baseUpdate();
        } else {
            modelUpdate();
        }
    }

    private void del() {
        if (isBase) {
            baseDel();
        } else {
            modelDel();
        }
    }

    private void insert() {
        if (isBase) {
            baseInsert();
        } else {
            modelInsert();
        }
    }

    /**************************************************** mode to custom db. setup 2 ************************************/
    private void modelInsert() {
        DBModelUtil dbUtil = new DBModelUtil(this, "hello.db", CustoContext.version);

        Model m = new Model("hello_key", 1313, 212, 5, "label");
        dbUtil.modelToDB(m, Model.class);
    }

    private void modelDel() {

    }

    private void modelQuery() {

    }

    private void modelUpdate() {

    }

    /**************************************************** base operating. setup 1 ************************************/
    private void baseQuery() {
        mDataManager.query();
    }

    private void baseUpdate() {
        mDataManager.update("hello10", 0);
    }

    private void baseDel() {
        mDataManager.del();
    }

    private void baseInsert() {
        int v1 = 10000;
        int c = 9999;
        for (int i = 0; i < 20; i++) {
            v1 /= 5;
            c += 99;
            mDataManager.insert("hello" + (i + 1), i * 100, v1, c, (i % 2 == 0) ? "整除了" + i : null);
        }
    }
    /**************************************************************************************************************/
}
