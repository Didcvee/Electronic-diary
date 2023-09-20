package ru.spb.rtkdiary.utils;

import ru.spb.rtkdiary.DTO.GroupDTO;
import ru.spb.rtkdiary.DTO.SubjectsDTO;

import java.util.ArrayList;
import java.util.List;

public class WrapperSubjectsDTOAndGroupDTO {
    private final List<SubjectsDTO> subjectsDTOList = new ArrayList<>();
    private final List<GroupDTO> groupDTOList = new ArrayList<>();

    public WrapperSubjectsDTOAndGroupDTO(String subjectName, Integer subjectId, String groupName, Integer groupId) {
        SubjectsDTO subjectsDTO = new SubjectsDTO(subjectId, subjectName);
        subjectsDTOList.add(subjectsDTO);
        GroupDTO groupDTO = new GroupDTO(groupId,groupName);
        groupDTOList.add(groupDTO);
    }

    public WrapperSubjectsDTOAndGroupDTO() {
    }

    public List<SubjectsDTO> getSubjectsDTOList() {
        return subjectsDTOList;
    }



    public List<GroupDTO> getGroupDTOList() {
        return groupDTOList;
    }


}
