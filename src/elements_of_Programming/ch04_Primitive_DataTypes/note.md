
## CH4. 기본 자료형

### 체크목록
- 기본 자료형의 크기, 구간, 부호 여부, 연산자에 익숙한가?
- Primitive와 Boxed Primitive(Wrapper class)의 차이를 아는가? Auto-Boxing, Unboxing이 무엇인가?
- java.lang.Math 클래스에서 제공하는 수학과 관련한 핵심 정적 메소드들이 익숙한가?
  > Math.abs(), ceil(), floor(), min(), max(), pow(), sqrt(), random() 등 
- Random 라이브러리의 핵심 메소드가 익숙한가?
  > nextInt(16), nextInt(), nextBoolean(), nextDouble() 등
- 비트 연산자를 활용한 비트 연산자를 잘 다루는 가?
- Boxed Primitive의 메소드들을 활용하여
  - 최댓값, 최솟값을 다룰 수 있는가
  - Boxed Primitive의 인스턴스를 생성할 수 있는가
- 정수, 문자, 문자열간 변환 방법에 익숙한가? 


### 학습 기록 
- 22/04/28
   - CH04.01 패리티의 의미와 비트 연산자, 마스킹을 활용하여 패리티 계산하기
   - CH04.02 비트 스왑하기
   - CH04.03 비트 뒤집기
     - 비트 스왑 방법 활용하기 (O)
     - lookup table을 통해 캐싱 이용하기 (나중)

- 22/05/05
  - CH04.04 같은 무게(weight)를 갖는 가장 가까운 정수 찾기
    > 음이 아닌 어떤 정수 x에 대해, 2진수로 표현하고, 1의 개수를 weight로 정의한다 가정하자. 예를 들어, f(92) = 4 이다. x와 무게는 같지만, x와의 차이가 최소가 되는 y를 반환하는 프로그램을 작성하시오.
  
    
### 참고하면 좋은 링크

- 비트 연산 활용 관련
> https://graphics.stanford.edu/~seander/bithacks.html
