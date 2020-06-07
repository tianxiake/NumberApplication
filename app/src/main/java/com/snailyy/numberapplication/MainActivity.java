package com.snailyy.numberapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.snailyy.numberapplication.utils.HiLogger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	@BindView(R.id.ll_bottom_menu)
	LinearLayout llBottomMenu;
	@BindView(R.id.vp_content)
	ViewPager vpContent;
	@BindView(R.id.btn_market)
	Button btnMarket;
	@BindView(R.id.btn_operate)
	Button btnOperate;
	@BindView(R.id.btn_my)
	Button btnMy;
	private CustomFragmentAdapter customFragmentAdapter;
	private List<String> content = new ArrayList<>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hideActionBar();
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		content.add(Constants.MARKET);
//		content.add(Constants.OPERATION);
//		content.add(Constants.MY);
		customFragmentAdapter = new CustomFragmentAdapter(getSupportFragmentManager());
		customFragmentAdapter.setContent(content);
		vpContent.setOffscreenPageLimit(2);
		vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				HiLogger.d(TAG, "onPageSelected:{}", position);
				int childCount = llBottomMenu.getChildCount();
				for (int i = 0; i < childCount; i++) {
					Button button = (Button) llBottomMenu.getChildAt(i);
					if (i != position) {
						button.setTextColor(Color.parseColor("#9A000000"));
					} else {
						BaseFragment baseFragment = customFragmentAdapter.getContentFragment().get(position);
						baseFragment.onShow();
						button.setTextColor(Color.parseColor("#EE000000"));
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		vpContent.setAdapter(customFragmentAdapter);
		customFragmentAdapter.notifyDataSetChanged();
		vpContent.post(new Runnable() {
			@Override
			public void run() {
				vpContent.setCurrentItem(1);
				vpContent.setCurrentItem(0);
			}
		});
	}

	private void hideActionBar() {
		ActionBar supportActionBar = getSupportActionBar();
		if (supportActionBar != null) {
			supportActionBar.hide();
		}
	}

	@OnClick({R.id.btn_market, R.id.btn_my, R.id.btn_operate})
	public void onMenuClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_market:
				vpContent.setCurrentItem(0);
				break;
			case R.id.btn_operate:
				vpContent.setCurrentItem(1);
				break;
			case R.id.btn_my:
				vpContent.setCurrentItem(2);
				break;
			default:
				break;
		}
	}

	private class CustomFragmentAdapter extends FragmentPagerAdapter {
		private SparseArray<BaseFragment> contentFragment = new SparseArray<>();
		private List<String> content = new ArrayList<>();

		public CustomFragmentAdapter(@NonNull FragmentManager fm) {
			super(fm);
		}

		@NonNull
		@Override
		public Object instantiateItem(@NonNull ViewGroup container, int position) {
			Object o = super.instantiateItem(container, position);
			contentFragment.put(position, (BaseFragment) o);
			return o;
		}

		public SparseArray<BaseFragment> getContentFragment() {
			return contentFragment;
		}

		@Override
		public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
			contentFragment.remove(position);
			super.destroyItem(container, position, object);
		}

		public void setContent(List<String> content) {
			this.content = content;
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			String s = content.get(position);
			switch (s) {
				case Constants.MARKET:
					return new MarketFragment();
				case Constants.OPERATION:
					return new OperationFragment();
				default:
					break;
			}
			return null;
		}

		@Override
		public int getCount() {
			return content.size();
		}
	}

}
