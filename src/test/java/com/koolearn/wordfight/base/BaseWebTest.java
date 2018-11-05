package com.koolearn.wordfight.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Time: 10:26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Data
public class BaseWebTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    TestRestTemplate restTemplate;

    /*@Test
    public void mockDemo() throws Exception {
        String content = this.mvc.perform(get("/bbb")
                .accept(MediaType.TEXT_HTML))
                *//*.andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(754))
                .andExpect(jsonPath("$.rows", Matchers.hasSize(5)))
                .andExpect(jsonPath("$.rows[0].desc",Matchers.notNullValue()))*//*
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }*/
}