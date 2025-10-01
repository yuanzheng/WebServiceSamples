package com.ytechtrade.inventorymanagementsystem.services;

import com.ytechtrade.inventorymanagementsystem.enums.TransactionStatus;
import com.ytechtrade.inventorymanagementsystem.models.dtos.Response;
import com.ytechtrade.inventorymanagementsystem.models.dtos.TransactionRequest;

public interface TransactionService {
    Response purchase(TransactionRequest transactionRequest);

    Response sell(TransactionRequest transactionRequest);

    Response returnToSupplier(TransactionRequest transactionRequest);

    Response getAllTransactions(int page, int size, String filter);

    Response getAllTransactionById(Long id);

    Response getAllTransactionByMonthAndYear(int month, int year);

    Response updateTransactionStatus(Long transactionId, TransactionStatus status);
}
