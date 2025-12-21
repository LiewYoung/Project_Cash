package top.liewyoung.agentTools;

/**
 * 统一
 *
 * @author LiewYoung
 * @since 2025/12/3
 */

public interface AgentDo {
    int getCurrentRound();
    void reset();
    int getMaxRounds();
    boolean isAgreed();
}
