package top.liewyoung.strategy.gameEvent.events.fate;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 生病住院事件
 */
public class HospitalEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.FATE;
    }

    @Override
    public String getName() {
        return "生病住院";
    }

    @Override
    public String getDescription() {
        return "生病住院需要支付医疗费用";
    }

    @Override
    public void execute(EventContext ctx) {
        int cost = ctx.randomInt(400, 1000);
        if (ctx.canAfford(cost)) {
            ctx.deductCash(cost);
            ctx.showMessage(
                    String.format("命运事件：生病住院！\n医疗费用：%d元\n当前现金：%d元", cost, ctx.getCash()),
                    "医疗支出",
                    MessageType.WARNING);
        } else {
            ctx.showMessage("命运事件：生病住院！\n资金不足支付医疗费用，需要借款。", "医疗危机", MessageType.ERROR);
        }
    }
}
