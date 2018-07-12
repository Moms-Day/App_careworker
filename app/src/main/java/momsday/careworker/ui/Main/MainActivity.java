package momsday.careworker.ui.Main;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import momsday.careworker.R;
import momsday.careworker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding r;
    private SectionsPagerAdapter sectionsPagerAdapter;

    PatientListFragment patientListFragment;
    ChattingFragment chattingFragment;
    WriteFormFragment writeFormFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    void init() {
        patientListFragment = new PatientListFragment();
        chattingFragment = new ChattingFragment();
        writeFormFragment = new WriteFormFragment();

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        r.mainViewPager.setAdapter(sectionsPagerAdapter);

        r.tabLayoutMain.addTab(r.tabLayoutMain.newTab().setText("환자 목록"));
        r.tabLayoutMain.addTab(r.tabLayoutMain.newTab().setText("채팅"));
        r.tabLayoutMain.addTab(r.tabLayoutMain.newTab().setText("폼 작성"));

        r.mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(r.tabLayoutMain));
        r.tabLayoutMain.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(r.mainViewPager));


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return patientListFragment;
//                    return new PatientListFragment();
                case 1:
                    return chattingFragment;
//                    return new ChattingFragment();
                case 2:
                    return writeFormFragment;
//                    return new WriteFormFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}