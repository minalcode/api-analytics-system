
package com.api.analytics.api_analytics.Service;

import com.api.analytics.api_analytics.Model.ApiLog;
import com.api.analytics.api_analytics.Repository.ApiLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogService {

    private final ApiLogRepository apiLogRepository;

    public ApiLogService(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }

    public ApiLog saveLog(String endpoint, String httpMethod,
                          int statusCode, long responseTimeMs) {
        ApiLog log = new ApiLog(
                endpoint, httpMethod, statusCode, responseTimeMs
        );

        return apiLogRepository.save(log);
    }

    public List<ApiLog> getAllLogs() {
        return apiLogRepository.findAll();
    }

    public long getTotalRequests() {
        return apiLogRepository.count();
    }

    public long getSuccessfulRequests() {
        return apiLogRepository.countByStatusCode(200);
    }

    public long getFailedRequests() {
        return apiLogRepository.countByStatusCode(500);
    }
}
