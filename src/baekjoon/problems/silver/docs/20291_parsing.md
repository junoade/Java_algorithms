# 파일 정리 성공출처분류

| 시간 제한             | 메모리 제한 | 제출 | 정답 | 맞은 사람 | 정답 비율 |
| :-------------------- | :---------- | :--- | :--- | :-------- | :-------- |
| 3 초 (추가 시간 없음) | 1024 MB     | 410  | 251  | 203       | 66.776%   |

## 문제

친구로부터 노트북을 중고로 산 스브러스는 노트북을 켜자마자 경악할 수밖에 없었다. 바탕화면에 온갖 파일들이 정리도 안 된 채 가득했기 때문이다. 그리고 화면의 구석에서 친구의 메시지를 확인할 수 있었다.

바탕화면의 파일들에는 값진 보물에 대한 정보가 들어 있어. 하나라도 지우게 된다면 보물은 물론이고 다시는 노트북을 쓸 수 없게 될 거야. 파일들을 잘 분석해서 보물의 주인공이 될 수 있길 바랄게. 힌트는 “확장자”야.

화가 났던 스브러스는 보물 이야기에 금세 화가 풀렸고 보물의 정보를 알아내려고 애썼다. 하지만 파일이 너무 많은 탓에 이내 포기했고 보물의 절반을 보상으로 파일의 정리를 요청해왔다. 스브러스의 요청은 다음과 같다.

- 파일을 확장자 별로 정리해서 몇 개씩 있는지 알려줘
- 보기 편하게 확장자들을 사전 순으로 정렬해 줘

그럼 보물의 절반을 얻어내기 위해 얼른 스브러스의 노트북 파일 정리를 해줄 프로그램을 만들자!

## 입력

첫째 줄에 바탕화면에 있는 파일의 개수 N이 주어진다. (1≤N≤50 000)

둘째 줄부터 N개 줄에 바탕화면에 있는 파일의 이름이 주어진다. 파일의 이름은 알파벳 소문자와 점(`.`)으로만 구성되어 있다. 점은 정확히 한 번 등장하며, 파일 이름의 첫 글자 또는 마지막 글자로 오지 않는다. 각 파일의 이름의 길이는 최소 3, 최대 100이다.

## 출력

확장자의 이름과 그 확장자 파일의 개수를 한 줄에 하나씩 출력한다. 확장자가 여러 개 있는 경우 확장자 이름의 사전순으로 출력한다.

## 예제 입력 1 복사

```
8
sbrus.txt
spc.spc
acm.icpc
korea.icpc
sample.txt
hello.world
sogang.spc
example.txt
```

## 예제 출력 1 복사

```
icpc 2
spc 2
txt 3
world 1
```

## 노트

엄청난 보물의 정체는 바탕화면을 정리했다는 뿌듯함이라고 알려진다.

## 출처

[University](https://www.acmicpc.net/category/5) > [서강대학교](https://www.acmicpc.net/category/83) > [2020 Sogang Programming Contest (Champion)](https://www.acmicpc.net/category/detail/2354) A번

- 문제를 검수한 사람: [gumgood](https://www.acmicpc.net/user/gumgood), [jhnah917](https://www.acmicpc.net/user/jhnah917), [pichulia](https://www.acmicpc.net/user/pichulia), [QuqqU](https://www.acmicpc.net/user/QuqqU), [raararaara](https://www.acmicpc.net/user/raararaara), [rhs0266](https://www.acmicpc.net/user/rhs0266), [shiftpsh](https://www.acmicpc.net/user/shiftpsh), [sogangcse](https://www.acmicpc.net/user/sogangcse), [woonikim](https://www.acmicpc.net/user/woonikim)
- 문제를 만든 사람: [sbrus_1213](https://www.acmicpc.net/user/sbrus_1213)

## 알고리즘 분류

- [구현](https://www.acmicpc.net/problem/tag/102)
- [문자열](https://www.acmicpc.net/problem/tag/158)
- [정렬](https://www.acmicpc.net/problem/tag/97)
- [파싱](https://www.acmicpc.net/problem/tag/96)

## 메모