package com.example.PFEproject.rest;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.dto.LoginDTO;
import com.example.PFEproject.dto.TokenDTO;
import com.example.PFEproject.service.RoleService;
import com.example.PFEproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestPropertySource("classpath:config/ressource.properties")
class RoleRestTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    TokenDTO admin = new TokenDTO();
    LoginDTO loginDTO = new LoginDTO();
    Long id ;
    Role role = new Role();
    @Autowired
    private AuthREST authREST;
    @BeforeEach
    void setUp() throws Exception {
         id = 2L;
         role = new Role(id,"ROLE_ADMIN");
    }
    @Test
    void ShouldSaveRole() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles/saveRole").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(role.getName())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(role.getId().intValue())));
    }

    @Test
    void ShouldReturnRoleByName() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles/FindRoleByName/"+role.getName()));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(role.getName())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(role.getId().intValue())));
    }
}