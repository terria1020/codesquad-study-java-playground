### 목표 달성을 위한 코드 작성에 대한 생각 등

명령어 출력을 위한 help, console 출력 담당 개발 -> Prompt.run 메소드를 개편

printCalender() 함수를 재활용 할 수 있는 것으로 코드 재사용성에 대해 더 깨닫게 되었음

오늘의 날짜를 인식해 이번 달 달력을 출력하는 것에서, Deprecated 된 Date 타입 대신 LocalDateTime을 사용했음

schedule을 출력하기 위해 schedule이 존재하는지를 담고 있는 boolean 변수를 추가, 그 변수의 flag들로 스케줄의 존재 여부를 출력