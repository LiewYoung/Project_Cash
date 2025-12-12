package top.liewyoung.professions;

/**
 * 公务员职业 - 稳定收入，中等支出
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class Government implements Profession {
    
    private static final String NAME = "公务员";
    private static final int SALARY = 4600;         // 月工资
    private static final int EXPENSES = 3550;       // 月支出
    private static final int INITIAL_CASH = 500;    // 初始现金
    private static final int INITIAL_SAVINGS = 1200;// 初始储蓄
    
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
        return "作为一名公务员，你拥有稳定的收入和良好的福利保障。" +
               "虽然收入不是最高，但稳定性让你可以更从容地规划财务未来。";
    }
    
    @Override
    public String toString() {
        return String.format("%s [工资: $%d, 支出: $%d, 现金流: $%d]", 
            NAME, SALARY, EXPENSES, getMonthlyCashFlow());
    }
}
