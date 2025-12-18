package top.liewyoung.strategy.gameEvent.events.opportunity;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.asset.impl.BusinessAsset;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 创办企业机会事件
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class BusinessOpportunityEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.OPPORTUNITY;
    }

    @Override
    public String getName() {
        return "创办企业";
    }

    @Override
    public String getDescription() {
        return "创办企业获得被动收入的机会";
    }

    @Override
    public void execute(EventContext ctx) {
        int businessCost = ctx.randomInt(1500, 2500);
        int businessIncome = ctx.randomInt(200, 500);

        if (ctx.canAfford(businessCost)) {
            String[] options = { "创业 (" + businessCost + "元)", "放弃" };
            int choice = ctx.showOptions(
                    String.format("机会：创办企业\n投入资金：%d元\n月收入：%d元\n是否创业？", businessCost, businessIncome),
                    "企业创办机会",
                    options,
                    MessageType.QUESTION);

            if (choice == 0) {
                ctx.deductCash(businessCost);
                ctx.addPassiveIncome(businessIncome);
                // 创建企业资产并添加到资产管理器
                String businessName = "企业" + ctx.randomInt(100, 999);
                BusinessAsset business = new BusinessAsset(businessName, businessCost, businessIncome);
                ctx.addAsset(business);
                ctx.showMessage(
                        String.format("创业成功！\n企业：%s\n价值：%d元\n月收入：%d元\n当前现金：%d元",
                                businessName, businessCost, businessIncome, ctx.getCash()),
                        "创业成功",
                        MessageType.INFO);
            }
        } else {
            ctx.showMessage(
                    String.format("资金不足！\n需要：%d元\n当前现金：%d元", businessCost, ctx.getCash()),
                    "资金不足",
                    MessageType.WARNING);
        }
    }
}
