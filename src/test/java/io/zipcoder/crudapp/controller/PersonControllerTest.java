package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import io.zipcoder.crudapp.repository.PersonRepository;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testFind() throws Exception {
        Long givenId = 1L;
        BDDMockito
                .given(personRepository.findById(givenId))
                .willReturn(Optional.of(new Person("Xiong", "Yuan")));

        String expected = "{\"id\":null, \"firstName\":\"Xiong\",\"lastName\":\"Yuan\"}";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/people/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }
}
