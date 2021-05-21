package com.alethio.service.order.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    // 성공, 재고부족(재고보다 더많이 주문), 재고없음(재고가 0)
    @Test
    public void 주문테스트_성공() throws Exception{
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
        String result = "success";
        mvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(clothTestData))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));


    }


}