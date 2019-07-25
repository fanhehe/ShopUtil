package com.fanhehe.util.constant;

public enum AccountType {
    EMAIL(1),
    PHONE(2),
    WECHAT(3),
    SINA(4),
    GITHUB(5),
    FACEBOOK(6);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccountType getAccountTypeByValue(int value) {
        for (AccountType item: AccountType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }
}
