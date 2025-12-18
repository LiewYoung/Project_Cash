package top.liewyoung.strategy.asset.impl;

import top.liewyoung.strategy.asset.Asset;
import top.liewyoung.strategy.asset.AssetType;

import java.util.Random;

/**
 * 房产资产
 * 低波动率，有稳定租金收入
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class PropertyAsset implements Asset {

    private final String name;
    private final int purchasePrice;
    private int currentValue;
    private int monthlyIncome; // 租金
    private final double volatility = 0.05; // ±5%波动

    public PropertyAsset(String name, int purchasePrice, int monthlyIncome) {
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
        return AssetType.PROPERTY;
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
        // 房产波动：-5% 到 +5%，但更倾向于增值
        double change = (random.nextDouble() * 2 - 0.5) * volatility; // 略微倾向正值
        currentValue = (int) (currentValue * (1 + change));
        // 租金可能小幅上涨
        if (random.nextDouble() < 0.1) { // 10%概率涨租金
            monthlyIncome += random.nextInt(50) + 10;
        }
        if (currentValue < 0)
            currentValue = 0;
    }

    @Override
    public String getStatus() {
        int profit = currentValue - purchasePrice;
        double rate = (double) profit / purchasePrice * 100;
        return String.format("租金%d元 (%.1f%%)", monthlyIncome, rate);
    }
}
