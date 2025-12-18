package top.liewyoung.strategy.gameEvent.events.market;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 房价上涨事件
 */
public class PropertyMarketEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.MARKET;
    }

    @Override
    public String getName() {
        return "房价上涨";
    }

    @Override
    public String getDescription() {
        return "房价上涨，租金收入增加";
    }

    @Override
    public void execute(EventContext ctx) {
        int rentIncrease = ctx.randomInt(100, 300);
        ctx.addPassiveIncome(rentIncrease);
        ctx.showMessage(
                String.format("市场事件：房价上涨！\n租金收入增加：%d元\n当前月租金：%d元", rentIncrease, ctx.getPassiveIncome()),
                "房价上涨",
                MessageType.INFO);
    }
}
