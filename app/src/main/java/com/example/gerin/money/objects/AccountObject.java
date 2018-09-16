package com.example.gerin.money.objects;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;

public class AccountObject {
    public ObservableField<String> currency = new ObservableField<>();
    public ObservableDouble balance = new ObservableDouble();
    public ObservableDouble budgetPercent = new ObservableDouble();
}
