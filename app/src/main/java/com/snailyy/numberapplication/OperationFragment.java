package com.snailyy.numberapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author yongjie created on 2020/4/18.
 */
public class OperationFragment extends BaseFragment {

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.operate_layout, container, false);
		return inflate;
	}
}
