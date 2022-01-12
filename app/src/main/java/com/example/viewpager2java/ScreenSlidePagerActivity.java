package com.example.viewpager2java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;
    private static final String TAG = "ScreenSlidePagerActivity";


    TabLayout tabLayout;
    final String[] tabNames = {"QR-Code", "Neural Network plant detection"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        Log.v(TAG, "viewPager.setAdapter");

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        Log.v(TAG, "tabLayout initialized");



    }

    @Override
    public void onStart() {
        super.onStart();


        if (tabLayout != null && viewPager!= null) {
            new TabLayoutMediator(
                    tabLayout,
                    viewPager,
                    (tab, position) -> {
                        tab.setText(tabNames[position]);
                        tab.setIcon(R.drawable.ic_launcher_background);
                    }

            ).attach();
        } else {
            if (tabLayout == null) {
                Log.v(TAG, "tabLayout == null");
            }
            if (viewPager == null) {
                Log.v(TAG, "viewPager == null");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents some ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            // here you can supply custom ScreenSlidePageFragemnt, based on the position
            if (position == 0) {
               return ScreenSlidePageFragment.newInstance("This is the first Fragment");
            } else {
                return ScreenSlidePageFragment.newInstance("This is the second Fragment");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
