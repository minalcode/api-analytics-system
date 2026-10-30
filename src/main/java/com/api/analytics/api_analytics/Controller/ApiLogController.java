
package com.api.analytics.api_analytics.Controller;

import com.api.analytics.api_analytics.Model.ApiLog;
import com.api.analytics.api_analytics.Service.ApiLogService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiLogController {

    private final ApiLogService apiLogService;

    public ApiLogController(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @GetMapping("/demo")
    public Map<String, String> demo() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is working successfully");
        response.put("project", "API Usage Analytics and Monitoring System");
        return response;
    }

    @GetMapping("/logs")
    public List<ApiLog> getAllLogs() {
        return apiLogService.getAllLogs();
    }

    @GetMapping("/analytics/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        summary.put("totalRequests",
                apiLogService.getTotalRequests());

        summary.put("successfulRequests",
                apiLogService.getSuccessfulRequests());

        summary.put("failedRequests",
                apiLogService.getFailedRequests());

        return summary;
    }
}
