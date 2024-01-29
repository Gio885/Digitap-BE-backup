package com.exolab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
/*
    la classe abstractMongo... fornita da SpringData mongoDb viene utilizzata per la configurazione del clientMongoDB
    nel contesto di un applicazioneSpring
 */
    @Override
    protected String getDatabaseName() {
        return "digitap";
    }
}
