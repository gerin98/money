package com.example.gerin.money.apis;

import com.example.gerin.money.objects.AccountObject;
import com.example.gerin.money.objects.CustomerObject;

public interface DataHandler {
    void getCustomerAccounts(String id, CustomerObject customerObject);
    void getAccountData(String id, AccountObject accountObject);
}
