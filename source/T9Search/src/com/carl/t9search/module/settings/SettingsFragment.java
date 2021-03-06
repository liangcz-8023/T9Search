package com.carl.t9search.module.settings;

import android.view.View;
import com.carl.t9search.framework.app.AppManager;
import com.carl.t9search.framework.base.BaseFragment;
import com.carl.t9search.framework.widget.SettingItemView;
import com.carl.t9search.framework.widget.SettingItemView.OnSettingItemCheckListener;
import com.carl.t9search.framework.widget.SettingItemView.OnSettingItemClickListener;

import com.carl.t9search.R;
import com.umeng.fb.FeedbackAgent;

public class SettingsFragment extends BaseFragment implements OnSettingItemClickListener, OnSettingItemCheckListener{
    
    private SettingItemView mRebuildView;
    private SettingItemView mVibrateView;
    private SettingItemView mSoundView;
    private SettingItemView mQuickView;
    private SettingItemView mFeedbackView;
    private SettingItemView mHideView;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initViews(View root) {
        
        mRebuildView = (SettingItemView)root.findViewById(R.id.siv_rebuild_index);
        mRebuildView.setOnSettingClickListener(this);
        
        mVibrateView = (SettingItemView)root.findViewById(R.id.siv_fb_vibrate);
        mVibrateView.setSwitchChecked(SettingsManager.getVibrateFeedback());
        mVibrateView.setOnCheckChangeListener(this);
        
        mSoundView = (SettingItemView)root.findViewById(R.id.siv_fb_sound);
        mSoundView.setSwitchChecked(SettingsManager.getSoundFeedback());
        mSoundView.setOnCheckChangeListener(this);
        mSoundView.setVisibility(View.GONE);
        
        mFeedbackView = (SettingItemView)root.findViewById(R.id.siv_feedback);
        mFeedbackView.setOnSettingClickListener(this);
        
        mHideView = (SettingItemView)root.findViewById(R.id.siv_hide_after_open_app);
        mHideView.setSwitchChecked(SettingsManager.getHideAfterOpenApp());
        mHideView.setOnCheckChangeListener(this);
        
        mQuickView = (SettingItemView)root.findViewById(R.id.siv_quick_dial);
        mQuickView.setOnSettingClickListener(this);
    }

    @Override
    public void initDatas() {
        
    }

    @Override
    public void onSettingItemClick(View view) {
        switch(view.getId()){
        case R.id.siv_rebuild_index:
            AppManager.getInstance().startLoadTask(getActivity(), null, true);
            break;
        case R.id.siv_feedback:
            FeedbackAgent agent = new FeedbackAgent(getActivity());
            agent.startFeedbackActivity();
            break;
        case R.id.siv_quick_dial:
            openFragment(this, QuickDialFragment.class, null);
            break;
        case R.id.siv_about:
        	break;
        }
    }

    @Override
    public void onSettingItemCheckChanged(View view, boolean checked) {
        switch(view.getId()){
        case R.id.siv_fb_vibrate:
            SettingsManager.setVibrateFeedback(checked);
            break;
        case R.id.siv_fb_sound:
            SettingsManager.setSoundFeedback(checked);
            break;
        case R.id.siv_hide_after_open_app:
            SettingsManager.setHideAfterOpenApp(checked);
            break;
        }
    }

}
