package ru.hogwarts.school.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile file) throws IOException;

    String getExtension(String fileName);

    Avatar findAvatarByStudentId(Long id);

    List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);
}
