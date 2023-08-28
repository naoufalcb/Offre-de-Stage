package com.example;

import com.example.entities.Student;
import com.example.repository.SkillRepository;
import com.example.repository.StudentRepository;
import com.example.security.Account;
import com.example.security.Role;
import com.example.service.AccountService;
import com.example.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Main implements CommandLineRunner {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    private AccountService accountService;


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        accountService.addNewRole(new Role(null,"ADMIN"));
//        accountService.addNewRole(new Role(null,"STUDENT"));






        /*Student student = Student.builder()
                .firstName("Mansour")
                .lastName("MANSOUR")
                .internee(false)
                //.skills(new ArrayList<>())
                .build();




        *//*Skill skill = Skill.builder()
                .skill_name("CSS")
                .build();*//*

        student.setAccount(accountService.);
        studentRepository.save(student);
        //skillRepository.save(skill);*/



//        Student student2 = new Student();
        //Student student2 = new Student("Ahmed","AHMED",true);

//        Skill skill1 = new Skill("JavaScript");
//        Skill skill2 = new Skill("HTML");
//
//
//
//        student1.addSkill(skill1);
//        student1.addSkill(skill2);
//        student2.addSkill(skill1);

//        accountService.addNewRole(new Role(null,"STUDENT"));
//        Account account2 = Account.builder()
//                .username("student2")
//                .password("2222")
//                .build();
//        accountService.addNewAccount(account2);
//        accountService.addRoleToAccount("student2","STUDENT");
//        student2.setAccount(accountService.addNewAccount(account2));



        /*studentRepository.save(student1);
        studentRepository.save(student2);
        skillRepository.save(skill1);

        skillRepository.save(skill2);*/


////        Set<Skill> skl2 = ;
////        Student st2 = Student.builder()
////                .first_name("Ahmed")
////                .last_name("ahmed")
////                .internee(true)
////                .skills(skl2)
////                .build();
////
////        Set<Skill> skl1= Skill.builder()
////                .skill_name("JavaScript")
////                .build();

        /*accountService.addNewRole(new Role(null,"ADMIN"));
      Account admin = Account.builder()
                .username("admin")
                .password("admin")
                .build();
        accountService.addNewAccount(admin);
        accountService.addRoleToAccount("admin","ADMIN");*/

        /*accountService.addNewRole(new Role(null,"STUDENT"));
        Account account = Account.builder()
                .username("account")
                .password("account")
                .build();
        accountService.addNewAccount(account);
        accountService.addRoleToAccount("account","STUDENT");*/


        /*Student student = Student.builder()
                .firstName("Mansour")
                .lastName("MANSOUR")
                .internee(false)
                .build();
        studentRepository.save(student);

        accountService.addNewRole(new Role(null,"STUDENT"));
        Account account = Account.builder()
                .username("account")
                .password("account")
                .build();
        accountService.addNewAccount(account);
        accountService.addRoleToAccount("account","STUDENT");



        student.setAccount(account);
        studentRepository.save(student);
*/





    }
}
