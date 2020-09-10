package cn.com.jiahang.core.manager.config;

import cn.com.jiahang.core.manager.message.handle.impl.EmailSendMsgHandle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置静态参数初始化
 */
@Configuration
public class StaticConfig {

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Bean
    public void initStatic() {
        EmailSendMsgHandle.setEmailFrom(emailFrom);
    }
}
