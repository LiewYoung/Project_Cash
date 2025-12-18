package top.liewyoung.strategy.gameEvent.events.bank;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 取款事件
 */
public class WithdrawEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.BANK;
    }

    @Override
    public String getName() {
        return "银行取款";
    }

    @Override
    public String getDescription() {
        return "从银行取款";
    }

    @Override
    public void execute(EventContext ctx) {
        int amount = ctx.randomInt(300, 1000);
        ctx.addCash(amount);
        ctx.showMessage(
                String.format("银行事件：取款\n取款金额：%d元\n当前现金：%d元", amount, ctx.getCash()),
                "银行取款",
                MessageType.INFO);
    }
}
