package top.liewyoung.strategy.asset.impl;

import top.liewyoung.strategy.asset.Asset;
import top.liewyoung.strategy.asset.AssetType;

import java.util.Random;

/**
 * 企业资产
 * 中等波动率，有月收入
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class BusinessAsset implements Asset {

    private final String name;
    private final int purchasePrice;
    private int currentValue;
    private int monthlyIncome;
    private final double volatility = 0.10; // ±10%波动

    public BusinessAsset(String name, int purchasePrice, int monthlyIncome) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.currentValue = purchasePrice;
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AssetType getType() {
        return AssetType.BUSINESS;
    }

    @Override
    public int getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    @Override
    public double getVolatility() {
        return volatility;
    }

    @Override
    public void updateValue(Random random) {
        // 企业波动：-10% 到 +10%
        double change = (random.nextDouble() * 2 - 1) * volatility;
        currentValue = (int) (currentValue * (1 + change));
        // 企业收入也可能小幅波动
        int incomeChange = (int) ((random.nextDouble() * 2 - 1) * monthlyIncome * 0.05);
        monthlyIncome = Math.max(0, monthlyIncome + incomeChange);
        if (currentValue < 0)
            currentValue = 0;
    }

    @Override
    public String getStatus() {
        int profit = currentValue - purchasePrice;
        String valueStatus = profit >= 0 ? "+" + profit : String.valueOf(profit);
        return String.format("月入%d元 (%s)", monthlyIncome, valueStatus);
    }
}
