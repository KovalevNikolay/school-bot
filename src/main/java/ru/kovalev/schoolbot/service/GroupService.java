package ru.kovalev.schoolbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.schoolbot.mapper.GroupMapper;
import ru.kovalev.schoolbot.model.dto.GroupDto;
import ru.kovalev.schoolbot.repository.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public List<GroupDto> findAll() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }
}
