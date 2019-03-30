package com.employee.service.integration.tests;

import com.employee.service.EmployeeServiceApp;
import com.employee.service.domain.Department;
import com.employee.service.domain.UserDetails;
import com.employee.service.repository.DepartmentRepository;
import com.employee.service.repository.UserDetailsRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.service.EventMessageService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@EnableJpaRepositories("com.employee.service.repository")
@EntityScan("com.employee.service.*")
@ComponentScan("com.employee.service.*")
@SpringBootTest(classes = {EmployeeServiceApp.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:config/application.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeApplicationIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @LocalManagementPort
    int randomManagementPort;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    EventMessageService eventMessageService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static String token;
    private static String AUTH_HEADER_NAME = "Authorization";

    private static Logger logger = LoggerFactory.getLogger(EmployeeApplicationIntegrationTest.class);

    @Before
    public void setup() {
    }

    @Test
    public void test_stage1_signup_userInputs() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/sign-up").content(getUserDetailsAsString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("user created"));
    }

    @Test
    public void test_stage2_login_userInputs() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/login").content(getUserDetailsAsString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        token = result.getResponse().getHeaders(AUTH_HEADER_NAME).get(0);
        logger.info(token);
        Assert.assertTrue(!StringUtils.isBlank(token));
    }


    @Test
    public void test_stage3_department_create() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/api/departments").content(getDepartmentAsString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, token);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("consulting"));
    }

    @Test
    public void test_stage4_employee_create() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/api/employees").content(getEmployeeRequestAsString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, token);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("200"));
    }

    @Test
    public void test_stage5_employee_update() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.put("/api/employees").content(getEmployeeRequestAsStringforUpdate()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, token);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("200"));
    }

    @Test
    public void test_stage6_employee_get() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/api/employees/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, token);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("test@email.com"));
    }

    @Test
    public void test_stage7_employee_get() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.delete("/api/employees/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(AUTH_HEADER_NAME, token);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String resultValue = result.getResponse().getContentAsString();
        logger.info(resultValue);
        Assert.assertTrue(resultValue.contains("test@email.com"));
    }


    private String getDepartmentAsString() throws Exception {
        Department department = new Department();
        department.setName("consulting");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(department);
    }

    private String getUserDetailsAsString() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName("admin");
        userDetails.setPassword("admin");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userDetails);
    }

    private String getEmployeeRequestAsString() throws Exception {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test@email.com");
        employeeRequest.setDepartmentName("consulting");
        employeeRequest.setBirthDay("06.07.1988");
        employeeRequest.setFullName("integration test");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(employeeRequest);
    }

    private String getEmployeeRequestAsStringforUpdate() throws Exception {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test@email.com");
        employeeRequest.setDepartmentName("Marketing");
        employeeRequest.setBirthDay("06.07.1988");
        employeeRequest.setFullName("integration test");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(employeeRequest);
    }
}
