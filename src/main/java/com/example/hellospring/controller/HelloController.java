package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//페이지가 들어오면 스프링에서는 static 폴더를 확인 하기 전에 controller 에 선언된 경로가 있는지 확인한다.
@Controller
public class HelloController {

    //1강 View 환경설정 강의
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //2섹션 MVC강의 / 페이지 파라미터에서 ?name="" 으로 name 값 설정해주어야함.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //2섹션 API 강의 / html 페이지를 띄워주는게 아닌, 문자 그대로를 노출
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    //2섹션 API 강의 / 문자가 아닌 객체를 넘주면 json 방식으로 반환한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //2섹션 API 혼자 공부한 내용 / 객체가 아닌 그냥 String 으로 getter setter 사용
    //getter setter 는 private 선언된 내용 핸들링할때 사용
    @GetMapping("hello-api-cms")
    @ResponseBody
    public String helloApiCms(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello.getName();
    }

    static class Hello {
        //getter setter 단축키 = Alt + Insert
        //프로퍼티 접근방식이라고 한다.
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
