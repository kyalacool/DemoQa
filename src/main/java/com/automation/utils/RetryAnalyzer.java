package com.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryAnalyzer implements IRetryAnalyzer {
    private ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        int currentRetryCount = retryCount.get();
        if (currentRetryCount < MAX_RETRY_COUNT) {
            currentRetryCount++;
            retryCount.set(currentRetryCount);
            log.warn("RetryAnalyzer runs for: {}. Attempt(s): {}", result.getName(), currentRetryCount);
            return true;
        }
        retryCount.remove();
        return false;
    }
}