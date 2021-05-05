package lt.psk.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.*;
import java.text.SimpleDateFormat;

@LoggingInterceptor
@Interceptor
public class MethodLogger {
    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        String fileName = "C:\\Users\\Saulius\\IdeaProjects\\PSK_Lab\\src\\main\\java\\lt\\psk\\interceptors\\MethodLogs.txt";
        File loggingFile = new File(fileName);

        if (!loggingFile.exists()) {
            try {
                loggingFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String className = ctx.getTarget().getClass().getSuperclass().getName();
        String methodName = ctx.getMethod().getName();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        try (FileWriter writer = new FileWriter(loggingFile, true)) {
            writer.write("Class name: " + className + "\n");
            writer.write("Method name: " + methodName + "\n");
            writer.write("Method was called at: " + timeStamp + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object obj = ctx.proceed();

        return obj;
    }
}
