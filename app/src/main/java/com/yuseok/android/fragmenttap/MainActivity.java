package com.yuseok.android.fragmenttap;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// 1. fragment.xml의 FramLayout은 RelativeLayout으로 바꾼뒤에 조정해야한다.
public class MainActivity extends AppCompatActivity {

    // 탭 및 페이저 속성 정의
    final int TAB_COUNT = 4;
    OneFragment one;
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 프래그먼트 init
        one = new OneFragment();
        two = new TwoFragment();
        three = new ThreeFragment();
        four = new FourFragment();


        // Tablayout 구현
        // 1. xml에 TabLayout과 ViewPager를 넣어준다.
        // 2. Activity에서 TabLayout event 발생시 ViewPager를 이용하도록 listener를 설정한다,
        // 3. viewPager에서는 Fragment를 호출하는 부분을 만들어준다.
        // 탭 layout정의
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        // 탭 생성 및 타이틀 입력
        tabLayout.addTab( tabLayout.newTab().setText("계산기")); // 가져온 위젯에서 탭 형태를 생산
        tabLayout.addTab( tabLayout.newTab().setText("단위 환산"));
        tabLayout.addTab( tabLayout.newTab().setText("검색"));
        tabLayout.addTab( tabLayout.newTab().setText("현재 위치"));

        // (ViewPager가 사용된)프래그먼트 페이저 작성
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Adapter생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        // Activity에서 Layout 리소스 XML에 정의된 Fragment의 참조를 가져오기위해 getSupportFragmentManager()함수 사용
        viewPager.setAdapter(adapter);

        // 1. 페이저 리스너 - 페이저가 변경되었을때 탭을 바꿔주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // 2. 탭이 변경되었을때 페이지를 바꿔주는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }


    class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Fragment의 값을 null로 초기화
            Fragment fragment = null;

            // position값에 따라 Pager변화
            switch (position) {
                case 0 : fragment = one; break;
                case 1 : fragment = two; break;
                case 2 : fragment = three; break;
                case 3 : fragment = four; break;

            }
            return fragment;
        }

        // 데이터의 총 개수
        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
