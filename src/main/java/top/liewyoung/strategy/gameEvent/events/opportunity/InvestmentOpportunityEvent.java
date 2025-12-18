package top.liewyoung.strategy.gameEvent.events.opportunity;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 投资项目机会事件
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class InvestmentOpportunityEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.OPPORTUNITY;
    }

    @Override
    public String getName() {
        return "投资项目";
    }

    @Override
    public String getDescription() {
        return "投资项目获得回报的机会";
    }

    @Override
    public void execute(EventContext ctx) {
        int investmentCost = ctx.randomInt(800, 1500);
        int investmentReturn = ctx.randomInt(1000, 1500);

        String[] options = { "投资 (" + investmentCost + "元)", "放弃" };
        int choice = ctx.showOptions(
                String.format("机会：投资项目\n投资金额：%d元\n预期回报：%d元\n是否投资？", investmentCost, investmentReturn),
                "投资机会",
                options,
                MessageType.QUESTION);

        if (choice == 0) {
            if (ctx.canAfford(investmentCost)) {
                ctx.deductCash(investmentCost);
                ctx.addCash(investmentReturn);
                ctx.showMessage(
                        String.format("投资成功！\n回报：%d元\n当前现金：%d元", investmentReturn, ctx.getCash()),
                        "投资成功",
                        MessageType.INFO);
            } else {
                ctx.showMessage(
                        String.format("资金不足！\n需要：%d元\n当前现金：%d元", investmentCost, ctx.getCash()),
                        "资金不足",
                        MessageType.WARNING);
            }
        }
    }
}
