package com.agency.broker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuotePagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments=new ArrayList<>();
    private String token="";
    private Long quoteId=0L;

    public QuotePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
            return QuoteRiskFragment.newInstance(quoteId,token) ;
    }

    @Override
    public int getCount() {

        return fragments.size();
    }
    public void addFragment(Fragment fragment,String token,Long quoteId){
        fragments.add(fragment);
        this.quoteId=quoteId;
        this.token=token;
    }
}
