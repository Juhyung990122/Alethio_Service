package com.alethio.service.order.controller;


import com.alethio.service.product.domain.Food;
import com.alethio.service.product.domain.ItemType;
import com.alethio.service.product.repository.ItemRepository;
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
    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                })).build();
    }

    // 성공
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

    @Test
    public void 주문테스트_입고요청() throws Exception {
        Food testProduct = new Food(Long.valueOf("3"),"테스트떡볶이",10, ItemType.food);
        itemRepository.save(testProduct);
        String clothTestData = "{\n" +
                "    \"contactInfo\": {\n" +
                "        \"contactEmail\": \"test@test.com\",\n" +
                "        \"contactName\": \"   구매자   \",\n" +
                "        \"mobile\": \"01099999999    \"\n" +
                "    },\n" +
                "    \"items\": {\n" +
                "        \"itemType\": \"food\",\n" +
                "        \"id\": 3\n" +
                "    }\n" +
                "}";
        String result = "{\"contactEmail\":\"test@test.com\",\"contactName\":\"구매자\",\"mobile\":\"01099999999\",\"itemType\":\"food\",\"itemId\":3}";
        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clothTestData))
                .andExpect(status().isOk())
                .andExpect(content().string(result))
                .andDo(print());
        itemRepository.delete(testProduct);

    }

    @Test(expected= NoSuchElementException.class)
    public void 주문테스트_재고없음() throws Throwable {
        Food testProduct = new Food(Long.valueOf("3"),"테스트떡볶이",0,ItemType.food);
        itemRepository.save(testProduct);
        String NoStockTestData = "{\n" +
                "    \"contactInfo\": {\n" +
                "        \"contactEmail\": \"test@test.com\",\n" +
                "        \"contactName\": \"   구매자   \",\n" +
                "        \"mobile\": \"01099999999    \"\n" +
                "    },\n" +
                "    \"items\": {\n" +
                "        \"itemType\": \"food\",\n" +
                "        \"id\": 3\n" +
                "    }\n" +
                "}";
        try {
        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(NoStockTestData))
                .andExpect(status().isInternalServerError())
                .andDo(print());
        } catch (NestedServletException e) {
            System.out.println(e.getCause());
            throw e.getCause();
        } finally {
            itemRepository.delete(testProduct);
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void 주문테스트_미등록상품() throws Throwable{
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