package top.liewyoung.strategy.gameEvent.events.opportunity;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.asset.impl.StockAsset;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 股票投资机会事件
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class StockOpportunityEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.OPPORTUNITY;
    }

    @Override
    public String getName() {
        return "股票投资";
    }

    @Override
    public String getDescription() {
        return "投资股票获取收益的机会";
    }

    @Override
    public void execute(EventContext ctx) {
        int stockCost = ctx.randomInt(500, 1000);
        int potentialGain = ctx.randomInt(200, 500);

        if (ctx.canAfford(stockCost)) {
            String[] options = { "投资 (" + stockCost + "元)", "放弃" };
            int choice = ctx.showOptions(
                    String.format("机会：股票投资\n投资金额：%d元\n潜在收益：%d元\n是否投资？", stockCost, potentialGain),
                    "股票投资机会",
                    options,
                    MessageType.QUESTION);

            if (choice == 0) {
                ctx.deductCash(stockCost);
                // 创建股票资产并添加到资产管理器
                String stockName = "股票" + ctx.randomInt(1000, 9999);
                StockAsset stock = new StockAsset(stockName, stockCost);
                ctx.addAsset(stock);
                ctx.showMessage(
                        String.format("投资成功！\n购买：%s\n价值：%d元\n当前现金：%d元", stockName, stockCost, ctx.getCash()),
                        "投资成功",
                        MessageType.INFO);
            }
        } else {
            ctx.showMessage(
                    String.format("资金不足！\n需要：%d元\n当前现金：%d元", stockCost, ctx.getCash()),
                    "资金不足",
                    MessageType.WARNING);
        }
    }
}
