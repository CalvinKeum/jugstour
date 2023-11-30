package com.calvinkeum.developer.jugtours;

import com.calvinkeum.developer.jugtours.model.Event;
import com.calvinkeum.developer.jugtours.model.Group;
import com.calvinkeum.developer.jugtours.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository groupRepository;

    public Initializer(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG", "London JUG")
                .forEach(name -> groupRepository.save(new Group(name))
        );

        Group group = groupRepository.findByName("Seattle JUG");

        Event event = Event.builder().title("Micro Frontends for Java Developers")
                .description("JHipster now has microfrontend support!")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();

        group.setEvents(Collections.singleton(event));

        groupRepository.save(group);
        groupRepository.findAll().forEach(System.out::println);
    }
}
