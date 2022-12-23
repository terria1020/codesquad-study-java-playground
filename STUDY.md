### 목표 달성을 위한 코드 작성에 대한 생각 등

PlanItem 대신에 Schedule이라는 클래스명을 사용했다. 기존에 그렇게 메소드명을 작성하고 변수명을 작성했기 때문에 Schedule로 명명했다.

Schedule 클래스가 HashMap를 가지고 기존의 코드를 먹는 상태로 갈지, HashMap의 요소들을 분리한 LocalDate, String 타입을 가질지 고민 끝에
요소들을 분리하여 가지고 있기로 했다. Calender에서 Schedule을 ArrayList 형태로 가지면 좋은 형태가 될 것 같다.

