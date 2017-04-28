package sample.spring.framework.pointcuts;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by associate on 9/2/16.
 */

@Aspect
public class MessageAspect
{
    private Logger log = Logger.getLogger(MessageAspect.class);

    @Pointcut("execution(* sample.spring.framework.standalone.business.apps.HelloWorld..*(..))")
    public void test()
    {

    }
/*
    @Before("test()")
    public void printPreLinerText() {
        System.out.println("This is a Pre-Liner text from a pointcut!");
    }

    @After("test()")
    public void printPostLinerText() {
        System.out.println("This is a Post-Liner text from a pointcut!");
    }*/

    @Around("execution(* sample.spring.framework..*(..))")
    public Object monitoringMethodExecutions(
                    ProceedingJoinPoint jp)
    {

        String className = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object retVal=null;

        try
        {
            log.info("Entering " + className + "." + methodName);

            long startTime = System.currentTimeMillis();

            retVal  = jp.proceed();

            long endTime = System.currentTimeMillis();

            log.info("Time to intercept was: " + (endTime - startTime));
            log.info("Exiting " + className + "." + methodName);
        }
        catch (Throwable t)
        {
            log.error("Excepiton thrown while intercepting..."+t);
        }

        return retVal;

    }
}
