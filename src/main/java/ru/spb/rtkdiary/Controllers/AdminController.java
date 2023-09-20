package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin1")
public class AdminController {
//    private final TeachService teachService;
//
//    private final SubjectsService subjectsService;
//    private final SubjectsDao subjectsDao;
//    private final GroupDAO groupDAO;
//
//    private final GroupService groupService;
//    private final PeopleService peopleService;
//    private final GroupRepo groupRepo;
//
//    @Autowired
//    public AdminController(TeachService teachService, SubjectsService subjectsService, SubjectsDao subjectsDao, GroupDAO groupDAO, GroupService groupService, PeopleService peopleService, GroupRepo groupRepo) {
//        this.teachService = teachService;
//        this.subjectsService = subjectsService;
//        this.subjectsDao = subjectsDao;
//        this.groupDAO = groupDAO;
//        this.groupService = groupService;
//        this.peopleService = peopleService;
//        this.groupRepo = groupRepo;
//    }
//
//    @GetMapping
//    public String index(Model model){
//        model.addAttribute("teachers", teachService.findAll());
//        System.out.println(groupRepo.findGroupByTeacherssId(1));
//        return "teachers/index";
//    }
//
//    @GetMapping("/{id}")
//    public String teacher(@PathVariable("id") int id, Model model){
//        model.addAttribute("tea", teachService.findOne(id));
//        model.addAttribute("sub", subjectsDao.findTeachersSub(id));
//        return "teachers/one";
//    }
//
//    @GetMapping("/{tea_id}/{sub_id}")
//    public String subject(@PathVariable("tea_id")int tea_id, @PathVariable("sub_id")int sub_id,Model model){
//        model.addAttribute("tea", teachService.findOne(tea_id));
//        model.addAttribute("sub", subjectsService.findOne(sub_id));
//        model.addAttribute("groups", groupDAO.findGroupByTeaIdAndSubId(tea_id,sub_id));
//        return "teachers/subjects";
//    }
//
//    @GetMapping("/{tea_id}/{sub_id}/{group_id}")
//    public String peoples(@PathVariable("tea_id") int tea_id, @PathVariable("sub_id")int sub_id,@PathVariable("group_id")int group_id,Model model){
//        model.addAttribute("tea", teachService.findOne(tea_id));
//        model.addAttribute("sub", subjectsService.findOne(sub_id));
//        model.addAttribute("group", groupService.findOne(group_id));
//        model.addAttribute("peoples", peopleService.findPeoplesByGroupId(group_id));
//        return "/teachers/people/tablepeoples";
//
//    }
//
//    @GetMapping("/popa")
//    public String popa(){
//        return "/teachers/people/document";
//    }
//
//    @GetMapping("/look")
//    @ResponseBody
//    public String hello(){
//        System.out.println("Я здесь");
//        return "Say Hello";
//    }
    @GetMapping("/gok")
    public String hok(){
        return "helolo world";
    }


}
