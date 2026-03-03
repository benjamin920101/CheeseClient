/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import net.dv8tion.jda.api.AccountType;

public class AccountTypeException
extends RuntimeException {
    private final AccountType requiredType;

    public AccountTypeException(AccountType requiredType) {
        this(requiredType, "The current AccountType is not valid for the attempted action. Required AccountType: " + (Object)((Object)requiredType));
    }

    public AccountTypeException(AccountType requiredType, String message) {
        super(message);
        this.requiredType = requiredType;
    }

    public AccountType getRequiredType() {
        return this.requiredType;
    }

    public static void check(AccountType actualType, AccountType requiredType) {
        if (actualType != requiredType) {
            throw new AccountTypeException(requiredType);
        }
    }
}

