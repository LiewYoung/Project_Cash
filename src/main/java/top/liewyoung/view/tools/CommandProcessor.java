package top.liewyoung.view.tools;

import top.liewyoung.thanos.Command;
import top.liewyoung.thanos.CommandConfig;
import top.liewyoung.thanos.Python3Engine;

public class CommandProcessor {
    private CommandConfig config = CommandConfig.INSTANCE;
    private Python3Engine engine;

    public CommandProcessor(Python3Engine engine) {
        this.engine = engine;
        this.engine.restart();
    }

    public void addCommand(Command command) {
        config.addCommand(command);
        engine.restart();
    }

    public void addCommands(Command...commands){

        for(Command command:commands){
            config.addCommand(command);
        }
        engine.restart();

    }

}
