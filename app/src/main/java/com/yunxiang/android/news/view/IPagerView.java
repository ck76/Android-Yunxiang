

package com.yunxiang.android.news.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public interface IPagerView {

    void init(Activity activity);

    View onCreateView(Activity context, Bundle info);
}
