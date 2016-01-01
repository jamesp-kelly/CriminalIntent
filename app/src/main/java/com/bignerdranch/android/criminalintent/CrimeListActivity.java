package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by james on 20/12/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
