package top.liewyoung.strategy.gameEvent.events.fungame;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 猜数字游戏事件
 */
public class GuessNumberEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FUNGAME;
    }

    @Override
    public String getName() {
        return "猜数字游戏";
    }

    @Override
    public String getDescription() {
        return "猜中数字获得奖励";
    }

    @Override
    public void execute(EventContext ctx) {
        int target = ctx.randomInt(1, 11); // 1-10
        String input = ctx.showInput("趣味游戏：猜数字（1-10）\n猜中有奖！", "猜数字游戏", "1");

        try {
            int guess = Integer.parseInt(input);
            if (guess == target) {
                int prize = ctx.randomInt(200, 500);
                ctx.addCash(prize);
                ctx.showMessage(
                        String.format("恭喜猜中！\n正确答案：%d\n获得奖金：%d元\n当前现金：%d元", target, prize, ctx.getCash()),
                        "游戏胜利",
                        MessageType.INFO);
            } else {
                ctx.showMessage(
                        String.format("猜错了！\n正确答案：%d\n你的答案：%d\n下次加油！", target, guess),
                        "游戏失败",
                        MessageType.INFO);
            }
        } catch (NumberFormatException e) {
            ctx.showMessage("请输入有效的数字！", "输入错误", MessageType.ERROR);
        }
    }
}
