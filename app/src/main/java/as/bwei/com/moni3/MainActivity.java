package as.bwei.com.moni3;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import as.bwei.com.moni3.adapter.MainAdapter;
import as.bwei.com.moni3.bean.MainBean;
import as.bwei.com.moni3.contract.MainContract;
import as.bwei.com.moni3.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.MainView {

    //QQ
    private UMShareAPI umShareAPI;
    private ImageView iv_head;
    //展示
    private static final int CODE_REQUEST_LOGIN = 1000;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MainPresenter presenter;
    private List<MainBean.Pois> datas = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //QQ
        umShareAPI = UMShareAPI.get(this);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UMAuthListener authListener = new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {

                    }

                    /**
                     * @desc 授权成功的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param data 用户资料返回
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                        Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "授权成功后的回调数据，用户信息"+data, Toast.LENGTH_LONG).show();
                        String iconurl = data.get("iconurl");
                        Glide.with(MainActivity.this).load(iconurl).into(iv_head);

                    }

                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                };
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
            }


        });

        //展示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter = new MainPresenter();
        presenter.attach(this);
        presenter.loadData("https://restapi.amap.com/v3/place/around?key=d78f39012867929dc6ad174dd498f51f&location=116.473168,39.993015&keywords=%E7%BE%8E%E9%A3%9F&types=&radius=1000&offset=20&page=1&extensions=all");
    }
    //QQ
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showList(String result) {
        Log.i(TAG, "result:" + result);
        Gson gson = new Gson();
        MainBean mainBean = gson.fromJson(result, MainBean.class);
        datas.addAll(mainBean.getPois());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
