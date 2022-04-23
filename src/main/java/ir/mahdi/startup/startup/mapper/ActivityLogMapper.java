package ir.mahdi.startup.startup.mapper;

import ir.mahdi.startup.startup.dto.in.ActivityLogReq;
import ir.mahdi.startup.startup.model.entity.ActivityLog;

public class ActivityLogMapper {
    public static ActivityLog mapActivityLogReqToActivityLog(ActivityLogReq activityLogReq){
        return new ActivityLog()
                .setPersonalCode(activityLogReq.getPersonalCode())
                .setCardPAN(activityLogReq.getCardPAN())
                .setName(activityLogReq.getName())
                .setApplicationType(activityLogReq.getApplicationType());
    }
}
