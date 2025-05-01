package mk.ukim.finki.petstore.service.impl;

import mk.ukim.finki.petstore.model.HistoryLog;
import mk.ukim.finki.petstore.repository.jpa.HistoryLogRepository;
import mk.ukim.finki.petstore.service.HistoryLogService;
import org.springframework.stereotype.Service;

@Service
public class HistoryLogServiceImpl implements HistoryLogService {
    private final HistoryLogRepository historyLogRepository;

    public HistoryLogServiceImpl(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    @Override
    public HistoryLog saveHistoryLog(int successfullyBought, int failedBought) {
        HistoryLog historyLog = new HistoryLog(successfullyBought, failedBought);
        historyLogRepository.save(historyLog);
        return historyLog;
    }
}
