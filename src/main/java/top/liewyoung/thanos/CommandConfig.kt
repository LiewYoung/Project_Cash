package top.liewyoung.thanos

/**
 * 全局单例
 * @author LiewYoung
 * @date 2025/12/22
 */
object CommandConfig {
    private val commandInfo = mutableSetOf<Command>()

    fun addCommand(command: Command) {
        commandInfo.add(command)
    }

    fun addMutableCommand(vararg commands: Command) {
        for(command in commands) {
            commandInfo.add(command)
        }
    }

    fun getCommands(): Set<Command> {
        return commandInfo
    }

}