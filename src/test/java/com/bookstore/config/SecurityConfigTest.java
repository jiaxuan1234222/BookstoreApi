package com.bookstore.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user")
    public void testBookEndpointWithAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDeleteWithoutAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/26a82426-06f7-4f72-86f9-0478499c10cb"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/26a82426-06f7-4f72-86f9-0478499c10cb"))
                .andExpect(status().isOk());
    }
}
