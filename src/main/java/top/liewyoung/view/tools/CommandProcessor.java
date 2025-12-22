package top.liewyoung.view.tools;

import top.liewyoung.thanos.Command;
import top.liewyoung.thanos.CommandConfig;
import top.liewyoung.thanos.Python3Engine;

/**
 * <h1>命令注册器</h1>
 * <h2>解释</h2>
 * 命令注册器是基于实例的，也就是说你<b>应当且仅当</b>在创建预定修改实例时才应该调用
 *
 * @author LiewYoung
 * @since 2025/12/22
 */
public class CommandProcessor {
    private final CommandConfig config = CommandConfig.INSTANCE;
    private final Python3Engine engine;

    public CommandProcessor(Python3Engine engine) {
        this.engine = engine;
        this.engine.restart();
    }

    /**
     * add 命令
     *
     * @param command 命令
     */
    public void addCommand(Command command) {
        config.addCommand(command);
        engine.restart();
    }

    /**
     * 添加命令(批量）
     *
     * @param commands 命令
     */
    public void addCommands(Command...commands){

        for(Command command:commands){
            config.addCommand(command);
        }
        engine.restart();

    }

}
