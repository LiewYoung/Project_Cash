package top.liewyoung.strategy.gameEvent.events.market;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 股市大涨事件
 */
public class StockMarketEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.MARKET;
    }

    @Override
    public String getName() {
        return "股市大涨";
    }

    @Override
    public String getDescription() {
        return "股市大涨，获得额外收益";
    }

    @Override
    public void execute(EventContext ctx) {
        int stockGain = ctx.randomInt(300, 700);
        ctx.addCash(stockGain);
        ctx.showMessage(
                String.format("市场事件：股市大涨！\n获得收益：%d元\n当前现金：%d元", stockGain, ctx.getCash()),
                "股市大涨",
                MessageType.INFO);
    }
}
