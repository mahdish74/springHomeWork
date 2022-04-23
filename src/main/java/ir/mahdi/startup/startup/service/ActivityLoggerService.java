package ir.mahdi.startup.startup.service;

import ir.mahdi.startup.startup.dto.in.ActivityLogReq;
import ir.mahdi.startup.startup.mapper.ActivityLogMapper;
import ir.mahdi.startup.startup.model.entity.ActivityLog;
import ir.mahdi.startup.startup.repository.ActivityLogRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityLoggerService {
    private final ActivityLogRepo activityLogRepo;

    public ActivityLoggerService(ActivityLogRepo activityLoggerRepo) {
        this.activityLogRepo = activityLoggerRepo;
    }

    @Transactional
    public void createActivityLogger(ActivityLogReq activityLogReq){
        ActivityLog activityLog = ActivityLogMapper.mapActivityLogReqToActivityLog(activityLogReq);
        activityLogRepo.save(activityLog);
    }
}
