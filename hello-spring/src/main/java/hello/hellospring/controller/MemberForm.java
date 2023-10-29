package hello.hellospring.controller;

public class MemberForm {
    private String name; // 변수 name과 템플릿의 name="name" 이 매칭
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}