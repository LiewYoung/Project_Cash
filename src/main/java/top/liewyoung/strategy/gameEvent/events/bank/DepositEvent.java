package top.liewyoung.strategy.gameEvent.events.bank;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 存款事件
 */
public class DepositEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.BANK;
    }

    @Override
    public String getName() {
        return "银行存款";
    }

    @Override
    public String getDescription() {
        return "存款到银行";
    }

    @Override
    public void execute(EventContext ctx) {
        int amount = ctx.randomInt(500, 1500);
        if (ctx.canAfford(amount)) {
            ctx.deductCash(amount);
            ctx.showMessage(
                    String.format("银行事件：存款\n存款金额：%d元\n当前现金：%d元", amount, ctx.getCash()),
                    "银行存款",
                    MessageType.INFO);
        }
    }
}
