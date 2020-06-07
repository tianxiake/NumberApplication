package com.snailyy.numberapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yongjie created on 2020/4/19.
 */
public class TitleRecyclerView extends LinearLayout {

	private TextView textView;
	private RecyclerView recyclerView;
	private RecyclerView.Adapter myAdapter;
	private boolean titleViewIsShow = true;

	public TitleRecyclerView(Context context) {
		this(context, null);
	}

	public TitleRecyclerView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		this.setOrientation(VERTICAL);
		LayoutInflater.from(this.getContext()).inflate(R.layout.title_recycler_layout, this, true);
		textView = this.findViewById(R.id.tv_title);
		recyclerView = this.findViewById(R.id.rv_content);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
	}

	public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
		recyclerView.setLayoutManager(layoutManager);
	}

	public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
		recyclerView.addItemDecoration(itemDecoration);
	}

	public void setShowTitleView(boolean showTitle) {
		if (showTitle) {
			titleViewIsShow = true;
			textView.setVisibility(View.VISIBLE);
		} else {
			titleViewIsShow = false;
			textView.setVisibility(View.GONE);
		}
	}

	public boolean isTitleViewIsShow() {
		return titleViewIsShow;
	}

	public void setTitle(CharSequence title) {
		textView.setText(title);
	}

	public void setAdapter(RecyclerView.Adapter adapter) {
		myAdapter = adapter;
		recyclerView.setAdapter(adapter);
	}

}
