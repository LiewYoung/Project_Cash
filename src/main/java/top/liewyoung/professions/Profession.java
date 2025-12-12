package top.liewyoung.professions;

/**
 * 职业接口 - 定义职业的基本属性
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public interface Profession {
    
    /**
     * 获取职业名称
     * @return 职业名称
     */
    String getName();
    
    /**
     * 获取月工资
     * @return 月工资
     */
    int getSalary();
    
    /**
     * 获取月支出
     * @return 月支出
     */
    int getExpenses();
    
    /**
     * 获取初始现金
     * @return 初始现金
     */
    int getInitialCash();
    
    /**
     * 获取初始储蓄
     * @return 初始储蓄
     */
    int getInitialSavings();
    
    /**
     * 获取月现金流（工资 - 支出）
     * @return 月现金流
     */
    default int getMonthlyCashFlow() {
        return getSalary() - getExpenses();
    }
    
    /**
     * 获取职业描述
     * @return 职业描述
     */
    String getDescription();
}
