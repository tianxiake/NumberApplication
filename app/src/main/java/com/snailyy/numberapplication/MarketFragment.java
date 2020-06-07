package com.snailyy.numberapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.snailyy.numberapplication.activity.BuyActivity;
import com.snailyy.numberapplication.activity.HealthActivity;
import com.snailyy.numberapplication.activity.VdsMessageActivity;
import com.snailyy.numberapplication.entity.AccountCurrencyEntity;
import com.snailyy.numberapplication.entity.MssWarn;
import com.snailyy.numberapplication.entity.QueryResponseEntity;
import com.snailyy.numberapplication.entity.SellRequest;
import com.snailyy.numberapplication.entity.SellResponse;
import com.snailyy.numberapplication.entity.SurvivalEntity;
import com.snailyy.numberapplication.entity.TradeEntity;
import com.snailyy.numberapplication.entity.VdsConfig;
import com.snailyy.numberapplication.net.NumberService;
import com.snailyy.numberapplication.net.NumberServiceHelper;
import com.snailyy.numberapplication.utils.HiLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yongjie created on 2020/4/18.
 */
public class MarketFragment extends BaseFragment {

    private static final String TAG = "MarketFragment";
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.rv_balance)
    TitleRecyclerView rvBalance;
    @BindView(R.id.rv_service_state)
    TitleRecyclerView rvServiceState;
    @BindView(R.id.rv_market)
    TitleRecyclerView rvMarket;

    @BindView(R.id.rv_week_wave)
    TitleRecyclerView rvWeekWave;
    @BindView(R.id.rv_mss_warn)
    TitleRecyclerView rvMssWarn;
    @BindView(R.id.rv_vds_message)
    TitleRecyclerView rvVds;
    @BindView(R.id.rv_buy_list)
    TitleRecyclerView rvBuyList;
    @BindView(R.id.rv_vds_mss_message)
    TitleRecyclerView rvVdsMss;
    @BindView(R.id.fl_progress)
    FrameLayout progressContainer;

    BalanceAdapter balanceAdapter;
    ServiceStateAdapter serviceStateAdapter;
    ServiceStateAdapter marketStateAdapter;

    TitleOneAdapter marketAdapter;
    TitleOneAdapter weekWaveAdapter;
    TitleOneAdapter mssWarnAdapter;
    TitleTwoAdapter buyListAdapter;
    TitleOneAdapter vdsStateAdapter;
    TitleOneAdapter vdsMssAdapter;

    private boolean isFirst = true;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.market_layout, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        buildBalance();
        buildServiceState();
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        marketAdapter = new TitleOneAdapter();
        marketAdapter.setOnItemClickListener(new TitleOneAdapter.OnItemClickListener<TitleEntity>() {
            @Override
            public void onItemClick(TitleEntity data, int position) {
                Intent intent = new Intent(MarketFragment.this.getContext(), BuyActivity.class);
                startActivity(intent);
            }
        });
        weekWaveAdapter = new TitleOneAdapter();
        mssWarnAdapter = new TitleOneAdapter();
        buyListAdapter = new TitleTwoAdapter();
        vdsStateAdapter = new TitleOneAdapter();
        vdsStateAdapter.setOnItemClickListener(new TitleOneAdapter.OnItemClickListener<TitleEntity>() {
            @Override
            public void onItemClick(TitleEntity data, int position) {
                Intent intent = new Intent(MarketFragment.this.getContext(), VdsMessageActivity.class);
                startActivity(intent);
            }
        });
        vdsMssAdapter = new TitleOneAdapter();

        rvMarket.setAdapter(marketAdapter);
        rvWeekWave.setAdapter(weekWaveAdapter);
        rvMssWarn.setAdapter(mssWarnAdapter);
        rvBuyList.setAdapter(buyListAdapter);
        rvVds.setAdapter(vdsStateAdapter);
        rvVdsMss.setAdapter(vdsMssAdapter);
        rvMarket.setTitle("ETH市场行情");
        rvWeekWave.setTitle("ETH一周波动");
        rvMssWarn.setTitle("ETH短信报警");
        rvBuyList.setTitle("ETH购买列表");
        rvVds.setTitle("Vds状态");
        rvVdsMss.setTitle("VDS短信报警");
    }

    private void buildServiceState() {
        serviceStateAdapter = new ServiceStateAdapter();
        serviceStateAdapter.setOnItemClickListener(new ServiceStateAdapter.OnItemClickListener<AccountCurrencyEntity>() {
            @Override
            public void onItemClick(AccountCurrencyEntity data, int position) {
                Intent intent = new Intent(MarketFragment.this.getContext(), HealthActivity.class);
                startActivity(intent);
            }
        });
        rvServiceState.setTitle("服务运行状态");
        rvServiceState.addItemDecoration(new SpaceItemDecoration(
                DisplayUtil.dip2px(this.getContext(), 5),
                DisplayUtil.dip2px(this.getContext(), 5),
                DisplayUtil.dip2px(this.getContext(), 5),
                DisplayUtil.dip2px(this.getContext(), 5),
                false));
        rvServiceState.setLayoutManager(new GridLayoutManager(this.getContext(), 3, LinearLayoutManager.VERTICAL, false));
        rvServiceState.setAdapter(serviceStateAdapter);
    }

    private void buildBalance() {
        balanceAdapter = new BalanceAdapter();
        rvBalance.setShowTitleView(false);
        rvBalance.addItemDecoration(new SpaceItemDecoration(0, 0,
                DisplayUtil.dip2px(this.getContext(), 10),
                0,
                false));
        rvBalance.setLayoutManager(new GridLayoutManager(this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
        rvBalance.setAdapter(balanceAdapter);
    }

    private void showSellDialog(TradeEntity tempData) {
        float tradePrice = tempData.getTradePrice();
        float newPrice = MessageCenter.getInstance().getQueryResponseEntity().getEthNewPrice();
        float ratio = newPrice / tradePrice - 1;
        float tradeVolume = tempData.getTradeVolume();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("预计卖出:").append(tradeVolume).append('\n')
                .append("预计盈利:").append(NumberUtils.getPercentStr(ratio));

        AlertDialog alertDialog = new AlertDialog.Builder(MarketFragment.this.getContext())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sell(tempData);
                        showProgress();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null)
                .setTitle("卖出")
                .setMessage(stringBuilder.toString())
                .create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void showProgress() {
        srlRefresh.setEnabled(false);
        progressContainer.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        srlRefresh.setEnabled(true);
        progressContainer.setVisibility(View.GONE);
    }

    private void sell(TradeEntity tempData) {
        SellRequest sellRequest = new SellRequest();
        sellRequest.setSellVolume(tempData.getTradeVolume());
        sellRequest.setTradeId(tempData.getTradeId());
        sellRequest.setSellTime(System.currentTimeMillis());
        NumberService numberService = NumberServiceHelper.getInstance().getNumberService();
        numberService.sell(sellRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SellResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SellResponse sellResponse) {
                        if (sellResponse != null && sellResponse.getStatus() == Constants.HTTP_STATUS_SUCCESS_CODE) {
                            ToastUtil.showToast(MarketFragment.this.getActivity(), "卖出成功");
                        } else {
                            ToastUtil.showToast(MarketFragment.this.getActivity(), "卖出失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HiLogger.e(TAG, "e", e);
                        hideProgress();
                        ToastUtil.showToast(MarketFragment.this.getActivity(), "卖出异常");
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                    }
                });
    }

    private void requestData() {
        NumberServiceHelper.getInstance().getNumberService().getNumberRunMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryResponseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryResponseEntity queryResponseEntity) {
                        HiLogger.d(TAG, "queryResponseEntity:{}", queryResponseEntity);
                        if (queryResponseEntity != null && Constants.HTTP_STATUS_SUCCESS_CODE == queryResponseEntity.getStatus()) {
                            ToastUtil.showToast(MarketFragment.this.getContext(), "请求成功");
                            MessageCenter.getInstance().setQueryResponseEntity(queryResponseEntity);
                            updateMarketUI(queryResponseEntity);
                        } else {
                            if (queryResponseEntity != null) {
                                ToastUtil.showToast(MarketFragment.this.getContext(), queryResponseEntity.getDesc());
                            } else {
                                ToastUtil.showToast(MarketFragment.this.getContext(), "请求结果为空");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HiLogger.e(TAG, "error", e);
                        ToastUtil.showToast(MarketFragment.this.getContext(), "请求发生错误");
                        if (srlRefresh.isRefreshing()) {
                            srlRefresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (srlRefresh.isRefreshing()) {
                            srlRefresh.setRefreshing(false);
                        }
                    }
                });
    }

    private void updateMarketUI(QueryResponseEntity queryResponseEntity) {
        if (queryResponseEntity == null) {
            ToastUtil.showToast(this.getContext(), "刷新UI失败");
            return;
        }
        updateBalance(queryResponseEntity);
        updateServiceState(queryResponseEntity);
        rvMarket.setTitle("市场行情 更新时间" + simpleDateFormat.format(System.currentTimeMillis()));
        marketAdapter.resetData(buildMarketEntity(queryResponseEntity));
        weekWaveAdapter.resetData(buildWeekWave(queryResponseEntity));
        mssWarnAdapter.resetData(buildMssWarnWave(queryResponseEntity));
        List<TradeEntity> buyListEntity = buildBuyListEntity(queryResponseEntity);
        if (buyListEntity == null || buyListEntity.isEmpty()) {
            rvBuyList.setVisibility(View.GONE);
        } else {
            rvBuyList.setVisibility(View.VISIBLE);
            buyListAdapter.resetData(buyListEntity);
        }
        vdsStateAdapter.resetData(buildVdsListEntity(queryResponseEntity));
        vdsMssAdapter.resetData(buildVdsMssEntity(queryResponseEntity));
    }

    private void updateServiceState(QueryResponseEntity queryResponseEntity) {
        List<ServiceTitleEntity> titleList = new ArrayList<>();
        ServiceTitleEntity timeEntity = new ServiceTitleEntity("运行时间", queryResponseEntity.getHealthTime() / (1000 * 60 * 60) + "时", ServiceTitleEntity.TYPE_NORMAL);
        boolean priceServiceIsSurvivel = queryResponseEntity.isPriceServiceIsSurvivel();
        boolean accountServiceIsSurvivel = queryResponseEntity.isAccountServiceIsSurvivel();
        boolean orderServiceIsSurvivel = queryResponseEntity.isOrderServiceIsSurvivel();
        boolean marketDepthServiceIsSurvivel = queryResponseEntity.isMarketDepthServiceIsSurvivel();
        ServiceTitleEntity priceEntity = new ServiceTitleEntity("价格服务", priceServiceIsSurvivel ? "正常" : "异常", ServiceTitleEntity.TYPE_WARN, priceServiceIsSurvivel);
        ServiceTitleEntity accountEntity = new ServiceTitleEntity("账户服务", accountServiceIsSurvivel ? "正常" : "异常", ServiceTitleEntity.TYPE_WARN, accountServiceIsSurvivel);
        ServiceTitleEntity orderEntity = new ServiceTitleEntity("订单服务", orderServiceIsSurvivel ? "正常" : "异常", ServiceTitleEntity.TYPE_WARN, orderServiceIsSurvivel);
        ServiceTitleEntity marketEntity = new ServiceTitleEntity("深度服务", marketDepthServiceIsSurvivel ? "正常" : "异常", ServiceTitleEntity.TYPE_WARN, marketDepthServiceIsSurvivel);
        titleList.add(timeEntity);
        titleList.add(priceEntity);
        titleList.add(accountEntity);
        titleList.add(orderEntity);
        titleList.add(marketEntity);
        serviceStateAdapter.resetData(titleList);
    }

    private void updateBalance(QueryResponseEntity queryResponseEntity) {
        balanceAdapter.resetData(queryResponseEntity.getCurrency());
    }

    private List<TitleEntity> buildVdsListEntity(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> marketEntity = new ArrayList<>();
        marketEntity.add(new TitleEntity("服务状态", queryResponseEntity.isVdsServiceIsLive() ? "正常" : "异常"));
        marketEntity.add(new TitleEntity("开盘价", queryResponseEntity.getVdsOpenPrice() + ""));
        marketEntity.add(new TitleEntity("最新价", queryResponseEntity.getVdsNewPrice() + ""));
        float ratio = queryResponseEntity.getVdsNewPrice() / queryResponseEntity.getVdsOpenPrice() - 1;
        marketEntity.add(new TitleEntity("最新/开盘", NumberUtils.getPercentStr(ratio)));
        marketEntity.add(new TitleEntity("重启次数", queryResponseEntity.getVdsServiceReconnectTimes() + ""));
        return marketEntity;
    }

    private List<TitleEntity> buildVdsMssEntity(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> marketEntity = new ArrayList<>();
        VdsConfig vdsConfig = queryResponseEntity.getVdsConfig();
        if (vdsConfig != null && vdsConfig.getMssWarns() != null) {
            List<MssWarn> mssWarns = vdsConfig.getMssWarns();
            for (int i = 0; i < mssWarns.size(); i++) {
                MssWarn mssWarn = mssWarns.get(i);
                marketEntity.add(new TitleEntity("", NumberUtils.getPercentStr(mssWarn.getWarnPercent())));
            }
        }
        return marketEntity;
    }

    private List<TitleEntity> buildServiceStateEntity(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> titleList = new ArrayList<>();
        boolean accountServiceIsSurvivel = queryResponseEntity.isAccountServiceIsSurvivel();
        boolean priceServiceIsSurvivel = queryResponseEntity.isPriceServiceIsSurvivel();
        boolean orderServiceIsSurvivel = queryResponseEntity.isOrderServiceIsSurvivel();
        boolean marketDepthServiceIsSurvivel = queryResponseEntity.isMarketDepthServiceIsSurvivel();
        TitleEntity priceEntity = new TitleEntity(SurvivalEntity.TYPE_PRICE, priceServiceIsSurvivel ? "正常" : "异常");
        TitleEntity accountEntity = new TitleEntity(SurvivalEntity.TYPE_ACCOUNT, accountServiceIsSurvivel ? "正常" : "异常");
        TitleEntity orderEntity = new TitleEntity(SurvivalEntity.TYPE_ORDER, orderServiceIsSurvivel ? "正常" : "异常");
        TitleEntity marketEntity = new TitleEntity(SurvivalEntity.TYPE_MARKET_DEPTH, marketDepthServiceIsSurvivel ? "正常" : "异常");
        titleList.add(priceEntity);
        titleList.add(accountEntity);
        titleList.add(orderEntity);
        titleList.add(marketEntity);
        return titleList;
    }

    private List<TitleEntity> buildMssWarnWave(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> mssWarnList = new ArrayList<>();
        List<MssWarn> mssWarns = queryResponseEntity.getMssWarns();
        for (int i = 0; i < mssWarns.size(); i++) {
            MssWarn mssWarn = mssWarns.get(i);
            mssWarnList.add(new TitleEntity("", NumberUtils.getPercentStr(mssWarn.getWarnPercent())));
        }
        return mssWarnList;
    }

    private List<TitleEntity> buildWeekWave(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> weekList = new ArrayList<>();
        List<QueryResponseEntity.WeekWave> weekWaves = queryResponseEntity.getWeekWaves();
        for (int i = 0; i < weekWaves.size(); i++) {
            QueryResponseEntity.WeekWave weekWave = weekWaves.get(i);
            weekList.add(new TitleEntity(weekWave.getKey(), NumberUtils.getPercentStr(weekWave.getWavePercent())));
        }
        return weekList;
    }

    private List<TitleEntity> buildMarketEntity(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> marketEntity = new ArrayList<>();
        float ethBuyAverage = queryResponseEntity.getEthBuyAverage();
        if (NumberUtils.getScaleFloat(ethBuyAverage, 2) > 0) {
            float ratio = queryResponseEntity.getEthNewPrice() / ethBuyAverage - 1;
            marketEntity.add(new TitleEntity("购买均价", NumberUtils.getScaleStr(ethBuyAverage, 2)));
            marketEntity.add(new TitleEntity("收益率", NumberUtils.getPercentStr(ratio)));
        }
        float ethRatio = queryResponseEntity.getEthNewPrice() / queryResponseEntity.getEthOpenPrice() - 1;
        marketEntity.add(new TitleEntity("最新价", queryResponseEntity.getEthNewPrice() + ""));
        marketEntity.add(new TitleEntity("开盘价", queryResponseEntity.getEthOpenPrice() + ""));
        marketEntity.add(new TitleEntity("最新/开盘", NumberUtils.getPercentStr(ethRatio)));
        marketEntity.add(new TitleEntity("卖单价", queryResponseEntity.getAskDepthLowPrice() + ""));
        marketEntity.add(new TitleEntity("买单价", queryResponseEntity.getBidDepthHighPrice() + ""));
        marketEntity.add(new TitleEntity("默认盈利比例", NumberUtils.getPercentStr(queryResponseEntity.getDefaultProfitPercent())));
        marketEntity.add(new TitleEntity("每秒交易次数", NumberUtils.getPercentStr(queryResponseEntity.get)));
        return marketEntity;
    }

    private List<TitleEntity> buildStateEntity(QueryResponseEntity queryResponseEntity) {
        List<TitleEntity> titleEntities = new ArrayList<>();
        titleEntities.add(new TitleEntity("等待", queryResponseEntity.getWait()));
        titleEntities.add(new TitleEntity("运行模式", queryResponseEntity.getRunMode()));
//		titleEntities.add(new TitleEntity("市场预期", queryResponseEntity.getCurrentMarketExpects()));
        titleEntities.add(new TitleEntity("运行时间", queryResponseEntity.getHealthTime() / (1000 * 60 * 60) + "时"));
        return titleEntities;
    }

    private List<TradeEntity> buildBuyListEntity(QueryResponseEntity queryResponseEntity) {
        List<TradeEntity> tradesList = queryResponseEntity.getTradesList();
        return tradesList;
    }

    @Override
    public void onShow() {
        super.onShow();
        HiLogger.d(TAG, "onShow");
        if (isFirst) {
            isFirst = false;
            requestData();
        }
    }

}
