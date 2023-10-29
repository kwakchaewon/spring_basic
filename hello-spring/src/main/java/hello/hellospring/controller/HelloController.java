package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // http://localhost:8080/hello-mvc?name=spring!
    // 1. get 방식으로 name 값 가져 오기
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {

        // 2. 전달할 모델에 name 추가하기
        model.addAttribute("name", name);

        // 3. 템플릿 반환: resources/templates/hello-template.html 반환
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // HTTP Response 에 내가 값을 직접 넣을때
    // 1. get 방식으로 name 값 가져 오기
    public String helloString(@RequestParam(value = "name") String name, Model model) {
        return "hello" + name; // "hello spring" 문자열만 출력
    }

    @GetMapping("hello-api")
    @ResponseBody // ResponseBody 태그 후 객체 반환시, json으로 반환됨
   // Hello 객체 리턴시 json 으로 반환
    public Hello helloApi(@RequestParam("name") String name) {
            Hello hello = new Hello();
            hello.setName(name);
            return hello;
        }

    static class Hello {

        private String name;

        // Getter & Setter 생성 : alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
