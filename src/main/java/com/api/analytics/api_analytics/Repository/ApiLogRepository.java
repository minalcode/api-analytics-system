package com.api.analytics.api_analytics.Repository;


import com.api.analytics.api_analytics.Model.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
    long countByStatusCode(int statusCode);
}
