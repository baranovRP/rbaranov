package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.model.Post;
import ru.job4j.repository.UserRepository;
import ru.job4j.service.DispatchFilterService;
import ru.job4j.service.MetadataService;
import ru.job4j.service.PictureService;
import ru.job4j.service.PostService;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ListPostsController.class)
public class ListPostsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DispatchFilterService dispatchFilterService;
    @MockBean
    private MetadataService metadataService;
    @MockBean
    private PictureService pictureService;
    @MockBean
    private PostService service;
    @MockBean
    private RegisterController controller;
    @MockBean
    private UserRepository repo;

    @Test
    @WithMockUser()
    public void getPostsCollection() throws Exception {
        BDDMockito.given(service.findAll())
            .willReturn(Arrays.asList(new Post().setContent("content")));
        mvc.perform(MockMvcRequestBuilders.get("/list")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", is("content")));
    }
}