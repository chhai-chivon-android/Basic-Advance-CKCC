package com.nuon.peter.demoapp.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by beniten on 12/29/15.
 *
 * Something called
 * public class MyPageAdapter extends SmartFragmentStatePagerAdapter<MyFragment>{}
 *
 */
public abstract class SmartFragmentStatePagerAdapterPlus<T extends Fragment> extends
    FragmentStatePagerAdapter {

  private SparseArray<T> registeredFragments = new SparseArray<T>();

  public SmartFragmentStatePagerAdapterPlus(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  // Register the fragment when the item is instantiated
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    T fragment = (T) super.instantiateItem(container, position);
    registeredFragments.put(position, fragment);
    return fragment;
  }

  // Unregister when the item is inactive
  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    registeredFragments.remove(position);
    super.destroyItem(container, position, object);
  }

  // Returns the fragment for the position (if instantiated)
  public T getRegisteredFragment(int position) {
    return registeredFragments.get(position);
  }
}