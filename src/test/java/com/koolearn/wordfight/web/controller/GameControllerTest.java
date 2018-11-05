package com.koolearn.wordfight.web.controller;

import com.koolearn.wordfight.base.BaseWebTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 14:13
 */
@Slf4j
public class GameControllerTest extends BaseWebTest {

    String uid;

    @Before
    public void setup() {
        uid = "8060596d49a1459c80b70a48521488be";
    }

    @Test
    @Transactional
    public void getRandomWordPkData() throws Exception{
        String content = getMvc().perform(get("/v1/game/randomWordPkData")
                .param("uid", uid)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.randomWordStore", Matchers.hasSize(10)))
                .andExpect(jsonPath("$.data.robotAnswer", Matchers.hasSize(10)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

    @Test
    @Transactional
    public void savePkRecord() throws Exception{
        String content = getMvc().perform(post("/v1/game/savePKRecord")
                .param("uid", uid)
                .param("pkType", "1")
                .param("isWin", "1")
                .param("score", "65")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

    @Test
    @Transactional
    public void getPowerMap() throws Exception{
        String content = getMvc().perform(get("/v1/game/powerMap")
                .param("uid", uid)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

    @Test
    @Transactional
    public void getWorldRank() throws Exception{
        String content = getMvc().perform(get("/v1/game/worldrank")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

    @Test
    @Transactional
    public void testAddProp() throws Exception{
        String content = getMvc().perform(put("/v1/game/addProp")
                .param("uid", uid)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

    @Test
    @Transactional
    public void testUseProp() throws Exception{
        String content = getMvc().perform(put("/v1/game/useProp")
                .param("uid", uid)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("content:{}",content);
    }

}