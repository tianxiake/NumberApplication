package com.snailyy.numberapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.snailyy.numberapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2020/5/12.
 */

public class RangeView extends LinearLayout {
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.tv_value)
	TextView tvValue;
	@BindView(R.id.sb_range)
	SeekBar sbRange;
	private SeekMode seekMode = SeekMode.positive_percent;

	public enum SeekMode {
		positive_percent,
		positive_and_negative_percent;
	}


	public RangeView(Context context) {
		super(context);
		initView();
	}

	public RangeView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public RangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		LayoutInflater.from(this.getContext()).inflate(R.layout.range_view_layout, this, true);
		ButterKnife.bind(this);
		sbRange.setMax(100);
		sbRange.setProgress(0);
		sbRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (seekMode.name().equals(SeekMode.positive_percent.name())) {
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}

	public void setTextTitle(String title) {
		tvTitle.setText(title);
	}

	public void setSeekMode(SeekMode seekMode) {
		this.seekMode = seekMode;
		if (seekMode.name().equals(SeekMode.positive_percent.name())) {
			sbRange.setMax(100);
			sbRange.setProgress(0);
		} else if (seekMode.name().equals(SeekMode.positive_and_negative_percent.name())) {
			sbRange.setMax(100);
			sbRange.setProgress(-100);
		}
	}


}
