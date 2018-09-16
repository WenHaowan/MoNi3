package as.bwei.com.moni3.model;

import as.bwei.com.moni3.contract.MainContract;
import as.bwei.com.moni3.utils.OKHttpUtils;

/**
 * Created by HP on 2018/9/15.
 */

public class MainModel implements MainContract.MainModel {

    private OKHttpUtils utils;

    public MainModel() {
        utils = OKHttpUtils.getInstance();
    }

    @Override
    public void loadData(String url, final NetCallback callback) {
        utils.get(url, new OKHttpUtils.NetCallback() {
            @Override
            public void success(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void error(String errorMsg) {
                callback.onError(errorMsg);
            }
        });
    }
}
