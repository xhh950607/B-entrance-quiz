package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupRepository {
    private static List<Group> groupList;

    public static void init() {
        groupList = Stream.of(1, 2, 3, 4, 5, 6)
                .map(id -> new Group(id, "Term " + id, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public static List<Group> getGroupList() {
        return groupList;
    }

    public static Optional<Group> findOneByName(String name) {
        return groupList.stream()
                .filter(group -> group.getName().equals(name))
                .findFirst();
    }

    public static Optional<Group> findOneById(int id) {
        return groupList.stream()
                .filter(group -> group.getId() == id)
                .findFirst();
    }
}
