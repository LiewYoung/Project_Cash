package top.liewyoung.professions;

/**
 * 牙医职业 - 高收入，中等支出
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class Dentist implements Profession {
    
    private static final String NAME = "牙医";
    private static final int SALARY = 11200;        // 月工资
    private static final int EXPENSES = 8400;       // 月支出
    private static final int INITIAL_CASH = 400;    // 初始现金
    private static final int INITIAL_SAVINGS = 2800;// 初始储蓄
    
    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public int getSalary() {
        return SALARY;
    }
    
    @Override
    public int getExpenses() {
        return EXPENSES;
    }
    
    @Override
    public int getInitialCash() {
        return INITIAL_CASH;
    }
    
    @Override
    public int getInitialSavings() {
        return INITIAL_SAVINGS;
    }
    
    @Override
    public String getDescription() {
        return "作为一名牙医，你有着不错的专业收入。" +
               "开设自己的诊所需要大量投资，但也可能带来更高的回报。";
    }
    
    @Override
    public String toString() {
        return String.format("%s [工资: $%d, 支出: $%d, 现金流: $%d]", 
            NAME, SALARY, EXPENSES, getMonthlyCashFlow());
    }
}
