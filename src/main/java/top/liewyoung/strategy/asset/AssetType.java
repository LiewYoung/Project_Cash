package top.liewyoung.strategy.asset;

/**
 * 资产类型枚举
 * 
 * @author LiewYoung
 * @since 2025/12/18
 */
public enum AssetType {
    STOCK("股票", "[股]"),
    BUSINESS("企业", "[企]"),
    PROPERTY("房产", "[房]");

    private final String displayName;
    private final String icon;

    AssetType(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }
}
