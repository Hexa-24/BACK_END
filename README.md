# BACK_END

## Development Environment

- IDE:          Visual Studio Code
- Java:         Corretto JDK 17
- Framework:    Spring Boot (ver3.2.2)
- Build Tool:   Maven
- WAS:          Undertow
- VCS:          Git/Github
- Stacks:       JPA

## Naming Convensions

### Controller

HTTP 메서드와 기능에 기본적으로 대응 될 수 있게 구성

- getMember()
- postMember()
- putMember()
- deleteMember()

### Service

컨트롤러와는 별개로 구성하되 CRUD를 분명히 나타낼 수 있는 표현

- findMember()
- addMember()
- modifyMember()   
- removeMember()


