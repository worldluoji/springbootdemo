package com.transaction.declarativetransaction;

public interface FooService {
    void insertRecored();
    void insertThenRollback() throws RollBackException;
    void invokeInsertThenRollback() throws RollBackException;
}
