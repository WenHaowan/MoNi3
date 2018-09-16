package as.bwei.com.moni3.presenter;

import as.bwei.com.moni3.view.IView;

/**
 * Created by HP on 2018/9/15.
 */

public interface IPresenter<V extends IView> {

    void attach(V v);

    void detach();
}
