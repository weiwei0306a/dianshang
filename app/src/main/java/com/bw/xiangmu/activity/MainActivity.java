package com.bw.xiangmu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bw.xiangmu.R;
import com.bw.xiangmu.fragment.Home;
import com.bw.xiangmu.fragment.circle;
import com.bw.xiangmu.fragment.classify;
import com.bw.xiangmu.fragment.me;
import com.bw.xiangmu.fragment.shopping;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.rg);
        vp = findViewById(R.id.vp);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.btn4:
                        vp.setCurrentItem(3);
                        break;
                    case R.id.btn5:
                        vp.setCurrentItem(4);
                        break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        rg.check(R.id.btn1);
                        break;
                    case 1:
                        rg.check(R.id.btn2);
                        break;
                    case 2:
                        rg.check(R.id.btn3);
                        break;
                    case 3:
                        rg.check(R.id.btn4);
                        break;
                    case 4:
                        rg.check(R.id.btn5);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new Home();
                    case 1:
                        return new circle();
                    case 2:
                        return new shopping();
                    case 3:
                        return new classify();
                    case 4:
                        return new me();

                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }
}
