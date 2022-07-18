package tommy.spring.board.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tommy.spring.board.service.WriteArticleService;
import tommy.spring.board.vo.ArticleVO;
import tommy.spring.member.service.MemberService;
import tommy.spring.member.vo.MemberVO;

public class MainOne {

	public static void main(String[] args) {
		String[] configLocations = new String[] { "applicationContextOne.xml" };
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
		// context: Bean의 확장 버전으로 Spring이 Bean을 다루기 좀 더 쉽도록 기능들이 추가된 공간
		// AbstractApplicationContext: Application Context를 구현한 추상 클래스로, 내부에 정의된 특수한 Bean들을 등록할 수 있음
		// ClassPathXmlApplicationContext: ClassPath에 위치한 xml 파일을 읽어 설정 정보를 로딩(root로부터의 경로를 지정하면 됨)
		WriteArticleService articleService = (WriteArticleService)context.getBean("writeArticleService");
		// tommy.spring.board.service.WriteArticleService 인터페이스
		// context.getBean("writeArticleService"): xml파일에서 id가"writeArticleService"인 bean을 읽어옴
		articleService.write(new ArticleVO());
		MemberService memberService = context.getBean("memberService", MemberService.class);
		// 매개변수로 BeanName+Class를 보내므로 보다 명확하게 객체를 얻어올수가 있음(동일한 Class 타입이 있을 수 있기에)
		// .class: 매개변수로 보내는 Class 타입의 인스턴스를 반환하는 것
		// 매개변수로 Bean id값만 보내게되면 오브젝트 타입으로 리턴하므로 형변환을 해줘야한다
		memberService.regist(new MemberVO());
		context.close();

	}
}
