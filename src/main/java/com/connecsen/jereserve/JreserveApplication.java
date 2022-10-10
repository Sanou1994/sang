package com.connecsen.jereserve;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.connecsen.jereserve.jobs.JobSmsAllUsers;
import com.connecsen.jereserve.service.IAccountService;
import com.connecsen.jereserve.service.IDemandeService;

@Configuration
@EnableScheduling
@SpringBootApplication
public class JreserveApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;
	 @Autowired
     private ThreadPoolTaskScheduler taskScheduler;
	    @Autowired
	     IAccountService iAccountService;
	    @Autowired
		IDemandeService iDemandService;
	     public static void main(String[] args) {
		SpringApplication.run(JreserveApplication.class, args);
	

	}
	
    @Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
    

	  @PostConstruct
	   public void scheduleRunnableWithCronTrigger() {
	   taskScheduler.scheduleWithFixedDelay(new JobSmsAllUsers(this.iDemandService),5000); }
	   
	 
   
    @Bean
	 public RestTemplate getRestTemplate() {
		 return new RestTemplate();
	 }
	 
	@Override  
	public void run(String... args) throws Exception {
	this.iAccountService.initApp();
	}
   
}
