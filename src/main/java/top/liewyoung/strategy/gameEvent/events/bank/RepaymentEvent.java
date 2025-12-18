package top.liewyoung.strategy.gameEvent.events.bank;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 还款事件
 */
public class RepaymentEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.BANK;
    }

    @Override
    public String getName() {
        return "银行还款";
    }

    @Override
    public String getDescription() {
        return "偿还银行贷款";
    }

    @Override
    public void execute(EventContext ctx) {
        ctx.showMessage("银行事件：还款\n本月无需还款。", "银行还款", MessageType.INFO);
    }
}
