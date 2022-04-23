package ir.mahdi.startup.startup.infrastructure.aspect;

import ir.mahdi.startup.startup.dto.in.ActivityLogReq;
import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.service.ActivityLoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActivityLogger {
    @Value("${configuration.type}")
    private String applicationType;

    private final ActivityLoggerService activityLoggerService;

    public ActivityLogger(ActivityLoggerService activityLoggerService) {
        this.activityLoggerService = activityLoggerService;
    }


    @Around("@annotation(ir.mahdi.startup.startup.infrastructure.annotation.LogActivity)")
    public Object addControllerActivityLog(ProceedingJoinPoint joinPoint) throws Throwable {
        PrintRequestReq printRequestReq = (PrintRequestReq) joinPoint.getArgs()[1];
        String name = joinPoint.getSignature().getName();
        ActivityLogReq activityLogReq = new ActivityLogReq()
                .setCardPAN(printRequestReq.getCardPAN())
                .setPersonalCode(printRequestReq.getPersonalCode())
                .setName(name)
                .setApplicationType(applicationType);

        activityLoggerService.createActivityLogger(activityLogReq);

        return joinPoint.proceed();
    }


}
