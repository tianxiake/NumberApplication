package com.snailyy.numberapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.snailyy.numberapplication.Constants;
import com.snailyy.numberapplication.MessageCenter;
import com.snailyy.numberapplication.NumberUtils;
import com.snailyy.numberapplication.ParseUtils;
import com.snailyy.numberapplication.R;
import com.snailyy.numberapplication.ToastUtil;
import com.snailyy.numberapplication.entity.AccountCurrencyEntity;
import com.snailyy.numberapplication.entity.BuyRequest;
import com.snailyy.numberapplication.entity.BuyResponse;
import com.snailyy.numberapplication.entity.QueryResponseEntity;
import com.snailyy.numberapplication.net.NumberService;
import com.snailyy.numberapplication.net.NumberServiceHelper;
import com.snailyy.numberapplication.utils.HiLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BuyActivity extends AppCompatActivity {
	private static final String TAG = "BuyActivity";
	@BindView(R.id.rv_balance)
	TextView tvBalance;
	@BindView(R.id.tv_available)
	TextView tvAvailable;
	@BindView(R.id.et_buy_weight)
	EditText etBuyWeight;
	@BindView(R.id.btn_buy)
	Button btnBuy;
	@BindView(R.id.et_profit)
	EditText etProfit;
	@BindView(R.id.pb_progress)
	ProgressBar pbProgress;
	private float balance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		ButterKnife.bind(this);
		showData();
	}

	private void showData() {
		QueryResponseEntity queryResponseEntity = MessageCenter.getInstance().getQueryResponseEntity();
		if (queryResponseEntity != null) {
			List<AccountCurrencyEntity> currency = queryResponseEntity.getCurrency();
			for (int i = 0; i < currency.size(); i++) {
				AccountCurrencyEntity accountCurrencyEntity = currency.get(i);
				if (Constants.USDT_CURRENCY.equals(accountCurrencyEntity.currency)) {
					balance = accountCurrencyEntity.getBalance();
					tvBalance.setText("总的USDT:" + NumberUtils.getScaleStr(balance));
					tvAvailable.setText("可用USDT:" + NumberUtils.getScaleStr(accountCurrencyEntity.getAvailable()));
				}
			}
			btnBuy.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					float buyWeight = getBuyWeight();
					if (buyWeight >= 0 && buyWeight <= 1) {
						showDialog(buyWeight, getProfit());
					} else {
						ToastUtil.showToast(v.getContext(), "购买比例必须在0-1之间");
					}
				}
			});
		}
	}

	private float getBuyWeight() {
		Editable text = etBuyWeight.getText();
		if (text != null) {
			String s = text.toString();
			return ParseUtils.parseFloat(s, -1);
		}
		return -1;
	}

	private float getProfit() {
		Editable profitText = etProfit.getText();
		float profit = -1;
		if (profitText != null) {
			profit = ParseUtils.parseFloat(profitText.toString(), -1);
			if (profit > 1 || profit < 0) {
				profit = -1;
			}
		}
		return profit;
	}

	@Override
	public void onBackPressed() {
		if (pbProgress.getVisibility() == View.VISIBLE) {
			return;
		}
		super.onBackPressed();
	}

	private void showDialog(float buyWeight, float profit) {
		float buyCount = balance * buyWeight;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("预计消费")
				.append(NumberUtils.getScaleStr(buyCount))
				.append("usdt")
				.append('\n')
				.append("预期盈利比例:");
		if (profit < 0) {
			stringBuilder.append("默认比例");
		} else {
			stringBuilder.append(NumberUtils.getPercentStr(profit));
		}
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						buy(buyWeight, profit);
						showProgress();
						dialog.dismiss();
					}
				}).setNegativeButton("取消", null).setMessage(stringBuilder.toString()).create();
		alertDialog.show();
	}

	public void showProgress() {
		pbProgress.setVisibility(View.VISIBLE);
	}

	public void hideProgress() {
		pbProgress.setVisibility(View.GONE);
	}

	private void buy(float buyWeight, float profit) {
		BuyRequest buyRequest = new BuyRequest();
		buyRequest.setBuyWeight(buyWeight);
		buyRequest.setProfitPercent(profit);
		buyRequest.setBuyTime(System.currentTimeMillis());
		NumberService numberService = NumberServiceHelper.getInstance().getNumberService();
		numberService.buy(buyRequest)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<BuyResponse>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(BuyResponse buyResponse) {
						HiLogger.d(TAG, "BuyResponse:{}", buyResponse);
						if (buyResponse != null && buyResponse.getStatus() == Constants.HTTP_STATUS_SUCCESS_CODE) {
							ToastUtil.showToast(BuyActivity.this, "购买成功");
						} else {
							ToastUtil.showToast(BuyActivity.this, buyResponse.getDesc());
						}
					}

					@Override
					public void onError(Throwable e) {
						HiLogger.e(TAG, "Throwable", e);
						pbProgress.setVisibility(View.GONE);
						ToastUtil.showToast(BuyActivity.this, "购买失败");
					}

					@Override
					public void onComplete() {
						pbProgress.setVisibility(View.GONE);
					}
				});
	}

}
