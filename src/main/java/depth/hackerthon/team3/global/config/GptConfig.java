package depth.hackerthon.team3.global.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GptConfig {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String MODEL = "gpt-4o";
    public static final Integer MAX_TOKEN = 4096;
    public static final Boolean STREAM = false;
    // public static final String ROLE = "user";
    public static final Double TEMPERATURE = 0.6;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";

    //completions : 질답
    public static final String URL = "https://api.openai.com/v1/chat/completions";

    public static final String CONTENT =
            """
            
            """;


}
