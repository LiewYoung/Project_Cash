package top.liewyoung.strategy.gameEvent.events;

import top.liewyoung.strategy.TitlesTypes;
import top.liewyoung.strategy.gameEvent.EventContext;
import top.liewyoung.strategy.gameEvent.GameEvent;
import top.liewyoung.view.component.MDialog.MessageType;

/**
 * 起点事件 - 玩家经过起点时获得工资
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public class StartEvent implements GameEvent {

    @Override
    public TitlesTypes getType() {
        return TitlesTypes.START;
    }

    @Override
    public String getName() {
        return "起点事件";
    }

    @Override
    public String getDescription() {
        return "经过起点获得工资";
    }

    @Override
    public void execute(EventContext ctx) {
        int salary = ctx.getSalary();
        ctx.addCash(salary);

        ctx.showMessage(
                String.format("经过起点！\n获得工资：%d元\n当前现金：%d元", salary, ctx.getCash()),
                "起点事件",
                MessageType.INFO);
    }
}
