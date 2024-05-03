package bubble.test.ex07;

public interface Moveable {
	
	// public abstract 생략 가능
	public abstract void left();
	void right();
	void up();
	// 인터페이스 추가 기능 default 사용해보기
	// 인터페이스에 모든 메서드는 추상 메서드이어야 한다.
	// 단 default 메서드를 제외 하고
	default void down() {};
	// 마지막에 default는 세미 콜론 추가해야 한다. (버전에따라 다르긴함)
}
