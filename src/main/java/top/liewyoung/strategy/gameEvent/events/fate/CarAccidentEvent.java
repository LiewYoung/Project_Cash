package top.liewyoung.strategy.gameEvent.events.fate;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 车祸事件
 */
public class CarAccidentEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FATE;
    }

    @Override
    public String getName() {
        return "车祸损失";
    }

    @Override
    public String getDescription() {
        return "遭遇车祸需要支付维修费用";
    }

    @Override
    public void execute(EventContext ctx) {
        int loss = ctx.randomInt(500, 1300);
        if (ctx.canAfford(loss)) {
            ctx.deductCash(loss);
            ctx.showMessage(
                    String.format("命运事件：遭遇车祸！\n维修费用：%d元\n当前现金：%d元", loss, ctx.getCash()),
                    "车祸损失",
                    MessageType.WARNING);
        } else {
            ctx.showMessage("命运事件：遭遇车祸！\n资金不足支付维修费用，变卖资产。", "严重损失", MessageType.ERROR);
        }
    }
}
