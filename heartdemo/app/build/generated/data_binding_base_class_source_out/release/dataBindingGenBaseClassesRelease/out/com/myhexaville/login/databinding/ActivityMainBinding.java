package com.myhexaville.login.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.myhexaville.login.FlexibleFrameLayout;
import com.myhexaville.login.login.LoginSignUpChangeButton;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final LoginSignUpChangeButton button;

  @NonNull
  public final CoordinatorLayout coordinator;

  @NonNull
  public final FrameLayout loginFragment;

  @NonNull
  public final FrameLayout signUpFragment;

  @NonNull
  public final FlexibleFrameLayout wrapper;

  protected ActivityMainBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LoginSignUpChangeButton button, CoordinatorLayout coordinator,
      FrameLayout loginFragment, FrameLayout signUpFragment, FlexibleFrameLayout wrapper) {
    super(_bindingComponent, _root, _localFieldCount);
    this.button = button;
    this.coordinator = coordinator;
    this.loginFragment = loginFragment;
    this.signUpFragment = signUpFragment;
    this.wrapper = wrapper;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.myhexaville.login.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.myhexaville.login.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityMainBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityMainBinding)bind(component, view, com.myhexaville.login.R.layout.activity_main);
  }
}
