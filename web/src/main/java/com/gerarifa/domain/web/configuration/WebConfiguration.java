package com.gerarifa.domain.web.configuration;

import com.gerarifa.domain.adapter.SorteioServiceImpl;
import com.gerarifa.domain.ports.repository.SorteioRepository;
import com.gerarifa.domain.ports.services.SorteioService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@PropertySource(value = {"classpath:/sorteio.properties"}, ignoreResourceNotFound = true)
@ComponentScan(basePackages = { "com.gerarifa" })
public class WebConfiguration {

    @Bean
    SorteioService sorteioService(SorteioRepository sorteioRepository){
        return new SorteioServiceImpl(sorteioRepository, null, null);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:sorteio");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
