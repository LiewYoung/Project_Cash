package top.liewyoung.strategy.gameEvent.events.fate;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 中彩票事件
 */
public class LotteryEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FATE;
    }

    @Override
    public String getName() {
        return "彩票大奖";
    }

    @Override
    public String getDescription() {
        return "中彩票获得大奖";
    }

    @Override
    public void execute(EventContext ctx) {
        int win = ctx.randomInt(5000, 15000);
        ctx.addCash(win);
        ctx.showMessage(
                String.format("命运事件：中彩票！\n获得大奖：%d元\n当前现金：%d元", win, ctx.getCash()),
                "彩票大奖",
                MessageType.INFO);
    }
}
