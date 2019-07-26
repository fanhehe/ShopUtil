package com.fanhehe.util.constant;

import com.fanhehe.util.type.Type;

public enum AccountType {
    NONE(0),
    EMAIL(1),
    PHONE(2),
    UID(3),
    WECHAT(4),
    SINA(5),
    GITHUB(6),
    FACEBOOK(7);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * 根据ID，获取类型
     * @param value 类型ID
     * @return 类型
     */
    public static AccountType getAccountTypeByValue(int value) {
        for (AccountType item: AccountType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }

    /**
     * 根据目标target，获取参数类型
     * @param target 参数
     * @return 账号类型
     */
    public static AccountType getAccontTypeByTarget(String target) {
        if (Type.isUid(target)) {
            return AccountType.UID;
        }

        if (Type.isEmail(target)) {
            return AccountType.EMAIL;
        }

        if (Type.isPhone(target)) {
            return AccountType.PHONE;
        }

        return AccountType.NONE;
    }
}
