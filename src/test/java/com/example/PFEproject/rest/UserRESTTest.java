package com.example.PFEproject.rest;
import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.dto.TokenDTO;
import com.example.PFEproject.service.RoleService;
import com.example.PFEproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Collection;
import java.util.List;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestPropertySource("classpath:config/ressource.properties")
class UserRESTTest {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthREST authREST;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RoleService roleService;
    Role roleAdmin;
    Role rolePilote;
    Role roleResponsable;
    UserRole userRole;
    Collection<Role> rolesAdmin = new ArrayList<>();
    Collection<Role> rolesPilote = new ArrayList<>();
    Collection<Role> rolesResponsable = new ArrayList<>();
    User user = new User();
    User user1 = new User();
    User user2 = new User();
    TokenDTO admin = new TokenDTO();
    Long id;
    List<User> userList  = new ArrayList<>();
    String username;
    @BeforeEach
    void setUp() throws Exception {
        userRole = new UserRole();
         roleAdmin = roleService.findByName("ROLE_ADMIN");
         rolePilote = roleService.findByName("ROLE_PILOTE");
         roleResponsable = roleService.findByName("ROLE_RESPONSABLE");
        rolesAdmin.add(roleAdmin);
        rolesPilote.add(rolePilote);
        rolesResponsable.add(roleResponsable);
         id = 9L;
         user = new User(id,"hajar","hajar","hajar","hajar","3B",rolesAdmin,null,null,null,null);
         user1 = new User(7L,"Sofia","Sofia","Sofia","Sofia","3B",rolesPilote,null,null,null,null);
         user2 = new User(10L,"hajar","hajar","username","username","3B",rolesResponsable,null,null,null,null);
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
         username ="hajar";
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/saveAll").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userList)));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void givenUsers_whenGetAllUsers_thenListOfUsers() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(userList.size())));
    }
    @Test
    public void shouldReturnUserById() throws Exception{
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/id/"+id));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(user.getUsername())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId().intValue())));
    }
    @Test
    public void shouldReturnUserByUsername() throws Exception{
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/username/"+username));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(user.getUsername())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId().intValue())));
    }
    @Test
    void ShouldSaveUser() throws Exception {
        Role r = roleService.findByName("ROLE_ADMIN");
        userRole.setUser(user);
        userRole.setIdRole(r.getId());
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/saveUser").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRole)));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(user.getUsername())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId().intValue())));
    }
    @AfterEach
    public void Logout(){
          authREST.logoutAll();
    }
}