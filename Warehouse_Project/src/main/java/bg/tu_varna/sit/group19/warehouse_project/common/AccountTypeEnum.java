package bg.tu_varna.sit.group19.warehouse_project.common;



public class AccountTypeEnum {

    public enum AccountType{
        Admin,
        Owner,
        Agent
    }


    private AccountType accountType;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
