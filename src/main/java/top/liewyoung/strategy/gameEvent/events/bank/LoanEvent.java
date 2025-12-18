package top.liewyoung.strategy.gameEvent.events.bank;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 贷款事件
 */
public class LoanEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.BANK;
    }

    @Override
    public String getName() {
        return "银行贷款";
    }

    @Override
    public String getDescription() {
        return "从银行贷款";
    }

    @Override
    public void execute(EventContext ctx) {
        int amount = ctx.randomInt(2000, 5000);
        String[] options = { "贷款 (" + amount + "元)", "放弃" };
        int choice = ctx.showOptions(
                String.format("银行事件：贷款\n贷款金额：%d元\n是否贷款？", amount),
                "银行贷款",
                options,
                MessageType.QUESTION);

        if (choice == 0) {
            ctx.addCash(amount);
            ctx.showMessage(
                    String.format("贷款成功！\n获得贷款：%d元\n当前现金：%d元", amount, ctx.getCash()),
                    "贷款成功",
                    MessageType.INFO);
        }
    }
}
