package as.bwei.com.moni3.presenter;

import java.lang.ref.WeakReference;

import as.bwei.com.moni3.contract.MainContract;
import as.bwei.com.moni3.model.MainModel;

/**
 * Created by HP on 2018/9/15.
 */

public class MainPresenter implements MainContract.MainPresenter {

    private WeakReference<MainContract.MainView> viewWeakReference;
    private WeakReference<MainModel> modelWeakReference;

    @Override
    public void loadData(String url) {
        modelWeakReference.get().loadData(url, new MainContract.MainModel.NetCallback() {
            @Override
            public void onSuccess(String result) {
                viewWeakReference.get().showList(result);
            }

            @Override
            public void onError(String meg) {

            }
        });
    }

    @Override
    public void attach(MainContract.MainView mainView) {
        viewWeakReference = new WeakReference(mainView);
        modelWeakReference = new WeakReference(new MainModel());
    }

    @Override
    public void detach() {
        if (viewWeakReference != null) {
            viewWeakReference.clear();
            viewWeakReference = null;
            modelWeakReference.clear();
            modelWeakReference = null;
        }
    }
}
