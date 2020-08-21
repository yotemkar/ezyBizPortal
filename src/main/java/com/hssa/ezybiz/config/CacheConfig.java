package com.hssa.ezybiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
@Profile("hazelcast-cache")
public class CacheConfig {
	
	 @Bean
	    public Config hazelcastConfig(){
		 	/*Config config = new Config();
	        config.setInstanceName("hazelcast-cache");

	        MapConfig banDetails = new MapConfig();
	        banDetails.setTimeToLiveSeconds(20);
	        banDetails.setEvictionPolicy(EvictionPolicy.LFU);
	        config.getMapConfigs().put("banDetails",banDetails);
	        
	        return config;*/
		 return new Config().setInstanceName("hazelcast-cache")
	                .addMapConfig(new MapConfig().setName("banDetails")
	                .setMaxSizeConfig(new MaxSizeConfig(300,MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
	                .setEvictionPolicy(EvictionPolicy.LRU)
	                .setTimeToLiveSeconds(2000));
	    }

}
