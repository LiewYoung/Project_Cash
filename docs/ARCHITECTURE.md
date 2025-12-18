# Project Cash - 项目架构文档

## 概述

Project Cash 是一款基于 Java Swing 的现金流游戏，采用 Material Design 3 风格设计，实现了完整的事件系统和资产管理系统。

## 技术栈

- **Java 21** - 核心开发语言
- **Gradle** - 构建工具
- **FlatLaf** - Material Design 风格的 Look and Feel
- **JUnit 5** - 单元测试框架

## 目录结构

```
src/main/java/
├── org/atom/
│   └── Player.java                    # 玩家实体类
│
└── top/liewyoung/
    ├── strategy/                      # 策略层（游戏逻辑）
    │   ├── TitlesTypes.java           # 格子类型枚举
    │   ├── MapPostition.java          # 地图位置管理
    │   │
    │   ├── asset/                     # 资产管理模块
    │   │   ├── Asset.java             # 资产接口
    │   │   ├── AssetType.java         # 资产类型枚举
    │   │   ├── AssetManager.java      # 资产管理器
    │   │   └── impl/                  # 资产实现类
    │   │       ├── StockAsset.java    # 股票资产
    │   │       ├── BusinessAsset.java # 企业资产
    │   │       └── PropertyAsset.java # 房产资产
    │   │
    │   └── gameEvent/                 # 事件系统模块
    │       ├── GameEvent.java         # 事件接口
    │       ├── EventContext.java      # 事件上下文
    │       ├── EventRegistry.java     # 事件注册表
    │       ├── DefaultEventConfig.java # 默认事件配置
    │       └── events/                # 事件实现类
    │           ├── StartEvent.java    # 起点事件
    │           ├── opportunity/       # 机会事件
    │           ├── market/            # 市场事件
    │           ├── fate/              # 命运事件
    │           ├── bank/              # 银行事件
    │           └── fungame/           # 趣味游戏事件
    │
    └── view/                          # 视图层（UI）
        ├── ColorSystem/
        │   └── MaterialPalette.java   # Material Design 颜色系统
        │
        ├── component/                 # 自定义组件
        │   ├── MDialog.java           # Material 对话框
        │   └── MDbutton.java          # Material 按钮
        │
        ├── mainWindows/               # 主界面
        │   ├── HomePage.java          # 主页
        │   ├── DashboardPanel.java    # 仪表盘面板
        │   ├── MapDraw.java           # 地图绘制
        │   └── Setting.java           # 设置页面
        │
        └── tools/
            └── EventProcessor.java    # 事件处理器
```

## 核心模块

### 1. 事件系统 (gameEvent)

采用策略模式，支持动态注册和管理游戏事件。

```
GameEvent (接口)
    ├── getType()      # 事件类型
    ├── getName()      # 事件名称
    ├── getDescription() # 事件描述
    └── execute(EventContext) # 执行事件

EventRegistry
    ├── register()     # 注册事件
    ├── unregister()   # 注销事件
    └── getRandomEvent() # 随机获取事件

EventContext
    ├── showMessage()  # 显示消息
    ├── showOptions()  # 显示选项
    ├── addCash()      # 增加现金
    ├── deductCash()   # 扣除现金
    └── addAsset()     # 添加资产
```

**事件类型：**
| 类型 | 事件数量 | 说明 |
|-----|---------|------|
| START | 1 | 起点发工资 |
| OPPORTUNITY | 4 | 股票/企业/投资/房产 |
| MARKET | 3 | 股市/房价/通胀 |
| FATE | 4 | 意外/车祸/彩票/住院 |
| BANK | 4 | 存款/取款/贷款/还款 |
| FUNGAME | 4 | 猜数字/问答/反应/2048 |

### 2. 资产系统 (asset)

管理玩家的资产组合，支持价值波动。

```
Asset (接口)
    ├── getName()         # 资产名称
    ├── getType()         # 资产类型
    ├── getCurrentValue() # 当前价值
    ├── getMonthlyIncome() # 月收入
    ├── updateValue()     # 更新价值
    └── getStatus()       # 资产状态

AssetManager
    ├── addAsset()        # 添加资产
    ├── updateAllAssets() # 更新所有资产
    └── getTotalValue()   # 获取总价值
```

**资产类型：**
| 类型 | 波动率 | 月收入 |
|-----|--------|--------|
| StockAsset | ±20% | 无 |
| BusinessAsset | ±10% | 有 |
| PropertyAsset | ±5% | 有(租金) |

### 3. UI组件 (component)

Material Design 3 风格的自定义组件。

```
MDialog
    ├── showMessageDialog() # 消息对话框
    ├── showOptionDialog()  # 选项对话框
    └── showInputDialog()   # 输入对话框

MDbutton
    └── 自适应内容大小的按钮
```

### 4. 颜色系统 (ColorSystem)

```
MaterialPalette
    ├── MOSS   # 苔藓绿主题
    └── PURPLE # 紫色主题
```

## 数据流

```
用户操作 → DashboardPanel → EventProcessor → GameEvent
                ↓                               ↓
           MapDraw (动画)              EventContext
                                            ↓
                                    Player / AssetManager
                                            ↓
                                    PropertyPanel (表格更新)
```

## 扩展指南

### 添加新事件

1. 创建事件类实现 `GameEvent` 接口
2. 在 `DefaultEventConfig` 中注册

```java
public class MyEvent implements GameEvent {
    @Override
    public TitlesTypes getType() { return TitlesTypes.OPPORTUNITY; }
    
    @Override
    public void execute(EventContext ctx) {
        ctx.showMessage("自定义事件！", "提示", MessageType.INFO);
        ctx.addCash(100);
    }
}
```

### 添加新资产类型

1. 在 `AssetType` 枚举中添加新类型
2. 创建资产类实现 `Asset` 接口

### 自定义颜色主题

在 `MaterialPalette` 中添加新的静态主题实例。

## 构建与运行

```bash
# 编译
./gradlew build

# 运行测试
./gradlew test

# 运行应用
./gradlew run
```

## 依赖

| 依赖 | 版本 | 用途 |
|-----|------|------|
| FlatLaf | 3.5.4 | Material UI |
| JUnit Jupiter | 5.10.0 | 单元测试 |
| game2048-api | 1.0.1 | 2048小游戏 |

---

*更新日期: 2025-12-18*
