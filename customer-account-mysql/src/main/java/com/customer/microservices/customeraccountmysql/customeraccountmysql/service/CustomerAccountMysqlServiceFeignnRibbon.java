/*
 * package
 * com.customer.microservices.customeraccountmysql.customeraccountmysql.service;
 * 
 * import org.springframework.cloud.netflix.ribbon.RibbonClient; import
 * org.springframework.cloud.openfeign.FeignClient; import
 * org.springframework.web.bind.annotation.GetMapping;
 * 
 * import
 * com.customer.microservices.customeraccountmysql.customeraccountmysql.bean.
 * CustomerAccount;
 * 
 * // To define only fiegn we will use the follwoing protocal
 * // @FeignClient(name="customer-service-mysql", url="localhost:8080/api") //
 * 
 * // Follwoing one we user for Feign and Ribbon
 * 
 * @FeignClient(name = "customer-service-mysql")
 * 
 * @RibbonClient(name = "customer-service-mysql") public interface
 * CustomerAccountMysqlServiceFeignnRibbon {
 * 
 * @GetMapping("/api/customer") public CustomerAccount[] customerDetails(); }
 */