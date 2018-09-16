package as.bwei.com.moni3.contract;

import android.view.View;

import as.bwei.com.moni3.model.IModel;
import as.bwei.com.moni3.presenter.IPresenter;
import as.bwei.com.moni3.view.IView;

/**
 * Created by HP on 2018/9/15.
 */

public interface MainContract {

    interface MainView extends IView {
        void showList(String result);
    }

    interface MainModel extends IModel {

        interface NetCallback {
            void onSuccess(String result);

            void onError(String meg);
        }

        void loadData(String url, NetCallback callback);
    }

    interface MainPresenter extends IPresenter<MainView> {

        void loadData(String url);
    }
}
