package top.liewyoung.strategy.gameEvent.events.fungame;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 编程知识问答事件
 */
public class QuizEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FUNGAME;
    }

    @Override
    public String getName() {
        return "编程问答";
    }

    @Override
    public String getDescription() {
        return "回答编程问题获得奖励";
    }

    @Override
    public void execute(EventContext ctx) {
        String[] answers = { "Java", "Python", "C++", "JavaScript" };
        int correctIndex = 0; // Java是正确答案

        int choice = ctx.showOptions(
                "趣味问答：\n哪种编程语言最初被称为'Oak'？",
                "编程知识问答",
                answers,
                MessageType.QUESTION);

        if (choice == correctIndex) {
            int prize = ctx.randomInt(150, 400);
            ctx.addCash(prize);
            ctx.showMessage(
                    String.format("回答正确！\nJava最初被称为'Oak'。\n获得奖金：%d元\n当前现金：%d元", prize, ctx.getCash()),
                    "回答正确",
                    MessageType.INFO);
        } else {
            ctx.showMessage(
                    String.format("回答错误！\n正确答案是：Java\nJava最初被称为'Oak'。"),
                    "回答错误",
                    MessageType.INFO);
        }
    }
}
