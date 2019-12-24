package com.example.roome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivityApartmentSearcher extends AppCompatActivity {


    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private int[] selectedtabIcons = {
            R.drawable.ic_action_heart2,
            R.drawable.ic_action_chosenprofile};

    private int[] unselectedtabIcons = {
            R.drawable.ic_action_heart,
            R.drawable.ic_action_name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apartment_searcher);



//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        if( tabLayout.getTabAt(0) == tab){
//                        int tabIconColor = ContextCompat.getColor(context, R.color.tabSelectedIconColor);
                            tab.setIcon(selectedtabIcons[0]);
                    }
                    else {tab.setIcon(selectedtabIcons[1]);}}

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        if( tabLayout.getTabAt(0) == tab){
//                        int tabIconColor = ContextCompat.getColor(context, R.color.tabSelectedIconColor);
                            tab.setIcon(unselectedtabIcons[0]);
                        }
                        else {tab.setIcon(unselectedtabIcons[1]);}}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
//        Toolbar apartment_toolbar = (Toolbar) findViewById(R.id.apartment_tool_bar);
//        androidx.appcompat.widget.Toolbar apartment_toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.apartment_tool_bar);
//        setSupportActionBar(apartment_toolbar);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(unselectedtabIcons[0]);
        tabLayout.getTabAt(1).setIcon(unselectedtabIcons[1]);
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }




//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.action_bar_items, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // todo edit
//        switch(item.getItemId()) {
//            case R.id.ab_action_matches:
//                Toast.makeText(this, "matches", Toast.LENGTH_SHORT).show();
//                return true;
////            case R.id.ab_action_s:
////                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
////                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
