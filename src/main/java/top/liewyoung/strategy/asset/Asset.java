package top.liewyoung.strategy.asset;

import java.util.Random;

/**
 * 资产接口
 * 定义所有资产类型必须实现的方法
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public interface Asset {

    /**
     * 获取资产名称
     */
    String getName();

    /**
     * 获取资产类型
     */
    AssetType getType();

    /**
     * 获取购买价格
     */
    int getPurchasePrice();

    /**
     * 获取当前价值
     */
    int getCurrentValue();

    /**
     * 获取月收入（被动收入）
     */
    int getMonthlyIncome();

    /**
     * 获取波动率（-1到1之间，正数表示可能增值，负数表示可能贬值）
     */
    double getVolatility();

    /**
     * 更新资产价值（每次骰子事件后调用）
     * 
     * @param random 随机数生成器
     */
    void updateValue(Random random);

    /**
     * 获取资产状态描述
     */
    String getStatus();

    /**
     * 获取贬值/增值率描述
     */
    default String getDepreciationRate() {
        double volatility = getVolatility();
        if (volatility > 0) {
            return String.format("+%.0f%%", volatility * 100);
        } else if (volatility < 0) {
            return String.format("%.0f%%", volatility * 100);
        }
        return "0%";
    }
}
