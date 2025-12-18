package top.liewyoung.strategy.asset.impl;

import top.liewyoung.strategy.asset.Asset;
import top.liewyoung.strategy.asset.AssetType;

import java.util.Random;

/**
 * 股票资产
 * 高波动率，无月收入
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class StockAsset implements Asset {

    private final String name;
    private final int purchasePrice;
    private int currentValue;
    private final double volatility = 0.20; // ±20%波动

    public StockAsset(String name, int purchasePrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.currentValue = purchasePrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AssetType getType() {
        return AssetType.STOCK;
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
        return 0; // 股票无月收入
    }

    @Override
    public double getVolatility() {
        return volatility;
    }

    @Override
    public void updateValue(Random random) {
        // 股票波动：-20% 到 +20%
        double change = (random.nextDouble() * 2 - 1) * volatility;
        currentValue = (int) (currentValue * (1 + change));
        if (currentValue < 0)
            currentValue = 0;
    }

    @Override
    public String getStatus() {
        int profit = currentValue - purchasePrice;
        if (profit > 0) {
            return String.format("盈利 +%d元", profit);
        } else if (profit < 0) {
            return String.format("亏损 %d元", profit);
        }
        return "持平";
    }
}
