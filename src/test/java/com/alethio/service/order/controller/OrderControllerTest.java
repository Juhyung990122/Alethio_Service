package com.alethio.service.order.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;
import java.util.NoSuchElementException;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                })).build();
    }

    // 성공, 재고부족(재고보다 더많이 주문), 재고없음(재고가 0)
    @Test
    public void 주문테스트_성공() throws Exception {
        String clothTestData = "{\n" +
                "    \"contactInfo\": {\n" +
                "        \"contactEmail\": \"test@test.com\",\n" +
                "        \"contactName\": \"   구매자   \",\n" +
                "        \"mobile\": \"01099999999    \"\n" +
                "    },\n" +
                "    \"items\": {\n" +
                "        \"itemType\": \"food\",\n" +
                "        \"id\": 1\n" +
                "    }\n" +
                "}";
        String result = "{\"contactEmail\":\"test@test.com\",\"contactName\":\"구매자\",\"mobile\":\"01099999999\",\"itemType\":\"food\",\"itemId\":1}";
        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clothTestData))
                .andExpect(status().isOk())
                .andExpect(content().string(result))
                .andDo(print());
    }

    @Test(expected= NoSuchElementException.class)
    public void 주문테스트_재고없음() throws Throwable {
        String NoStockTestData = "{\n" +
                "    \"contactInfo\": {\n" +
                "        \"contactEmail\": \"test@test.com\",\n" +
                "        \"contactName\": \"   구매자   \",\n" +
                "        \"mobile\": \"01099999999    \"\n" +
                "    },\n" +
                "    \"items\": {\n" +
                "        \"itemType\": \"food\",\n" +
                "        \"id\": 2\n" +
                "    }\n" +
                "}";
        try {
        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(NoStockTestData))
                .andExpect(status().isInternalServerError())
                .andDo(print());}
        catch (NestedServletException e) {
            System.out.println(e.getCause());
            throw e.getCause();
        }
    }

}