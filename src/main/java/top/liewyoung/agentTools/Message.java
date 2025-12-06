package top.liewyoung.agentTools;

import com.fasterxml.jackson.annotation.JsonProperty;
import top.liewyoung.agentTools.Exceptions.RoleInvalid;

public class Message {
    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;

    /**
     *
     * @param role 枚举
     * @param content 对话内容
     */
    public Message(Role role, String content) {
        this.role = switch (role) {
            case Role.SYSTEM -> "system";
            case Role.ASSISTANT ->  "assistant";
            default -> "user";
        };
        this.content = content;
    }

    public Message(String role, String content) throws RoleInvalid {
        switch (role) {
            case "system" -> this.role = "system";
            case "assistant" -> this.role = "assistant";
            case "user" -> this.role = "user";
            default -> throw new RoleInvalid(role);
        }
        this.content = content;
    }
}