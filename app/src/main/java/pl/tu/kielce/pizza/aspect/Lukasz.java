package pl.tu.kielce.pizza.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.thymeleaf.expression.Strings;

@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "logging", name = "performanceLogging", havingValue = "true")
public class Lukasz
{
    
    @Autowired
    public Lukasz(Environment environment) {
        //TODO sprawdź!!, a potem włącz conditional!
        System.out.println(environment.getProperty("logging.performanceLogging"));
    }
    
    @Pointcut("@annotation(pl.tu.kielce.pizza.aspect.TrackTime)")
    public void trackTime()
    {
    }


    
    @Around("trackTime()")
    public Object logMethodExecution(ProceedingJoinPoint pjp) throws Throwable
    {
        return logAndReturn(pjp, log);
    }
    
    Object logAndReturn(final ProceedingJoinPoint joinPoint, final Logger logger)
    throws Throwable
    {
        System.out.println("HODOR");
        
        StringBuilder arguments = prepareArgumentsLog(joinPoint);
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Object returnedValue = null;
        Object returnedValueToLog = null;
        
        try
        {
            returnedValue = joinPoint.proceed();
            if(isReturnedValueLogExcluded(joinPoint))
            {
                returnedValueToLog = "<<RETURNED VALUE EXCLUDED>>";
            }
            else
            {
                returnedValueToLog = returnedValue;
            }
        }
        catch(Exception e)
        {
            stopWatch.stop();
            StringBuilder logMessage = prepareMethodLog(joinPoint, stopWatch,
                                                        returnedValueToLog, e
                                                       );
            logMethodDetailedInfo(logger, arguments, logMessage);
            throw e;
        }
        
        stopWatch.stop();
        
        StringBuilder method = prepareMethodLog(joinPoint, stopWatch,
                                                returnedValueToLog, null
                                               );
        
        logMethodDetailedInfo(logger, arguments, method);
        
        return returnedValue;
    }
    
    private void logMethodDetailedInfo(final Logger logger, StringBuilder stringArgs,
        StringBuilder logMessage)
    {
        logger.info(logMessage.toString());
        logger.info("\nArguments:");
        logger.info(stringArgs.toString());
        
        logger.info("************************************************************************************************************");
        logger.info("\n");
    }
    
    private StringBuilder prepareArgumentsLog(final ProceedingJoinPoint joinPoint)
    {
        StringBuilder stringArgs = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for(Object arg : args)
        {
            stringArgs.append("\n: ");
            if(arg != null)
            {
                stringArgs.append(arg.getClass()).append(" - ")
                          .append(arg.toString());
            }
            else
            {
                stringArgs.append("null");
            }
        }
        return stringArgs;
    }
    
    private StringBuilder prepareMethodLog(final ProceedingJoinPoint joinPoint,
        StopWatch stopWatch, Object returnedValue, Exception e)
    {
        StringBuilder logMessage = new StringBuilder("\n");
        logMessage.append("method: ")
                  .append(joinPoint.getTarget().getClass().getName()).append(".")
                  .append(joinPoint.getSignature().getName());
        if(e == null)
        {
            logMessage
                .append(" || returned value: "
                        + (returnedValue != null ? returnedValue.toString()
                                                 : "void") + " || ")
                .append("execution time: ")
                .append(stopWatch.getTotalTimeMillis()).append(" ms");
        }
        else
        {
            logMessage
                .append(" || exception was thrown: " + e.getClass() + " , Message: " + e.getMessage())
                .append("execution time: ")
                .append(stopWatch.getTotalTimeMillis()).append(" ms");
        }
        return logMessage;
    }
    
    protected boolean isReturnedValueLogExcluded(final ProceedingJoinPoint joinPoint)
    {
        return false;
    }
}
