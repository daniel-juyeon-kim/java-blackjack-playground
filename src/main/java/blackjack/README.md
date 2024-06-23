- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다. 
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
  - 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다. 
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
  - 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다. 
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
  - 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

- 반복되는 조건문을 제거하는 방법 중의 하나는 객체 지향의 다형성을 활용해 해결할 수 없는지 검토해 본다. 
- 게임 내 규칙을 자바 객체로 추상화한다.
  - 힛(Hit): 처음 2장의 상태에서 카드를 더 뽑는 것
  - 스테이(Stay): 카드를 더 뽑지 않고 차례를 마치는 것
  - 블랙잭(Blackjack): 처음 두 장의 카드 합이 21인 경우, 베팅 금액의 1.5배 
  - 버스트(Bust): 카드 총합이 21을 넘는 경우. 배당금을 잃는다.
- 현재 상태에서 다음 상태의 객체를 생성하는 역할을 현재 상태가 담당하도록 한다.

## ex

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000
jason: -20000
```

## TODO

- view
  - [x] View
    - [x] print(message)
    - [x] enterLine()
  - [x] InputView
    - [x] List<Player> inputName()
    - [x] boolean choiceHit(Player)
  - [x] ResultView
    - [x] void showInitialCards(Dealer, List<Participant>)
    - [x] void showHitCards(Participant participant)
    - [x] void showGameResult(Dealer, List<Participant>)
    - [x] void showDealerDrawnCard(Dealer)
- model
  - participant
    - [x] Participant
      - [x] void hit(Card)
      - [x] void stay()
      - [x] String getName()
      - [x] Cards getCards()
      - [x] boolean isFinished()
    - [x] AbstractParticipant
      - [x] void stay()
      - [x] Cards getCards()
      - [x] boolean isFinished()
    - [x] Dealer
      - [x] void hit(Card)
      - [x] String getName()
      - [x] double profit(List<Player> players)
      - [x] double profit(List<Player>)
    - [x] Player
      - [x] void hit(Card)
      - [x] String getName()
      - [x] double profit(Dealer)
  - state
    - [x] State
      - [x] State draw(Card)
      - [x] State stay()
      - [x] Cards cards()
      - [x] boolean isFinished()
      - [x] double profit(double)
    - abstracts
      - [x] Started
        - [x] Cards cards()
      - [x] Running
        - [x] boolean isFinished()
      - [x] Finished
        - [x] State draw(Card)
        - [x] State stay() 
        - [x] boolean isFinished()
        - [x] double profit(double)
        - [x] abstract double earningRate()
    - implements
      - [x] Hit
        - [x] void draw(Card)
        - [x] State stay();
        - [x] double profit(double)
      - [x] Blackjack
        - [x] double earningRate();
      - [x] Bust
        - [x] double earningRate();
      - [x] Stay
        - [x] double earningRate();
  - card
    - [x] Cards
      - [x] void add(Card)
      - [x] boolean isBlackjack()
      - [x] boolean isBust()
      - [x] int getScore()
      - [x] String getName()
      - [x] int size()
    - [x] CardFactory
      - [x] Card create
    - [x] Card
      - [x] boolean isAce()
      - [x] int getScore()
      - [x] String getName()
    - [x] Random
      - [x] int createZeroToMax(int)
    - [x] Denomination
      - [x] Denomination find(int index)
      - [x] boolean isAce()
      - [x] int getScore()
    - [x] Suit
      - [x] Suit find(int index)
      - [x] String getName()
  - [x] Game
    - [x] void play()
- controller
  - [x] blackjackController
    - [x] void run()
