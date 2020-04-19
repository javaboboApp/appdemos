package com.example.demoapi.mvp.shared;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends BaseContract.View>
    implements BaseContract.Presenter<T> {

  protected T view;

  protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

  public void onViewAttached() {
  }

  public void onViewDetached() {
    if (mCompositeSubscription.hasSubscriptions()) {
      mCompositeSubscription.clear();
    }
  }

  public void subscribe(Subscription subscription) {
    mCompositeSubscription.add(subscription);
  }


}
