package edu.ncu.safe.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import edu.ncu.safe.R;
import edu.ncu.safe.adapter.AppManagerPVAdapter;
import edu.ncu.safe.domain.UserAppBaseInfo;
import edu.ncu.safe.ui.fragment.AppManagerFragment;
import edu.ncu.safe.ui.fragment.NetManagerFragment;

/**
 * Created by Mr_Yang on 2016/5/19.
 */
public class AppManagerAcitvity extends MyAppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener,AppManagerFragment.OnDataChangeListener {
    private ViewPager vp_appManager;
    private LinearLayout ll_appManager;
    private LinearLayout ll_netManager;
    private List<Fragment> fragments ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmanager);
        initToolBar(getResources().getString(R.string.title_app_manager));
        vp_appManager = (ViewPager) this.findViewById(R.id.vp_appmanager);
        ll_appManager  = (LinearLayout) this.findViewById(R.id.ll_appmanager);
        ll_netManager = (LinearLayout) this.findViewById(R.id.ll_netmanager);

        fragments = new ArrayList<Fragment>();
        fragments.add(new AppManagerFragment());
        fragments.add(new NetManagerFragment());
        vp_appManager.setAdapter(new AppManagerPVAdapter(getSupportFragmentManager(),fragments));

        ll_netManager.setAlpha(0);
        ll_appManager.setOnClickListener(this);
        ll_netManager.setOnClickListener(this);
        vp_appManager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_appmanager:
                vp_appManager.setCurrentItem(0);
                break;
            case R.id.ll_netmanager:
                vp_appManager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset==0 && positionOffsetPixels==0){
            ll_appManager.setAlpha(position==0?1:0);
            ll_netManager.setAlpha(position);
        }else{
            ll_appManager.setAlpha(1-positionOffset);
            ll_netManager.setAlpha(positionOffset);
        }

    }
    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void dataChange(List<UserAppBaseInfo> infos, String packName) {
        ((NetManagerFragment)fragments.get(1)).onDataChanged(infos,packName);
    }
}
