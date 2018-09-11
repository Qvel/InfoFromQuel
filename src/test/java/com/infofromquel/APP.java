package com.infofromquel;


import com.infofromquel.config.*;
import com.infofromquel.controller.TopicController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDispatcherServlet.class,SpringSecurityConfig.class,PropertiesConfig.class, SpringConfig.class,WebConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class APP {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testController() throws Exception{
        mockMvc.perform(
                        get("/")

                        )
                        .andDo(print())
                        .andExpect(status().isOk())

        ;
       /* mockMvc.perform(
                get("/getAllTopics").contentType(MediaType.APPLICATION_JSON)
        ).andExpect((status().isUnauthorized()));*/
    }

    @Test
    @Ignore
    public void testControllerWithData() throws Exception{
        mockMvc.perform(
                get("/getUsers")
                .headers(HttpHeaders.EMPTY)
        ).andDo(print()).andExpect(status().isOk());
        mockMvc.perform(
                get("/getAllTopics")
        ).andDo(print()).andExpect(status().isOk());
    }
}
