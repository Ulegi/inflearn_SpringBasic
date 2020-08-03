package Hello.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    //hello 라는 요청이 들어오면 hello controller로 찾아옴
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!");
        //키가 data, 값이 hello (thymeleaf기능 => hello.html 보면 ${data}있음)
        return "hello";
        //templates 디렉토리에 있는 hello 파일로 렌더링(넘겨줌)해줌
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        //@RequestParam은 파라미터를 url에서 받겠다는거임
        //그리고 @RequestParam 안에 key를 넣어준다. String 뒤에 있는 것의 값을 담을 변수이다.
        //required라는 옵션은 기본이 true이다(true로 해서 값을 받을꺼면 위에처럼 안 써도 됨). false로 하면 파라미터를 받지 않아도 된다.
        //model 에 담으면 view에서 렌더링할 때 씀
        model.addAttribute("name", name);
        //여기서 model에 파라미터로 받은 name이 담김
        return "hello-template";
        //model에 name이 담겨서 hello-template으로 넘어감
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http에서 head와 body가 있는데 이 body에 직접 내용을 넣어주겠다. (html body 아님)
    //위에서 한 것과 차이는 템플릿을 거치지 않고(뷰파일과 상관없이) 그냥 여기서 넣는 데이터만 보내버림
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        //그래서 여기 hello 가 뷰파일이 아닌 문자열로 작용하는 것
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        //여기서 Hello라는 객체를 사용하려면 아래에 Hello라는 클래스를 꼭 선언해주어야한다.
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //이러면 name이라는 키와 그에 해당하는 값만을 JSON 형식으로 웹화면에 보여준다.
    }

    static class Hello {
        private String name;

        public String getName() {
            //* getName 메소드는 해당 클래스의 이름을 반환한다.
            return name;
            //* 여기서는 name에 클래스 이름을 담아 반환한다.
        }

        public void setName(String name) {
            this.name = name;
            //* setName 메소드는 파라미터로 받은 문자열을 인스턴스 변수 name에 저장한다.
            //* setName 메소드를 사용함으로써 private으로 선언된 Hello 클래스의 name변수에 접근할 수 있다.
        }
    }

}
