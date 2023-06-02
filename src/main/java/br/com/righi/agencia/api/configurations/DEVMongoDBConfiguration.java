package br.com.righi.agencia.api.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@Profile("dev")
public class DEVMongoDBConfiguration {
	
	@Value("${MONGODB_URI}")
	private String mongoDbConnectionUri;

	@Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoDbConnectionUri));
    }
	
	
	
}
