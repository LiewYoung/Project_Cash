package top.liewyoung.professions;

/**
 * 教师职业 - 中等收入，低支出
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class Teacher implements Profession {
    
    private static final String NAME = "教师";
    private static final int SALARY = 3300;         // 月工资
    private static final int EXPENSES = 2650;       // 月支出
    private static final int INITIAL_CASH = 400;    // 初始现金
    private static final int INITIAL_SAVINGS = 800; // 初始储蓄
    
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
        return "作为一名教师，你的工资虽然不高，但支出也相对较低。" +
               "稳定但有限的收入意味着你需要更加谨慎地管理财务，寻找合适的投资机会。";
    }
    
    @Override
    public String toString() {
        return String.format("%s [工资: $%d, 支出: $%d, 现金流: $%d]", 
            NAME, SALARY, EXPENSES, getMonthlyCashFlow());
    }
}
