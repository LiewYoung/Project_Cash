package top.liewyoung.strategy.gameEvent.events.fungame;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 反应游戏事件
 */
public class ReactionEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FUNGAME;
    }

    @Override
    public String getName() {
        return "反应游戏";
    }

    @Override
    public String getDescription() {
        return "测试反应速度获得奖励";
    }

    @Override
    public void execute(EventContext ctx) {
        ctx.showMessage("快速反应游戏！\n准备好后点击确定，然后尽快再次点击确定。", "反应游戏", MessageType.INFO);

        long startTime = System.currentTimeMillis();
        ctx.showMessage("点击确定开始计时...", "准备开始", MessageType.INFO);
        ctx.showMessage("点击确定停止计时！", "停止计时", MessageType.INFO);
        long endTime = System.currentTimeMillis();

        long reactionTime = endTime - startTime;
        int prize = 0;
        if (reactionTime < 500)
            prize = 300;
        else if (reactionTime < 1000)
            prize = 200;
        else if (reactionTime < 2000)
            prize = 100;

        if (prize > 0) {
            ctx.addCash(prize);
            ctx.showMessage(
                    String.format("反应时间：%d毫秒\n获得奖励：%d元\n当前现金：%d元", reactionTime, prize, ctx.getCash()),
                    "游戏结束",
                    MessageType.INFO);
        } else {
            ctx.showMessage(
                    String.format("反应时间：%d毫秒\n反应太慢了，下次加油！", reactionTime),
                    "游戏结束",
                    MessageType.INFO);
        }
    }
}
