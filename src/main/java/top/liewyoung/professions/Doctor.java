package top.liewyoung.professions;

/**
 * 医生职业 - 高收入，中等支出
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class Doctor implements Profession {
    
    private static final String NAME = "医生";
    private static final int SALARY = 13200;        // 月工资
    private static final int EXPENSES = 9650;       // 月支出
    private static final int INITIAL_CASH = 400;    // 初始现金
    private static final int INITIAL_SAVINGS = 3200; // 初始储蓄
    
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
        return "作为一名医生，你有着稳定的高收入，但医学院的贷款和高生活标准也带来了不小的支出。" +
               "你需要找到增加被动收入的方法来实现财务自由。";
    }
    
    @Override
    public String toString() {
        return String.format("%s [工资: $%d, 支出: $%d, 现金流: $%d]", 
            NAME, SALARY, EXPENSES, getMonthlyCashFlow());
    }
}
