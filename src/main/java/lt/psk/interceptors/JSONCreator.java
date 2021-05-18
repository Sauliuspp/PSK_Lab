package lt.psk.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@JSONInterceptor
@Interceptor
public class JSONCreator {
    @AroundInvoke
    public Object createJSON(InvocationContext ctx) throws Exception {

        System.out.println("JSONCreator called.");

        Object obj = ctx.proceed();

        String fileName = "C:\\Users\\Saulius\\IdeaProjects\\PSK_Lab\\src\\main\\java\\lt\\psk\\interceptors\\JSON_file.json";
        File jsonFile = new File(fileName);

        try {
            Object contextObject = ctx.getParameters();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(contextObject);

            if (!jsonFile.exists()) {
                try {
                    jsonFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
