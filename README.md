# otter-spring-boot-starter  

Otter Spring Book Edition  

[中文](./README_zh_CN.md)  

Support Function：  
- [x] Ensure Call Consistency  

# Ensure Call Consistency：  

  Support SpringBoot,SpringCloud(feign),dubbo-spring-boot-starter Frame   
  Before Using, Make Sure The Call Link Is Idempotent.   
  Simple explanation: service a calls service B, service B calls Service C,   
  in the link, B and C execute successfully     
  A Execute Exception, Then The Data Is Inconsistent   
  Otter Will Save The Error Information In MySQL And Automatically Try To Call The Link Again,  
  Service A Calls Service B, And Service B Calls Service C  
  If The Link Call Is Successful, The Data Is Consistent  
  
## Quick Start  

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>otter-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>1.0.0-RELEASE</version>
        </dependency>
```  

 ### Configuration File  
 
 ```properties
 ## application.properties
spring.datasource.url= #MySQL DataBase Url
spring.datasource.username= #MySQL DataBase username
spring.datasource.password= #MySQL DataBase password
spring.jpa.hibernate.ddl-auto= #If Not Initialized otter_entity DataBase,Please Set Up :update
 ```  

# Start Using  

```java
@RestController
@Otter
public class Demo {
	
    @Resource
	public FeignService feignService;
    /**
    * Use zookeeper As A Registry,As A Demonstration
    */
	@Reference
	private DubboService dubboService;

	@Repair
	@GetMapping("/feign")
	public String feign() {
        feignService.update();
        //Local SQL Execution
		return "feign";
	}

    @Repair
	@GetMapping("/dubbo")
	public String feign() {
        dubboService.update();
        //Local SQL Execution
		return "dubbo";
	}
}
```