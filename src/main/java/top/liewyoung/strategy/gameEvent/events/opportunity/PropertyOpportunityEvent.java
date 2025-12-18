package top.liewyoung.strategy.gameEvent.events.opportunity;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.asset.impl.PropertyAsset;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 房地产投资机会事件
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class PropertyOpportunityEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.OPPORTUNITY;
    }

    @Override
    public String getName() {
        return "房地产投资";
    }

    @Override
    public String getDescription() {
        return "购买房产获取租金收入的机会";
    }

    @Override
    public void execute(EventContext ctx) {
        int propertyCost = ctx.randomInt(2000, 5000);
        int propertyIncome = ctx.randomInt(300, 700);

        if (ctx.canAfford(propertyCost)) {
            String[] options = { "购买 (" + propertyCost + "元)", "放弃" };
            int choice = ctx.showOptions(
                    String.format("机会：房地产投资\n购买金额：%d元\n月租金收入：%d元\n是否购买？", propertyCost, propertyIncome),
                    "房地产机会",
                    options,
                    MessageType.QUESTION);

            if (choice == 0) {
                ctx.deductCash(propertyCost);
                ctx.addPassiveIncome(propertyIncome);
                // 创建房产资产并添加到资产管理器
                String propertyName = "房产" + ctx.randomInt(10, 99) + "号";
                PropertyAsset property = new PropertyAsset(propertyName, propertyCost, propertyIncome);
                ctx.addAsset(property);
                ctx.showMessage(
                        String.format("购买成功！\n房产：%s\n价值：%d元\n月租金：%d元\n当前现金：%d元",
                                propertyName, propertyCost, propertyIncome, ctx.getCash()),
                        "购买成功",
                        MessageType.INFO);
            }
        } else {
            ctx.showMessage(
                    String.format("资金不足！\n需要：%d元\n当前现金：%d元", propertyCost, ctx.getCash()),
                    "资金不足",
                    MessageType.WARNING);
        }
    }
}
