package com.agency.broker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CleanserAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments=new ArrayList<>();
    private String token="";
    private Long policyId=0L;

    public CleanserAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position==0) {
            return RiskFragment.newInstance(policyId,token) ;
        }
        else if(position==1){
            return ReceiptFragment.newInstance(policyId,token);
        }
        else if(position == 2) {
            return ClaimsFragment.newInstance(policyId,token);
        }
        else{
            return RiskFragment.newInstance(policyId,token);
        }    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment,String token,Long policyId){
        fragments.add(fragment);
        this.policyId=policyId;
        this.token=token;
    }
}
