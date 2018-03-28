package com.ykyy.server.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest
{
    private MockMvc mvc;

    @Before
    public void setMvc()
    {
        mvc= MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void runtest() throws Exception
    {
        RequestBuilder requestBuilder = null;

        requestBuilder = get("/user/getall");
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(Matchers.equalTo("success")));
    }
}
