package ru.kovalev.schoolbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.schoolbot.mapper.UserMapper;
import ru.kovalev.schoolbot.model.dto.UserDto;
import ru.kovalev.schoolbot.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean existById(Long id) {
        return userRepository.existsById(id);
    }

    public void createUser(UserDto dto) {
        userRepository.save(userMapper.toEntity(dto));
    }

    public Optional<UserDto> getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    public void update(UserDto dto) {
        if (existById(dto.getId())) {
            userRepository.save(userMapper.toEntity(dto));
        }
    }
}
