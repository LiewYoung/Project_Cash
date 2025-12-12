package top.liewyoung.professions;

import java.util.Random;

/**
 * 职业工厂类 - 用于创建职业实例
 * 
 * @author LiewYoung
 * @since 2025/12/10
 */
public class ProfessionFactory {
    
    private static final Random random = new Random();
    
    /**
     * 所有可用职业
     */
    public enum ProfessionType {
        DOCTOR,
        TEACHER,
        PRESIDENT,
        GOVERNMENT,
        DENTIST
    }
    
    /**
     * 根据类型创建职业
     * @param type 职业类型
     * @return 职业实例
     */
    public static Profession create(ProfessionType type) {
        return switch (type) {
            case DOCTOR -> new Doctor();
            case TEACHER -> new Teacher();
            case PRESIDENT -> new President();
            case GOVERNMENT -> new Government();
            case DENTIST -> new Dentist();
        };
    }
    
    /**
     * 随机创建一个职业
     * @return 随机职业实例
     */
    public static Profession createRandom() {
        ProfessionType[] types = ProfessionType.values();
        return create(types[random.nextInt(types.length)]);
    }
    
    /**
     * 获取所有职业列表
     * @return 所有职业数组
     */
    public static Profession[] getAllProfessions() {
        ProfessionType[] types = ProfessionType.values();
        Profession[] professions = new Profession[types.length];
        for (int i = 0; i < types.length; i++) {
            professions[i] = create(types[i]);
        }
        return professions;
    }
}
