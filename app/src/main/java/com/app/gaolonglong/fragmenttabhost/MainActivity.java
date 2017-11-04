package com.app.gaolonglong.fragmenttabhost;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private CustomViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private LinearLayout sendTagLayout;
    private Class mClass[] = {HomeFragment.class, ReportFragment.class, null,MessageFragment.class, MineFragment.class};
    private Fragment mFragment[] = {new HomeFragment(), new ReportFragment(),null, new MessageFragment(), new MineFragment()};
    private String mTitles[] = {"首页", "报表", "消息", "我的"};

    private int mImages[] = {
            R.drawable.tab_home,
            R.drawable.tab_report,
            R.drawable.tab_message,
            R.drawable.tab_mine
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        initView();
        initEvent();
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
        sendTagLayout= (LinearLayout) findViewById(R.id.sendTagLayout);
        mFragmentList = new ArrayList<Fragment>();

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mFragment.length; i++) {
                TabHost.TabSpec tabSpec = mTabHost.newTabSpec(i+"").setIndicator(getTabView(i));
                mTabHost.addTab(tabSpec, mClass[i], null);
                if(i!=2){
                    mFragmentList.add(mFragment[i]);
                }

                mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);

        }
        mViewPager.setAnimation(null);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private View getTabView(int index) {
        View view =null;
        if(index==0){
            view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);
            image.setImageResource(mImages[0]);
            title.setText(mTitles[0]);
        }else if(index==1){
            view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);
            image.setImageResource(mImages[1]);
            title.setText(mTitles[1]);
        }else if(index==2){
            view = LayoutInflater.from(this).inflate(R.layout.tab_center_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            //TextView title = (TextView) view.findViewById(R.id.title);
            //image.setImageResource(mImages[1]);
            //title.setText(mTitles[1]);
        }else if(index==3){
            view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);
            image.setImageResource(mImages[2]);
            title.setText(mTitles[2]);
        }else if(index==4){
            view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);
            image.setImageResource(mImages[3]);
            title.setText(mTitles[3]);
        }




        return view;
    }

    private void initEvent() {
        sendTagLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"tabId",Toast.LENGTH_SHORT).show();
            }
        });
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(!tabId.equals("2")){
                    if(tabId.equals("0")){
                        mViewPager.setCurrentItem(0);
                    }else if(tabId.equals("1")){
                        mViewPager.setCurrentItem(1);
                    }else if(tabId.equals("3")){
                        mViewPager.setCurrentItem(2);
                    }else if(tabId.equals("4")){
                        mViewPager.setCurrentItem(3);
                    }

                }else{

                }
                Toast.makeText(MainActivity.this,""+tabId,Toast.LENGTH_SHORT).show();

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mTabHost.setCurrentTab(0);
                }else if(position==1){
                    mTabHost.setCurrentTab(1);
                }else if(position==2){
                    mTabHost.setCurrentTab(3);
                }else if(position==3){
                    mTabHost.setCurrentTab(4);
                }
                //
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
