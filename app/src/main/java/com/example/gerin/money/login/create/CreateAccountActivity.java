package com.example.gerin.money.login.create;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gerin.money.R;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    private int pageNum;
    private CreateAccountPersonalFragment fragment1;
    private CreateAccountEmailPassFragment fragment2;
    private ViewPager view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = findViewById(R.id.create_account_container);
        fragment1 = new CreateAccountPersonalFragment();
        fragment2 = new CreateAccountEmailPassFragment();

        CreateAccountAdapter adapter = new CreateAccountAdapter(getSupportFragmentManager());
        view.setAdapter(adapter);
        view.setOffscreenPageLimit(2);

        view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void nextPage(View view) {
        View focusView = this.getCurrentFocus();
        this.view.setCurrentItem(this.view.getCurrentItem() + 1);
    }

    @Override
    public void onBackPressed() {
        if (pageNum == 0) {
            finish();
        } else {
            view.setCurrentItem(view.getCurrentItem() - 1);
        }
    }

    private class CreateAccountAdapter extends FragmentPagerAdapter {
        private CreateAccountAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return fragment1;
            } else {
                return fragment2;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}


