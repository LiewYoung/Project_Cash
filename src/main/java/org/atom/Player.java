package org.atom;

import top.liewyoung.strategy.asset.AssetManager;

public class Player {
    private String profession; // 职业
    private int cash; // 现金
    private int salary; // 工资
    private int monthlyExpenses; // 月支出
    private int passiveIncome; // 被动收入
    private AssetManager assetManager; // 资产管理器

    public Player(String profession, int cash, int salary, int monthlyExpenses) {
        this.profession = profession;
        this.cash = cash;
        this.salary = salary;
        this.monthlyExpenses = monthlyExpenses;
        this.passiveIncome = 0;
        this.assetManager = new AssetManager();
    }

    public String getProfession() {
        return profession;
    }

    public int getCash() {
        return cash;
    }

    public int getSalary() {
        return salary;
    }

    public int getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public int getPassiveIncome() {
        return passiveIncome;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addPassiveIncome(int amount) {
        this.passiveIncome += amount;
    }

    public void removePassiveIncome(int amount) {
        this.passiveIncome -= amount;
    }

    // 计算现金流（包含资产月收入）
    public int calculateCashflow() {
        int assetIncome = assetManager != null ? assetManager.getTotalMonthlyIncome() : 0;
        return (salary + passiveIncome + assetIncome) - monthlyExpenses;
    }

    // 计算总资产价值
    public int getTotalAssetValue() {
        return assetManager != null ? assetManager.getTotalValue() : 0;
    }
}
