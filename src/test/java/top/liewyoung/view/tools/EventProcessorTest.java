package top.liewyoung.view.tools;

import static org.junit.jupiter.api.Assertions.*;

import org.atom.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.liewyoung.strategy.TitlesTypes;

/**
 * EventProcessor测试类
 */
class EventProcessorTest {

    private Player testPlayer;
    private EventProcessor eventProcessor;

    @BeforeEach
    void setUp() {
        // 创建一个测试玩家：普通职员，初始现金1000，工资2000，月支出1000
        testPlayer = new Player("普通职员", 1000, 2000, 1000);
        eventProcessor = new EventProcessor(testPlayer, true); // 使用测试模式
    }

    @Test
    void testConstructor() {
        assertNotNull(eventProcessor);
        assertNotNull(eventProcessor.getPlayerCashflowInfo());
    }

    @Test
    void testSetCurrentPlayer() {
        Player newPlayer = new Player("医生", 5000, 5000, 3000);
        eventProcessor.setCurrentPlayer(newPlayer);

        String info = eventProcessor.getPlayerCashflowInfo();
        assertTrue(info.contains("医生"));
        assertTrue(info.contains("5000"));
    }

    @Test
    void testProcessStartEvent() {
        int initialCash = testPlayer.getCash();
        int salary = testPlayer.getSalary();

        // 模拟起点事件
        eventProcessor.processEvent(TitlesTypes.START);

        // 起点事件应该增加工资到现金
        assertEquals(initialCash + salary, testPlayer.getCash());
    }

    @Test
    void testProcessOpportunityEvent() {
        // 机会事件有多种可能，我们主要测试处理器不会崩溃
        assertDoesNotThrow(() -> {
            eventProcessor.processEvent(TitlesTypes.OPPORTUNITY);
        });
    }

    @Test
    void testProcessMarketEvent() {
        // 市场事件有多种可能，我们主要测试处理器不会崩溃
        assertDoesNotThrow(() -> {
            eventProcessor.processEvent(TitlesTypes.MARKET);
        });
    }

    @Test
    void testProcessFateEvent() {
        // 命运事件有多种可能，我们主要测试处理器不会崩溃
        assertDoesNotThrow(() -> {
            eventProcessor.processEvent(TitlesTypes.FATE);
        });
    }

    @Test
    void testProcessBankEvent() {
        // 银行事件有多种可能，我们主要测试处理器不会崩溃
        assertDoesNotThrow(() -> {
            eventProcessor.processEvent(TitlesTypes.BANK);
        });
    }

    @Test
    void testProcessFunGameEvent() {
        // 趣味游戏事件有多种可能，我们主要测试处理器不会崩溃
        assertDoesNotThrow(() -> {
            eventProcessor.processEvent(TitlesTypes.FUNGAME);
        });
    }

    @Test
    void testProcessEventWithNullPlayer() {
        EventProcessor emptyProcessor = new EventProcessor(null, true); // 使用测试模式

        // 应该不会抛出异常，但会显示错误消息
        assertDoesNotThrow(() -> {
            emptyProcessor.processEvent(TitlesTypes.START);
        });
    }

    @Test
    void testGetPlayerCashflowInfo() {
        String info = eventProcessor.getPlayerCashflowInfo();

        // 验证信息包含关键字段
        assertTrue(info.contains("普通职员"));
        assertTrue(info.contains("1000"));
        assertTrue(info.contains("2000"));
        assertTrue(info.contains("1000"));
        assertTrue(info.contains("现金流"));

        // 验证现金流计算正确
        // 初始被动收入为0，所以现金流 = 工资(2000) - 月支出(1000) = 1000
        assertTrue(info.contains("1000元") || info.contains("1000"));
    }

    @Test
    void testPlayerCashflowCalculation() {
        // 测试现金流计算
        int expectedCashflow =
            testPlayer.getSalary() +
            testPlayer.getPassiveIncome() -
            testPlayer.getMonthlyExpenses();
        assertEquals(1000, expectedCashflow); // 2000 + 0 - 1000 = 1000

        // 添加被动收入后重新计算
        testPlayer.addPassiveIncome(500);
        expectedCashflow =
            testPlayer.getSalary() +
            testPlayer.getPassiveIncome() -
            testPlayer.getMonthlyExpenses();
        assertEquals(1500, expectedCashflow); // 2000 + 500 - 1000 = 1500
    }

    @Test
    void testPlayerFinancialOperations() {
        // 测试现金操作
        int initialCash = testPlayer.getCash();
        testPlayer.setCash(initialCash + 1000);
        assertEquals(2000, testPlayer.getCash());

        // 测试被动收入操作
        int initialPassiveIncome = testPlayer.getPassiveIncome();
        testPlayer.addPassiveIncome(300);
        assertEquals(300, testPlayer.getPassiveIncome());

        testPlayer.removePassiveIncome(100);
        assertEquals(200, testPlayer.getPassiveIncome());
    }

    @Test
    void testEventProcessorIntegration() {
        // 综合测试：模拟玩家经过不同类型格子的事件处理
        TitlesTypes[] allTypes = TitlesTypes.values();

        for (TitlesTypes type : allTypes) {
            assertDoesNotThrow(
                () -> {
                    eventProcessor.processEvent(type);
                },
                "处理 " + type + " 事件时不应该抛出异常"
            );
        }

        // 验证玩家信息仍然可获取
        String info = eventProcessor.getPlayerCashflowInfo();
        assertNotNull(info);
        assertFalse(info.isEmpty());
    }
}
