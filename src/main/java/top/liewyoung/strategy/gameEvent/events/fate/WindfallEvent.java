package top.liewyoung.strategy.gameEvent.events.fate;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 意外之财事件
 */
public class WindfallEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FATE;
    }

    @Override
    public String getName() {
        return "意外之财";
    }

    @Override
    public String getDescription() {
        return "意外获得一笔钱";
    }

    @Override
    public void execute(EventContext ctx) {
        int windfall = ctx.randomInt(1000, 3000);
        ctx.addCash(windfall);
        ctx.showMessage(
                String.format("命运事件：意外之财！\n获得：%d元\n当前现金：%d元", windfall, ctx.getCash()),
                "意外之财",
                MessageType.INFO);
    }
}
