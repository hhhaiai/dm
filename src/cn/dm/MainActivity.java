package cn.dm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import cn.dm.db.DataManager;

public class MainActivity extends Activity implements OnClickListener {

    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        Log.e("sanbo", "ttttttt", new Throwable());
    }

    private void initView() {
        this.findViewById(R.id.btnInsert).setOnClickListener(this);
        this.findViewById(R.id.btnDel).setOnClickListener(this);
        this.findViewById(R.id.btnUpdate).setOnClickListener(this);
        this.findViewById(R.id.btnQuery).setOnClickListener(this);
        mDataManager = DataManager.getInstance(this);
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
            int v1 = 10000;
            int c = 9999;
            for (int i = 0; i < 20; i++) {
                v1 /= 5;
                c += 99;
                mDataManager.insert("hello" + i, i * 100, v1, c, (i % 2 == 0) ? "整除了" + i : null);
            }
            break;
        case R.id.btnDel:
            mDataManager.del();
            break;
        case R.id.btnUpdate:
            mDataManager.update("hello10", 0);
            break;
        case R.id.btnQuery:
            mDataManager.query();
            break;

        default:
            break;
        }
    }
}
