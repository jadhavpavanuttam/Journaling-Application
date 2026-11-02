package com.example.journalingapp.service;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;  //nullPointerException

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUSer(user));
    }

//    assert --> Dave Karna


    //    @AfterAll  ,@BeforeAll
//    public void setUp(int x, int y, int having) {
//        assertEquals(having, x + y);
//        System.out.println("Yes confirmed....!!");
//    }
    @ParameterizedTest
//    @CsvSource(
    @ValueSource(strings = {
            "pavan",
            "amol",
            "jagruti"
    })
    public void testFindByUsername(String name) {
        assertNotNull(userRepository.findByUsername(name), "failed for : " + name);
    }

    //    @Disabled  //that means ignored this one...
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "4,5,9",
            "3,5,8"
    })
    public void testIt(int x, int y, int expected) {
        assertEquals(expected, x + y);
    }

}


/**
 * if test is right --> true else false or terminated the program --> assertEquals(5, 3 + 2);
 *
 * @ValueSource(strings = {
 * "pavan",
 * "jagruti",
 * "amol",
 * "mohit"
 * })
 * @CsvSource({ "pavan",
 * "jagruti",
 * "hitesh",     //failed in this line because of it has not in the DB.
 * "amol",
 * "mohit"
 * })
 * @EnumSource
 * @ParameterizedTest
 * @CsvSource({ "1,2,3",
 * "5,4,9",
 * "3,4,7"
 * })
 * public void test(int x, int y, int expected) {
 * assertEquals(expected, x + y);
 * }
 */