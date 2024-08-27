package com.saran.Bookstoreapi.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(true) //Allows you to specify the content type using a query parameter like ?mediaType=xml or ?mediaType=json
                .parameterName("mediaType")
                .ignoreAcceptHeader(false) //This tells Spring to consider the Accept header sent by the client.
                .defaultContentType(MediaType.APPLICATION_JSON) //Sets the default content type to JSON if no specific media type is requested.
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
