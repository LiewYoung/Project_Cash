package top.liewyoung.professions;

/**
 * 总裁职业 - 极高收入，高支出
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class President implements Profession {
    
    private static final String NAME = "企业总裁";
    private static final int SALARY = 23500;        // 月工资
    private static final int EXPENSES = 18500;      // 月支出
    private static final int INITIAL_CASH = 1000;   // 初始现金
    private static final int INITIAL_SAVINGS = 5000;// 初始储蓄
    
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
        return "作为一家公司的总裁，你拥有令人羡慕的高收入。" +
               "但是，维持高端生活方式和社交需求意味着你的支出也非常惊人。" +
               "财务自由的门槛对你来说更高。";
    }
    
    @Override
    public String toString() {
        return String.format("%s [工资: $%d, 支出: $%d, 现金流: $%d]", 
            NAME, SALARY, EXPENSES, getMonthlyCashFlow());
    }
}
