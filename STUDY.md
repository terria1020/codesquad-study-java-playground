### 목표 달성을 위한 코드 작성에 대한 생각 등

PlanItem 대신에 Schedule이라는 클래스명을 사용했다. 기존에 그렇게 메소드명을 작성하고 변수명을 작성했기 때문에 Schedule로 명명했다.

Schedule 클래스가 HashMap를 가지고 기존의 코드를 먹는 상태로 갈지, HashMap의 요소들을 분리한 LocalDate, String 타입을 가질지 고민 끝에
요소들을 분리하여 가지고 있기로 했다. Calender에서 Schedule을 ArrayList 형태로 가지면 좋은 형태가 될 것 같다.

강좌에서는 HashMap를 그대로 사용하고, Schedule의 내용에 해당하는 String을 아예 PlanItem으로 만들어, HashMap<Date, PlanItem> 형식으로 구현했다.
PlanItem은 Date 타입과 String 타입 변수를 갖는다.

강좌에서, 새로운 PlanItem을 생성해, HashMap.put 호출 시 planItem의 date를 받아 와 put의 key로 넘겨주고
value로 planItem을 넣는 형식으로 구현했다.

결국, 강좌에서 풀어 간 방법이던 내가 풀어 간 방법이던 일정에 대한 내용을 클래스 화 시키는 것에는 변함이 없다. 단지 구현 방법의 차이라고 본다.

코딩에 정답은 없다. 어떤 정보를 클래스로 분리하여 얼마나 유의미한 정보를 객체화 시키느냐에 포커싱을 두고 각자가 생각한 방법대로 구현하면 된다.
