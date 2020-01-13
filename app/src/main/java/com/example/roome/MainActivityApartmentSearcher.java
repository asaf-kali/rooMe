package com.example.roome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.roome.Apartment_searcher_tabs_classes.ApartmentSearcherHome;
import com.example.roome.Apartment_searcher_tabs_classes.EditFiltersApartmentSearcher;
import com.example.roome.Apartment_searcher_tabs_classes.EditProfileApartmentSearcher;
import com.example.roome.Apartment_searcher_tabs_classes.TwoFragmentA;
import com.example.roome.user_classes.ApartmentSearcherUser;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivityApartmentSearcher extends AppCompatActivity {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private int[] selectedtabIcons = {R.drawable.ic_action_filled_home,
            R.drawable.ic_action_filled_heart, R.drawable.ic_action_filled_hourglass,
            R.drawable.ic_action_filled_person};

    private int[] unselectedtabIcons = {R.drawable.ic_action_empty_home,
            R.drawable.ic_action_empty_heart, R.drawable.ic_action_empty_hourglass,
            R.drawable.ic_action_empty_person};

    public static ApartmentSearcherUser aUser;

    // Firebase instance variables
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference firebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apartment_searcher);
//        FirebaseMediate.setDataSnapshot(); //todo add previous activity to handle this
        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        aUser = getCurrentApartmentSearcherUser();
        viewPager = (CustomViewPager) findViewById(R.id.viewpager_apartment);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_apartment);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.setIcon(selectedtabIcons[tab.getPosition()]);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        tab.setIcon(unselectedtabIcons[tab.getPosition()]);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

    private ApartmentSearcherUser getCurrentApartmentSearcherUser() {

        String aptUid = MyPreferences.getUserUid(getApplicationContext());
        return FirebaseMediate.getApartmentSearcherUserByUid(aptUid);
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(selectedtabIcons[0]);
        tabLayout.getTabAt(1).setIcon(unselectedtabIcons[1]);
        tabLayout.getTabAt(2).setIcon(unselectedtabIcons[2]);
        tabLayout.getTabAt(3).setIcon(unselectedtabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ApartmentSearcherHome(), "HOME");
        adapter.addFragment(new TwoFragmentA(), "MATCHES");
        adapter.addFragment(new EditFiltersApartmentSearcher(), "FILTERS");
        adapter.addFragment(new EditProfileApartmentSearcher(), "PROFILE");
        viewPager.setAdapter(adapter);
    }

    public ApartmentSearcherUser getaUser() {
        return aUser;
    }

    public void setaUser(ApartmentSearcherUser aUser) {
        this.aUser = aUser;
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
}
