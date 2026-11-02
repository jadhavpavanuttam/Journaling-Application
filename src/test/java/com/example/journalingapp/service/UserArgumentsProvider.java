package com.example.journalingapp.service;


import com.example.journalingapp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("sanjya").password("sanjya").build()),
                Arguments.of(User.builder().username("sivam").password("sivam").build())
        );
    }
}
/*
 *Unit testing :-
 *
 * multiples of components testings is known as unit testing.
 *
 * Integration testing :-
 *
 */
