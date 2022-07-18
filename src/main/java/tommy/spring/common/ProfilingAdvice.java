package tommy.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class ProfilingAdvice {
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		// around 어드바이스에서만 ProceedingJoinPoint를 매개변수로 사용(proceed()메소드가 필요하기 때문에)
		// After Returning, After Throwing, After 어드바이스는 JoinPoint를 매개변수로 사용
		String signatureString = joinPoint.getSignature().toShortString();
		// joinPoint에서 제공하는 메서드: Signature getSignature(), Object getTarget(), Object[] getArgs() 
		// getSignature(): 클라이언트가 호출한 메서드의 시그니처 (리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체를 리턴 
		// getSignature()메서드가 리턴하는 Signature 객체가 제공하는 메서드: String getName(), String toLongString(), String toShortString() 
		// toShortString(): 클라이언트가 호출한 메서드의 시그니처를 축약한 문자열로 리턴
		
		// => 클라이언트가 호출한 메서드의 시그니처 정보가 저장된 객체를 축약한 문자열로 리턴해서 signatureString에 저장
		System.out.println(signatureString + " 시작");
		long start = System.currentTimeMillis(); // 현재시각을 밀리세컨드 단위로 반환
		try {
			Object result = joinPoint.proceed();
			// proceed()메서드를 사용해서 실제 대상 객체의 메서드를 호출
			// joinPoint.proceed()메서드를 실행시, 대상 객체의 메서드가 실행되므로 이 코드 전후로 공통 기능을 위한 코드를 위치시킴
			return result;
		}finally {
			long finish = System.currentTimeMillis();
			System.out.println(signatureString + " 종료");
			System.out.println(signatureString + " 실행 시간: " + (finish - start) + "ms");
		}
	}
}
