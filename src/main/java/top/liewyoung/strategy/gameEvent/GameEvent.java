package top.liewyoung.strategy.gameEvent;

import top.liewyoung.strategy.TitlesTypes;

/**
 * 游戏事件接口
 * 所有游戏事件都需要实现此接口
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public interface GameEvent {

    /**
     * 获取事件类型
     * 
     * @return 事件对应的格子类型
     */
    TitlesTypes getType();

    /**
     * 获取事件名称
     * 
     * @return 事件名称
     */
    String getName();

    /**
     * 获取事件描述
     * 
     * @return 事件描述
     */
    String getDescription();

    /**
     * 执行事件
     * 
     * @param ctx 事件执行上下文
     */
    void execute(EventContext ctx);
}
