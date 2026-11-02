package com.example.journalingapp.repository;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

//@SpringBootTest //now spring application context is to be going on the null mode.

public class UserRepositoryImplTest {
    //    @Autowired
    @InjectMocks
    public UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        try {
            MockitoAnnotations.openMocks(this); //very FAST / RAPIDLY it will work because of no fetching the data from the databases because of return fake object.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadUSerByUsernameTest() {
        //ArgumentMatchers.anyString() :- also we will be used in place of this one particular username like findByUsername("name").and so on...
        // in this way we achieved  user serviced;
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().username("pavan").password("pavan").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("pavan");
        Assertions.assertNotNull(user);
    }
}
/*
@SpringBootTest :- Component is to used for the testing the components of all the spring boot applications.
@InjectMocks : is to be defining the mock injecting on the particular Fields
@MockBean / @MockitoBean :- annotation only iff used whenever we used the @SpringBootTest Annotation otherwise
Commonly  used @Mock Annotation
which is to be thrown NullPointerException without creating @Autowired or @Bean
Radis:- is a cache which is to be used for the connecting the databases and work something on it.

PROFILES:-every application has two environment viz:- DEVELOPEMENT & PRODUCTION.


 */