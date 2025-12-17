# EventProcessor 事件处理器使用说明

## 概述

EventProcessor 是 CashFlow 游戏的事件处理核心组件，负责处理玩家走到不同格子上时触发的各种事件。它为游戏增加了丰富的互动性和随机性，使游戏体验更加生动有趣。

## 功能特性

### 支持的事件类型

1. **起点事件 (START)**
   - 玩家经过起点时获得工资
   - 自动更新现金余额

2. **机会事件 (OPPORTUNITY)**
   - 小生意投资机会
   - 额外工作收入
   - 股票投资机会
   - 房地产投资机会

3. **市场事件 (MARKET)**
   - 资产升值/贬值
   - 市场紧急支出
   - 市场波动影响

4. **命运事件 (FATE)**
   - 中彩票
   - 继承遗产
   - 遭遇盗窃
   - 生病住院
   - 遇到贵人
   - 意外之财

5. **银行事件 (BANK)**
   - 存款/取款
   - 贷款/还款
   - 金融业务操作

6. **趣味游戏事件 (FUNGAME)**
   - 猜数字游戏
   - 知识问答
   - 掷硬币游戏

## 集成方式

### 1. 基本使用

```java
// 创建玩家
Player player = new Player("普通职员", 1000, 2000, 1000);

// 创建事件处理器
EventProcessor eventProcessor = new EventProcessor(player);

// 处理事件
eventProcessor.processEvent(TitlesTypes.START);
eventProcessor.processEvent(TitlesTypes.OPPORTUNITY);
```

### 2. 与 DashboardPanel 集成

EventProcessor 已经集成到 DashboardPanel 中。当玩家摇骰子移动时，会自动触发对应格子的事件：

```java
// 在 DashboardPanel 中初始化
private void initializeDefaultPlayer() {
    Player defaultPlayer = new Player("普通职员", 1000, 2000, 1000);
    setCurrentPlayer(defaultPlayer);
}

// 在 diceEvent 方法中触发事件
public void diceEvent() {
    // ... 移动逻辑 ...
    
    // 获取当前位置的格子类型
    TitlesTypes currentType = map.getType(x, y);
    
    // 触发事件处理
    if (eventProcessor != null) {
        eventProcessor.processEvent(currentType);
        infoPanel.refreshData(); // 更新玩家信息显示
    }
}
```

### 3. 玩家信息显示

EventProcessor 提供了玩家现金流信息的获取方法：

```java
// 获取玩家详细信息
String playerInfo = eventProcessor.getPlayerCashflowInfo();
// 返回格式：职业：普通职员\n现金：1000元\n工资：2000元\n月支出：1000元\n被动收入：0元\n现金流：1000元
```

## 事件处理逻辑

### 随机性设计

每个事件类型都有多种可能的子事件，通过随机数选择：

1. **机会事件**：4种可能（小生意、额外收入、股票、房地产）
2. **市场事件**：3种可能（资产升值、资产贬值、紧急支出）
3. **命运事件**：6种可能（中彩票、继承遗产、遭遇盗窃、生病住院、遇到贵人、意外之财）
4. **趣味游戏**：3种可能（猜数字、知识问答、掷硬币）

### 玩家交互

大多数事件都包含玩家选择：
- 投资机会需要玩家确认是否投资
- 银行事件提供多种业务选择
- 趣味游戏需要玩家参与互动

### 财务影响

事件会影响玩家的财务状况：
- **现金变化**：工资、投资、支出等
- **被动收入变化**：资产投资、市场波动等
- **现金流计算**：实时更新现金流状态

## 测试模式

EventProcessor 支持测试模式，避免 GUI 对话框阻塞自动化测试：

```java
// 创建测试模式的事件处理器
EventProcessor testProcessor = new EventProcessor(player, true);

// 在测试模式下，不会显示任何对话框
testProcessor.processEvent(TitlesTypes.START);
```

## 扩展和自定义

### 添加新事件类型

1. 在 `TitlesTypes` 枚举中添加新类型
2. 在 `EventProcessor` 的 `processEvent` 方法中添加新的 case
3. 实现对应的事件处理方法

### 修改事件概率

调整随机数范围来改变事件出现的概率：

```java
// 修改机会事件的子事件数量
int eventType = random.nextInt(4); // 当前是4种可能
```

### 自定义事件效果

修改具体事件方法的逻辑来调整：
- 投资金额范围
- 收益/损失比例
- 游戏难度

## 示例代码

### 完整游戏循环示例

```java
public class GameExample {
    public static void main(String[] args) {
        // 初始化游戏
        Player player = new Player("医生", 5000, 5000, 3000);
        EventProcessor processor = new EventProcessor(player);
        
        // 模拟游戏过程
        System.out.println("=== 游戏开始 ===");
        System.out.println(processor.getPlayerCashflowInfo());
        
        // 经过起点
        processor.processEvent(TitlesTypes.START);
        System.out.println("\n经过起点后：");
        System.out.println(processor.getPlayerCashflowInfo());
        
        // 遇到机会
        processor.processEvent(TitlesTypes.OPPORTUNITY);
        System.out.println("\n遇到机会后：");
        System.out.println(processor.getPlayerCashflowInfo());
        
        // 市场波动
        processor.processEvent(TitlesTypes.MARKET);
        System.out.println("\n市场波动后：");
        System.out.println(processor.getPlayerCashflowInfo());
    }
}
```

### 事件处理器演示程序

项目包含一个完整的演示程序 `EventProcessorDemo`，展示了所有事件类型的效果：

```bash
# 运行演示程序
java top.liewyoung.view.tools.EventProcessorDemo
```

## 注意事项

1. **线程安全**：EventProcessor 不是线程安全的，应在 Swing 事件分发线程中使用
2. **玩家状态**：确保在处理事件前设置了正确的玩家对象
3. **GUI 依赖**：标准模式依赖 Swing 的 JOptionPane 显示对话框
4. **测试模式**：自动化测试时应使用测试模式避免对话框阻塞

## 故障排除

### 常见问题

1. **事件没有触发**
   - 检查玩家对象是否已设置
   - 验证 TitlesTypes 枚举值是否正确
   - 确认 processEvent 方法被正确调用

2. **对话框不显示**
   - 确保不在测试模式下
   - 检查是否在 Swing 事件分发线程中
   - 验证 JOptionPane 没有被其他窗口遮挡

3. **玩家信息不更新**
   - 调用 refreshData() 方法更新显示
   - 检查玩家对象的 getter 方法
   - 验证现金流计算逻辑

### 调试建议

1. 启用详细日志记录
2. 使用测试模式进行单元测试
3. 逐步调试事件处理流程
4. 验证随机数生成范围

## 版本历史

- v1.0 (2025/12/17): 初始版本，支持6种基本事件类型
- v1.1: 添加测试模式支持
- v1.2: 集成到 DashboardPanel
- v1.3: 添加玩家信息显示功能

## 贡献指南

欢迎提交改进建议和 bug 报告。在修改代码时，请确保：
1. 保持向后兼容性
2. 添加相应的单元测试
3. 更新文档说明
4. 遵循项目代码风格

## 许可证

本项目采用 MIT 许可证。详情见项目根目录的 LICENSE 文件。