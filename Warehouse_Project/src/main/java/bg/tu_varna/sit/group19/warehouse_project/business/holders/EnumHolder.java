package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;

public class EnumHolder {
    private Enums.AccountType accountType;
    private Enums.OpenMode openMode;

    private final static EnumHolder INSTANCE = new EnumHolder();

    private EnumHolder() {}

    public static EnumHolder getInstance() {
        return INSTANCE;
    }

    public void setAccountType(Enums.AccountType accountType) {
        this.accountType = accountType;
    }

    public Enums.AccountType getAccountType() {
        return this.accountType;
    }

    public Enums.OpenMode getOpenMode() {
        return openMode;
    }

    public void setOpenMode(Enums.OpenMode openMode) {
        this.openMode = openMode;
    }
}
