package mk.ukim.finki.petstore.service;

import mk.ukim.finki.petstore.model.HistoryLog;

public interface HistoryLogService {
    HistoryLog saveHistoryLog(int successfullyBought, int failedBought);
}
