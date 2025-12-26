package top.liewyoung.thanos

data class Command(val name: String, val obj: Any)

/**
 * 这是一个注册器，允许用户注册命令，但是你不应当调用他
 * <b>无论在什么情况下你都只应当通过 CommandProcessor 来处理命令 </b>
 *
 *@author LiewYoung
 *@since 2025/12/22
 */
class CommandRegistry {
    /*命令注册表*/
    private val commands = CommandConfig;
    private var engine: Python3Engine? = null;

    constructor(engine: Python3Engine?){
        this.engine = engine
        engine?.setRegistry(this)
    }

    /**
     * 全部注册
     * @param [command] 指令实体
     */
    fun registerAll(vararg command: Command) {
        for (c in command) {
            commands.addCommand(c)
            engine?.inject(c.name, c.obj)
        }
    }

    /**
     * 注册单个命令
     * @param [command] 命令
     */
    fun register(command: Command) {
        commands.addCommand(command)
        engine?.inject(command.name, command.obj)
    }

    /** 修改引擎
     * <b>无需管理命令注入，我们使用单例共享数据</b>
     * @param [engine] 发动机
     */
    fun changeEngine(engine: Python3Engine){
        this.engine = engine
        val config = commands.getCommands();
        for (c in config) {
            engine.inject(c.name, c.obj)
        }
    }
}