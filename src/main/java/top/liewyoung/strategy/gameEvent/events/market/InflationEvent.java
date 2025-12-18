package top.liewyoung.strategy.gameEvent.events.market;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 通货膨胀事件
 */
public class InflationEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.MARKET;
    }

    @Override
    public String getName() {
        return "通货膨胀";
    }

    @Override
    public String getDescription() {
        return "通货膨胀导致现金贬值";
    }

    @Override
    public void execute(EventContext ctx) {
        int inflationLoss = ctx.randomInt(200, 500);
        if (ctx.canAfford(inflationLoss)) {
            ctx.deductCash(inflationLoss);
            ctx.showMessage(
                    String.format("市场事件：通货膨胀！\n现金贬值损失：%d元\n当前现金：%d元", inflationLoss, ctx.getCash()),
                    "通货膨胀",
                    MessageType.WARNING);
        }
    }
}
