package com.example.journalingapp.service;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

//@SpringBootTest //now spring application context is to be going on the null mode.
@Slf4j
@ActiveProfiles("dev")
public class UserServiceImplTest {
    // let suppose we want to create csv and create a file in this one. that time we have to used @BeforeAll ,@AfterAll ,and so on...
    @InjectMocks
    public UserDetailsServiceImpl userDetailsService;
    @MockitoBean
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        try {
            MockitoAnnotations.initMocks(this);
//            MockitoAnnotations.openMocks(this); //very FAST / RAPIDLY it will work because of no fetching the data from the databases because of return fake object.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadUSerByUsernameTest() {

        try {
            User pavan = userRepository.findByUsername("pavan");
            assertTrue(!pavan.getJournalEntries().isEmpty());

            when(userRepository
                    .findByUsername(ArgumentMatchers.anyString()))
                    .thenReturn(User
                            .builder()
                            .username("pavan")
                            .password("pavan")
                            .roles(new ArrayList<>())
                            .build());
            UserDetails user = userDetailsService
                    .loadUserByUsername("pavan");
            Assertions.assertNotNull(user);
        } catch (Exception e) {
            log.error(String.valueOf(e.fillInStackTrace()));
            Thread.currentThread().interrupt();
        }
    }
}

/*
 Optional<User> pavan = Optional.of(userRepository.findByUsername("pavan"));
 assertTrue(pavan.stream().toList().size() == 1);
 ArgumentMatchers.anyString() :- also we will be used in place of this one particular username like findByUsername("name").and so on...
 in this way we achieved  user serviced;
 OngoingStubbing<User> userOngoingStubbing = when(userRepository.findByUsername(ArgumentMatchers.any())).thenReturn(User.builder().username("pavan").password("innerins").build());

@SpringBootTest :- Component is too used for the testing the components of all the spring boot applications.
@InjectMocks : is to be defining the mock injecting on the particular Fields
@MockBean / @MockitoBean :- annotation only iff used whenever we used the @SpringBootTest Annotation otherwise
Commonly  used @Mock Annotation
which is to be thrown NullPointerException without creating @Autowired or @Bean
Radis:- is a cache which is to be used for the connecting the databases and work something on it.
@Mock --> is used for to create a fake user
PROFILES:-every application has two environment viz:- DEVELOPMENT & PRODUCTION.

@ActiveProfiles("dev")  //Or Make sure which is also can be working on the @AutoProfiles("prod") in this way we have to use this one.
 */